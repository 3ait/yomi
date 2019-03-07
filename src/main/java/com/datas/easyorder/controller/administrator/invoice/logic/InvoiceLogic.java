package com.datas.easyorder.controller.administrator.invoice.logic;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import com.datas.easyorder.controller.BaseLogic;
import com.datas.easyorder.controller.administrator.invoice.InvoiceView;
import com.datas.easyorder.db.dao.CustomerRepository;
import com.datas.easyorder.db.dao.InvoiceRepository;
import com.datas.easyorder.db.dao.InvoiceSpecifications;
import com.datas.easyorder.db.dao.UserCompanyRepository;
import com.datas.easyorder.db.entity.Invoice;
import com.datas.easyorder.db.entity.User;
import com.datas.easyorder.db.entity.UserCompany;
import com.datas.utils.SearchForm;
import com.plugin.mail.logic.SendMail;
import com.plugin.pdf.HtmlPDF;
import com.plugin.utils.DateHelper;

@Component
public class InvoiceLogic extends BaseLogic<Invoice>{

	@Autowired
	private InvoiceRepository invoiceRepository;
	@Autowired
	HtmlPDF htmlPDF;
	@Autowired
	TemplateEngine templateEngine;
	@Autowired
	SendMail sendMail;
	@Autowired
	UserCompanyRepository userCompanyRepository;
	@Autowired
	CustomerRepository customerRepository;
	
	
	private String relativePathForInvoice = "/invoice";
	private String pdfTempatePath = "administrator/invoice/pdf/template";
	/**
	 * 
	 * @param searchForm
	 * @param pageable
	 * @return
	 */
	public Page<Invoice> getList(SearchForm searchForm, Pageable pageable) {
		return invoiceRepository.findAll(InvoiceSpecifications.getSearchSpecification(searchForm),pageable);
	}



	@Override
	public CrudRepository<Invoice, Long> getRepository() {
		return invoiceRepository;
	}
	
	
	/**
	 * 更新属性
	 * 
	 * @param cateoryId
	 * @param atrributeName
	 * @param attributeValue
	 */
	@Transactional(rollbackOn = Exception.class)
	public void update(Long id, String atrributeName, String attributeValue,User user) {

		Invoice invoice = invoiceRepository.findOne(id);
		try {
			Field field = Invoice.class.getDeclaredField(atrributeName);
			field.setAccessible(true);
			if(field.getType()  ==  String.class){
				field.set(invoice, attributeValue);
			}else if(field.getType().equals(Double.class) || field.getType().equals(double.class)){
				field.set(invoice, Double.valueOf(attributeValue));
			}else if(field.getType().equals(Integer.class) || field.getType().equals(int.class)){
				field.set(invoice, Integer.valueOf(attributeValue));
			}else if(field.getType().equals(Byte.class) || field.getType().equals(byte.class)){
				field.set(invoice, Byte.valueOf(attributeValue));
			}else if(field.getType().equals(java.util.Date.class)){
				field.set(invoice, DateHelper.parseYYYYMMDD(attributeValue));
			}
			
			invoice.setUser(user);
			invoiceRepository.save(invoice);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	/**
	 * 
	 * @param invoiceId
	 * @return InvoiceView
	 */
	@Transactional(rollbackOn = Exception.class)
	public InvoiceView getInvoiceById(long invoiceId,User user) {
		
		InvoiceView invoiceView = new InvoiceView();
		Invoice invoice = invoiceRepository.findOneByIdAndUserCompanyId(invoiceId,user.getUserCompany().getId());
		invoiceView.setInvoice(invoice);
		invoiceView.setInvoiceItemList(invoice.getInvoiceItems().stream().sorted((it1,it2) -> it1.getId().intValue()-it2.getId().intValue()).collect(Collectors.toList()));
		return invoiceView;
	}


	/**
	 * Email
	 * @param invoiceId
	 * @param user
	 * @return
	 * @throws IOException 
	 */
	@Transactional(rollbackOn = Exception.class)
	public String email(long invoiceId, User user,HttpServletRequest request,HttpServletResponse response){
		
		String ret = "true";
		try {
			
			//生成Invoice
			InvoiceView invoiceView = getInvoiceById(invoiceId,user);
			
			String html = templateEngine.process(pdfTempatePath, createWebContext(invoiceId, user, request, response));
			
			if(!new File( absoultPath+relativePathForInvoice).exists()){
				new File( absoultPath+relativePathForInvoice).mkdirs();
			}
			String path = absoultPath+relativePathForInvoice+ "/"+invoiceView.getInvoice().getAliasId()+".pdf";
			
			Path invoicePath = Paths.get(path);
			OutputStream os =  Files.newOutputStream(invoicePath, StandardOpenOption.CREATE);
			htmlPDF.pdfOutputStream(html.toString(),os);
			os.flush();
			os.close();
			
			//发送邮件
			UserCompany userCompany = userCompanyRepository.findOne(user.getUserCompany().getId());
			sendMail.init(userCompany, invoiceView.getInvoice().getCustomerEmail());
			boolean sendRet = sendMail.send(userCompany.getSendingMailSubject() + " #Invoice: "+ invoiceView.getInvoice().getAliasId(), invoiceView.getInvoice().getEmailInfo() + "<br/>" + userCompany.getSendingMailTemplate(), new File(path));
			
			//更新Invoice status mail sent
			if(sendRet){
				invoiceView.getInvoice().setStatus((byte)1);
				invoiceRepository.save(invoiceView.getInvoice());
			}
			
		} catch (Exception e) {
			ret = e.getMessage();
			e.printStackTrace();
		}
        
		return ret;
	}


	@Transactional(rollbackOn = Exception.class)
	public void pdfOutputStream(long invoiceId, User user, HttpServletRequest request,HttpServletResponse response) throws IOException {
		
        String html = templateEngine.process(pdfTempatePath, createWebContext(invoiceId, user, request, response));
        htmlPDF.pdfOutputStream(html.toString(),response.getOutputStream());
	}

	/**
	 * 创建webContext
	 * @param invoiceId
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	@Transactional(rollbackOn = Exception.class)
	private WebContext createWebContext(long invoiceId, User user,HttpServletRequest request,HttpServletResponse response){
		InvoiceView invoiceView = getInvoiceById(invoiceId,user);
		WebContext webContext = new WebContext(request, response, request.getServletContext());
		
		List<InvoiceView> list = new ArrayList<>();
		
		invoiceView.setCustomer(customerRepository.findOne(invoiceView.getInvoice().getCustomerId()));
		invoiceView.setUserCompany( userCompanyRepository.findOne(user.getUserCompany().getId()));
		list.add(invoiceView);
		webContext.setVariable("invoiceViewList", list);
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, 20);
		webContext.setVariable("dueDate", calendar.getTime());
		
        return webContext;
	}


	/**
	 * invoice 批量预览
	 * @param invoiceIds
	 * @param user
	 * @return List<InvoiceView>
	 */
	@Transactional(rollbackOn = Exception.class)
	public List<InvoiceView> invoicePreview(Long[] invoiceIds, User user) {
		
		List<InvoiceView> list = new ArrayList<>();
		
		for(Long invoiceId :invoiceIds){
			InvoiceView invoiceView = this.getInvoiceById(invoiceId,user);
			invoiceView.setCustomer( customerRepository.findOne(invoiceView.getInvoice().getCustomerId()));
			invoiceView.setUserCompany(userCompanyRepository.findOne(user.getUserCompany().getId()));
			list.add(invoiceView);
		}
		
		return list;
	}
	/**
	 * 批量发送Email
	 * @param invoiceIds
	 * @param lognUser
	 * @param request
	 * @param response
	 * @return
	 */
	public void emailBatch(Long[] invoiceIds, User lognUser, HttpServletRequest request,
			HttpServletResponse response) {
		for(Long id:invoiceIds){
			email(id, lognUser, request, response);
		}
	}
	
	
}
