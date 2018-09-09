package com.datas.easyorder.controller.administrator.payment.logic;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.datas.easyorder.controller.BaseLogic;
import com.datas.easyorder.db.dao.CustomerPaymentHistoryRepository;
import com.datas.easyorder.db.dao.CustomerPaymentHistorySpecifications;
import com.datas.easyorder.db.dao.CustomerRepository;
import com.datas.easyorder.db.dao.InvoiceRepository;
import com.datas.easyorder.db.entity.Customer;
import com.datas.easyorder.db.entity.CustomerPaymentHistory;
import com.datas.easyorder.db.entity.Invoice;
import com.datas.easyorder.db.entity.User;
import com.datas.utils.GuessingWord;
import com.datas.utils.SearchForm;

@Component
public class PaymentLogic extends BaseLogic<CustomerPaymentHistory> {

	@Autowired
	CustomerPaymentHistoryRepository customerPaymentHistoryRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	InvoiceRepository invoiceRepository;

	@Override
	public CrudRepository<CustomerPaymentHistory, Long> getRepository() {
		return customerPaymentHistoryRepository;
	}

	public Page<CustomerPaymentHistory> getList(SearchForm searchForm, Pageable pageable) {
		return customerPaymentHistoryRepository
				.findAll(CustomerPaymentHistorySpecifications.getSearchSpecification(searchForm), pageable);
	}

	@Override
	public List<GuessingWord> guessingWordList(String q, PageRequest pageRequest) {

		Page<Customer> list = customerRepository.findByCompanyNameContainingOrNameContaining(q, q, pageRequest);

		List<GuessingWord> guessingWordList = new ArrayList<>();
		list.getContent().forEach(customer -> {
			GuessingWord gw = new GuessingWord();
			gw.setWord(customer.getCompanyName());
			gw.setId(customer.getId());
			guessingWordList.add(gw);
		});

		return guessingWordList;
	}

	/**
	 * 根据Invoice 获取customer 公司信息
	 * 
	 * @param invoiceId
	 * @return JSONObject
	 */
	public JSONObject getCompanyInfoByInvoiceId(String invoiceId) {

		Invoice invoice = invoiceRepository.findOneByAliasId(invoiceId);
		JSONObject json = new JSONObject();
		if (invoice != null) {
			json.put("customerId", invoice.getCustomerId());
			json.put("customerCompanyName", invoice.getCustomerCompanyName());
			json.put("invoiceId", invoice.getId());
		}

		return json;
	}

	/**
	 * Save Payment & update customer balance & update invoice balance
	 * 
	 * @param customerPaymentHistory
	 * @param hiddenId
	 * @param user
	 */
	@Transactional(rollbackOn = Exception.class)
	public void savePayment(CustomerPaymentHistory customerPaymentHistory, Long CustomerId, User user) {
		customerPaymentHistory.setUser(user);
		Customer customer = customerRepository.findOne(CustomerId);
		customerPaymentHistory.setCustomer(customer);
		customerPaymentHistory.setModifyTime(Calendar.getInstance().getTime());
		customerPaymentHistory.setCreateTime(Calendar.getInstance().getTime());
		customerPaymentHistory.setUser(user);
		customerPaymentHistoryRepository.save(customerPaymentHistory);

		if (customerPaymentHistory.getInvoice() != null) {

			Invoice invoice = invoiceRepository.findOne(customerPaymentHistory.getInvoice().getId());

			if ((invoice.getBalance() - customerPaymentHistory.getPayment()) > 0) {
				/*
				 * {value: -2, text: '--Select--'}, {value: 1, text: 'Unpaid'},
				 * {value: 2, text: 'PaidInPart'}, {value: 3, text: 'Paid'},
				 * invoice.setPaid((byte)1); /* {value: -2, text: '--Select--'},
				 * {value: 0, text: 'New Invoice'}, {value: 1, text: 'Emailed'},
				 * {value: 2, text: 'Completed'}, {value: 3, text: 'Cancelled'},
				 */
				invoice.setStatus((byte) 2);
				invoice.setBalance(invoice.getBalance() - customerPaymentHistory.getPayment());
			} else {
				invoice.setPaid((byte) 3);
				invoice.setStatus((byte) 2);
				invoice.setBalance(0D);
			}
			invoice.setUser(user);
			invoice.setModifyTime(Calendar.getInstance().getTime());
			invoiceRepository.save(invoice);

		}
		customer.setBalance(customer.getBalance() - customerPaymentHistory.getPayment());
		customer.setModifyTime(Calendar.getInstance().getTime());
		customerRepository.save(customer);
	}

}
