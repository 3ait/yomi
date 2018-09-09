package com.datas.easyorder.controller.administrator.invoice;

import java.util.List;

import com.datas.easyorder.db.entity.Customer;
import com.datas.easyorder.db.entity.Invoice;
import com.datas.easyorder.db.entity.InvoiceItem;
import com.datas.easyorder.db.entity.UserCompany;

public class InvoiceView {

	private Invoice invoice;
	
	private List<InvoiceItem> invoiceItemList;
	
	private Customer customer;
	
	private UserCompany userCompany;

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public List<InvoiceItem> getInvoiceItemList() {
		return invoiceItemList;
	}

	public void setInvoiceItemList(List<InvoiceItem> invoiceItemList) {
		this.invoiceItemList = invoiceItemList;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public UserCompany getUserCompany() {
		return userCompany;
	}

	public void setUserCompany(UserCompany userCompany) {
		this.userCompany = userCompany;
	}

	
	
}
