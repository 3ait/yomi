package com.datas.easyorder.controller.administrator.customer;

import java.util.Date;

import com.datas.easyorder.controller.administrator.invoice.InvoiceView;

public class Transfer {

	private Date date;
	private String details;
	private Double invoiceCost;
	
	private InvoiceView invoiceView;
	
	private Double deposits;
	private Double balance;
	private String memo;
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Double getInvoiceCost() {
		return invoiceCost;
	}
	public void setInvoiceCost(Double invoiceCost) {
		this.invoiceCost = invoiceCost;
	}
	public Double getDeposits() {
		return deposits;
	}
	public void setDeposits(Double deposits) {
		this.deposits = deposits;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public InvoiceView getInvoiceView() {
		return invoiceView;
	}
	public void setInvoiceView(InvoiceView invoiceView) {
		this.invoiceView = invoiceView;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
	
	
}
