package com.datas.easyorder.controller.administrator.customer.logic;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import com.datas.easyorder.controller.BaseLogic;
import com.datas.easyorder.controller.administrator.customer.Transfer;
import com.datas.easyorder.controller.administrator.invoice.InvoiceView;
import com.datas.easyorder.controller.web.customer.CustomerView;
import com.datas.easyorder.db.dao.CustomerPaymentHistoryRepository;
import com.datas.easyorder.db.dao.CustomerRepository;
import com.datas.easyorder.db.dao.CustomerSpecifications;
import com.datas.easyorder.db.dao.InvoiceRepository;
import com.datas.easyorder.db.dao.OrderRepository;
import com.datas.easyorder.db.dao.UserCompanyRepository;
import com.datas.easyorder.db.entity.Customer;
import com.datas.easyorder.db.entity.CustomerPaymentHistory;
import com.datas.easyorder.db.entity.Invoice;
import com.datas.easyorder.db.entity.User;
import com.datas.easyorder.db.entity.UserCompany;
import com.datas.utils.SearchForm;
import com.plugin.mail.logic.SendMail;
import com.plugin.pdf.HtmlPDF;
import com.plugin.utils.DateHelper;
import com.plugin.utils.Md5;

@Component("customerLogic")
public class CustomerLogic extends BaseLogic<Customer>{

	public final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BaseLogic.class);
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private  InvoiceRepository invoiceRepository;
	@Autowired
	private  CustomerPaymentHistoryRepository customerPaymentHistoryRepository;
	@Autowired
	private  HtmlPDF htmlPDF;
	@Autowired
	private  TemplateEngine templateEngine;
	@Autowired
	private UserCompanyRepository userCompanyRepository;
	@Autowired
	private SendMail sendMail;
	@Autowired
	OrderRepository orderRepository;
	
	
	//satatement pdf template
	private String pdfTemplate = "administrator/customer/statement/pdf/template";
	private String relativePathForStatement = "/statement";
	
	
	/**
	 * admin 查询客户
	 * @param searchForm
	 * @param pageable
	 * @return Page<Customer>
	 */
	public Page<Customer> findAll(SearchForm searchForm,String customerType, Pageable pageable) {
		return customerRepository.findAll(CustomerSpecifications.getSearchSpecification(searchForm,customerType), pageable);
	}

	
	/**
	 * 
	 * @param id
	 * @return Customer
	 */
	public Customer findOneById(Long id) {
		Customer Customer = customerRepository.findOne(id);
		return Customer;
	}

	/**
	 * 
	 * @param customer
	 * @return Customer
	 */
	public Customer update(Customer customer) {
		return customerRepository.save(customer);
	}


	/**
	 * web
	 * customer login
	 */
	public Customer login(String username, String password){
		return customerRepository.findOneByEmailAndPasswordAndStatus(username,Md5.getMd5String(password),CustomerRepository.status_active);
	}


	/**
	 * 新增注册用户
	 * @param customerView
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackOn=Exception.class)
	public Customer save(CustomerView customerView,User user) throws Exception {
		
		Customer customer  = customerRepository.findOneByEmail(customerView.getCustomer().getEmail());
		
		if(customer==null){
			customer = new Customer();
			BeanUtils.copyProperties(customerView.getCustomer(), customer);
			
			customer.setPassword(Md5.getMd5String(customerView.getCustomer().getPassword()));
			customer.setCreateTime(Calendar.getInstance().getTime());
			customer.setModifyTime(Calendar.getInstance().getTime());
			customer.setStatus(CustomerRepository.status_inactive);
			customer.setBalance(0D);
			customer.setCustomerType(CustomerRepository.customerType_customer);
			customer.setName(customer.getCompanyName());
			
			customerRepository.save(customer);
			
			
			UserCompany userCompany = userCompanyRepository.findOne(user.getUserCompany().getId());
			sendMail.init(userCompany, customer);
			
			boolean sendRet = sendMail.send(customer.getCompanyName() + " Fronter Trading Online Registration", "Please check member database. CustomerID="+customer.getId());
			if(!sendRet){
				logger.error("customer register email sending faild");
			}
		}
		return customer;
	}

	/**
	 * 检查Email
	 * @param registerEmail
	 * @return
	 */
	public Customer findByEmail(String registerEmail) {
		return customerRepository.findOneByEmail(registerEmail);
	}


	@Override
	public CrudRepository<Customer, Long> getRepository() {
		return customerRepository;
	}

	/**
	 * 获取Invoice和payment statement
	 * @param customerId
	 * @param searchForm
	 * @return
	 */
	public List<Transfer> getTransferList(Long customerId, SearchForm searchForm) {
		
		//opening balance
		Date startDate = DateHelper.parseYYYYMMDD(searchForm.getDateFrom());
		Calendar dateToCalendar = Calendar.getInstance();
		dateToCalendar.setTime(DateHelper.parseYYYYMMDD(searchForm.getDateTo()));
		dateToCalendar.add(Calendar.DATE, 1);
		
		
		Byte[] status     = new Byte[]{InvoiceRepository.status_new,InvoiceRepository.status_emailed,InvoiceRepository.status_finished};
		Pageable pageable = new PageRequest(0, Integer.MAX_VALUE);
		Page<Invoice> invoiceListBefore = invoiceRepository.findByCustomerIdAndStatusInAndModifyTimeBefore(customerId,status,startDate,pageable);
		Double invoiceBeforeBalance = invoiceListBefore.getContent().stream().collect(Collectors.summingDouble(Invoice :: getTotal));
		Page<CustomerPaymentHistory> customerPaymentHistoryBefore = customerPaymentHistoryRepository.findByCustomerIdAndModifyTimeBefore(customerId,startDate,pageable);
		Double paymentBefore = customerPaymentHistoryBefore.getContent().stream().collect(Collectors.summingDouble(CustomerPaymentHistory :: getPayment));
		
		Transfer transfer0 = new Transfer();
		transfer0.setDate(startDate);
		transfer0.setDetails("Opening Balance");
		
		Double openingBalance = invoiceBeforeBalance-paymentBefore;
		transfer0.setBalance(openingBalance);
		
		
		List<Transfer> transferList = new ArrayList<>();
		//invoice List 
		Double totalInvoiceCost = 0D;
		Page<Invoice> invoiceList = invoiceRepository.findByCustomerIdAndStatusInAndModifyTimeBetween(customerId,status,startDate,dateToCalendar.getTime(),pageable);
		for(Invoice invoice:invoiceList){
			Transfer transfer = new Transfer();
			transfer.setDate(invoice.getCreateTime());
			
			transfer.setInvoiceCost(invoice.getTotal());
			transferList.add(transfer);
			
			//invoiceView
			InvoiceView invoiceView = new InvoiceView();
			invoiceView.setInvoice(invoice);
			invoiceView.setInvoiceItemList(invoice.getInvoiceItems().stream().sorted((it1,it2) -> it1.getId().intValue()-it2.getId().intValue()).collect(Collectors.toList()));
			transfer.setInvoiceView(invoiceView);
			totalInvoiceCost += invoice.getTotal();
		}
		
		Double totalDeposites = 0D;
		//Payment List
		Page<CustomerPaymentHistory> customerPaymentHistoryList = customerPaymentHistoryRepository.findByCustomerIdAndModifyTimeBetween(customerId,startDate,dateToCalendar.getTime(),pageable);
		for(CustomerPaymentHistory customerPaymentHistory:customerPaymentHistoryList){
			Transfer transfer = new Transfer();
			transfer.setDate(customerPaymentHistory.getCreateTime());
			transfer.setDetails(customerPaymentHistory.getPaymentMethod());
			transfer.setDeposits(customerPaymentHistory.getPayment());
			transfer.setMemo(customerPaymentHistory.getMemo());
			
			if(customerPaymentHistory.getInvoice()!=null){
				
				//invoiceView
				InvoiceView invoiceView = new InvoiceView();
				invoiceView.setInvoice(customerPaymentHistory.getInvoice());
				invoiceView.setInvoiceItemList(customerPaymentHistory.getInvoice().getInvoiceItems().stream().sorted((it1,it2) -> it1.getId().intValue()-it2.getId().intValue()).collect(Collectors.toList()));
				transfer.setInvoiceView(invoiceView);
			}
			
			transferList.add(transfer);
			totalDeposites += customerPaymentHistory.getPayment();
		}
		
		//按时间排序
		transferList.stream().sorted((t1,t2) -> Integer.valueOf((t2.getDate().getTime() - t1.getDate().getTime())+""));
		transferList.add(0, transfer0);
		for(Transfer t : transferList){
			openingBalance = openingBalance + (t.getInvoiceCost()==null?0:t.getInvoiceCost()) - (t.getDeposits()==null?0:t.getDeposits()) ;
			t.setBalance(openingBalance);
		}
		
		//总计
		Transfer transferLast = new Transfer();
		transferLast.setDetails("Amount Due");
		transferLast.setBalance(openingBalance);
		transferLast.setDeposits(totalDeposites);
		transferLast.setInvoiceCost(totalInvoiceCost);
		transferList.add(transferLast);
	
		return transferList;
	}

	/**
	 * 获取 Customer
	 * @param customerId
	 * @return
	 */
	public Customer getCustomerById(Long customerId) {
		return customerRepository.findOne(customerId);
	}

	/**
	 * 获取StatmentPdf
	 * @param customerId
	 * @param searchForm
	 * @param response
	 * @throws IOException 
	 */
	public void getStatementPdf(Long customerId, User user,SearchForm searchForm,HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String htmlString = templateEngine.process(pdfTemplate, createWebContext(customerId, user, searchForm, request, response));
		
		htmlPDF.pdfOutputStream(htmlString, response.getOutputStream());
	}
	
	/**
	 * 批量打印
	 * @param customerIds
	 * @param lognUser
	 * @param searchForm
	 * @param request
	 * @param response
	 */
	public void getStatementPdf(Long[] customerIds, User user, SearchForm searchForm, HttpServletRequest request, HttpServletResponse response)  throws IOException {
		
		StringBuffer sb = new StringBuffer();
		for(Long customerId:customerIds){
			sb.append(templateEngine.process(pdfTemplate, createWebContext(customerId, user, searchForm, request, response)));
			sb.append("<div style='page-break-after: always;'></div>");
		}
		htmlPDF.pdfOutputStream(sb.toString(), response.getOutputStream());
		
	}
	
	
	/**
	 * email
	 * @param customerId
	 * @param user
	 * @param searchForm
	 * @param request
	 * @param response
	 * @return String
	 */
	public String statementEmail(Long customerId, User user, SearchForm searchForm, HttpServletRequest request,
			HttpServletResponse response) {
		
		String ret = "success";
		try {
			
			//生成Statement
			String html = templateEngine.process(pdfTemplate, createWebContext(customerId, user, searchForm, request, response));
			
			if(!new File( absoultPath+relativePathForStatement + "/" + customerId).exists()){
				new File( absoultPath+relativePathForStatement + "/" + customerId).mkdirs();
			}
			String path =  absoultPath+relativePathForStatement + "/" + customerId + "/"+ "statement-" + customerId + "-" + searchForm.getDateFrom() + searchForm.getDateTo() +".pdf";
			
			Path invoicePath = Paths.get(path);
			OutputStream os =  Files.newOutputStream(invoicePath, StandardOpenOption.CREATE);
			htmlPDF.pdfOutputStream(html.toString(),os);
			os.flush();
			os.close();
			//发送邮件
			UserCompany userCompany = userCompanyRepository.findOne(user.getUserCompany().getId());
			Customer customer = customerRepository.findOne(customerId);
			sendMail.init(userCompany, customer);
			boolean sendRet = sendMail.send(userCompany.getSendingMailSubject() + " #Statement: "+ customerId + searchForm.getDateFrom() + searchForm.getDateTo() , userCompany.getSendingMailTemplate(), new File(path));
			if(!sendRet){
				ret = "send faild.";
			}
			
		} catch (Exception e) {
			ret = e.getMessage();
			e.printStackTrace();
		}
        
		return ret;
	}
	
	
	/**
	 * 创建webContext 生成PDF
	 * @param aliasId
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional(rollbackOn = Exception.class)
	private WebContext createWebContext(Long customerId, User user,SearchForm searchForm,HttpServletRequest request, HttpServletResponse response){
		WebContext webContext = new WebContext(request, response, request.getServletContext());
		
		webContext.setVariable("transferList", this.getTransferList(customerId, searchForm));
		webContext.setVariable("customer", customerRepository.findOne(customerId));
		webContext.setVariable("userCompany", userCompanyRepository.findOne(user.getUserCompany().getId()));
		webContext.setVariable("searchForm", searchForm);
        return webContext;
	}


	/**
	 * 激活发送邮件
	 * @param pk
	 * @param lognUser
	 * @throws Exception 
	 */
	public void sendActiveEmail(Long pk, User lognUser) throws Exception {
		Customer customer = customerRepository.findOne(pk);
		
		if(customer!=null){
			UserCompany userCompany = userCompanyRepository.findOne(lognUser.getUserCompany().getId());
			sendMail.init(userCompany, customer);
			
			boolean sendRet = sendMail.send(customer.getCompanyName()  + " From Fronter Trading ", "Your Fronter Trading account has actived. ");
			if(!sendRet){
				logger.error("customer register email sending faild");
			}
		}
		
	}

	/**
	 * 根据名称查找
	 * @param q
	 * @param customertypeCustomer
	 * @param pageable
	 * @return
	 */
	public Page<Customer> findByCompanyName(String q, String customertype, Pageable pageable) {
		return customerRepository.findByCompanyNameContainingAndCustomerType(q, customertype, pageable);
	}


	/**
	 * 新增注册用户
	 * @param customerView
	 * @return
	 */
	@Transactional(rollbackOn=Exception.class)
	public Customer save(CustomerView customerView) {
		
		Customer customer  = customerRepository.findOneByEmail(customerView.getCustomer().getEmail());
		
		if(customer == null){
			customer = customerView.getCustomer();
			
			customer.setPassword(Md5.getMd5String(customerView.getCustomer().getPassword()));
			customer.setCreateTime(Calendar.getInstance().getTime());
			customer.setModifyTime(Calendar.getInstance().getTime());
			customer.setStatus(CustomerRepository.status_active);
			customer.setBalance(0D);
			customer.setCustomerType(CustomerRepository.customerType_customer);
			customer.setName(customer.getCompanyName());
			customer.setBranch(customerView.getCustomer().getBranch());
			customerRepository.save(customer);
		}
		return customer;
	}
	
	/**
	 * 
	 * @param customer
	 * @return Customer
	 */
	public void editSave(Customer customer){
		customerRepository.save(customer);
	}
	/**
	 * 删除customer
	 * @param customerId
	 */
	@Transactional(rollbackOn = Exception.class)
	public void delCustomer(Long customerId) {
		customerRepository.delete(customerId);
		
	}


	/**
	 * 更新密码
	 * @param id
	 * @param prePassword
	 * @param newPassword
	 * @return
	 */
	public boolean updatePassword(Long id, String prePassword, String newPassword) {
		boolean ret = false;
		Customer customer = customerRepository.findOne(id);
		if(customer!=null){
			if(customer.getPassword().equals(Md5.getMd5String(prePassword))){
				customer.setPassword(Md5.getMd5String(newPassword));
				customerRepository.save(customer);
				ret = true;
			}
		}
		return ret;
	}
	/**
	 * admin 新增注册用户
	 * @param customerView
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackOn=Exception.class)
	public Customer save(Customer customer) {
		Customer customerDb = customerRepository.findOneByEmail(customer.getCompanyEmail());
		
		if(customerDb==null){
			customer.setPassword(Md5.getMd5String(customer.getPassword()));
			customer.setCreateTime(Calendar.getInstance().getTime());
			customer.setModifyTime(Calendar.getInstance().getTime());
			customer.setDiscount(1D);
			customerRepository.save(customer);
		}
		
		return customer;
	}
}
