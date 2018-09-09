package com.datas.easyorder.db.entity;
// Generated 2018-3-7 11:20:55 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Invoice generated by hbm2java
 */
@Entity
@Table(name = "invoice", catalog = "ultimatelife")
public class Invoice implements java.io.Serializable {

	private Long id;
	private Order order;
	private User user;
	private UserCompany userCompany;
	private String aliasId;
	private Long customerId;
	private String customerEmail;
	private String customerCompanyName;
	private String customerCompanyAddress;
	private String customerCompanyPhone;
	private String customerCompanyFax;
	private String customerCompanyMobile;
	private String userCompanyName;
	private String userCompanyAddress;
	private String userCompanyWebsite;
	private String userCompanyEmail;
	private String userCompanyTel;
	private String userCompanyFax;
	private String userCompanyMobile;
	private String userCompanyBankAccount;
	private String userCompanyGstNo;
	private Double subtotal;
	private Double gst;
	private Double total;
	private Double balance;
	private Byte paid;
	private Byte status;
	private String adminInfo;
	private Date createTime;
	private Date modifyTime;
	private String invoiceTermsConditions;
	private Double discount;
	private String emailInfo;
	private Double freight;
	private Set<CustomerPaymentHistory> customerPaymentHistories = new HashSet<CustomerPaymentHistory>(0);
	private Set<InvoiceItem> invoiceItems = new HashSet<InvoiceItem>(0);

	public Invoice() {
	}

	public Invoice(String aliasId) {
		this.aliasId = aliasId;
	}

	public Invoice(Order order, User user, UserCompany userCompany, String aliasId, Long customerId,
			String customerEmail, String customerCompanyName, String customerCompanyAddress,
			String customerCompanyPhone, String customerCompanyFax, String customerCompanyMobile,
			String userCompanyName, String userCompanyAddress, String userCompanyWebsite, String userCompanyEmail,
			String userCompanyTel, String userCompanyFax, String userCompanyMobile, String userCompanyBankAccount,
			String userCompanyGstNo, Double subtotal, Double gst, Double total, Double balance, Byte paid, Byte status,
			String adminInfo, Date createTime, Date modifyTime, String invoiceTermsConditions, Double discount,
			String emailInfo, Double freight, Set<CustomerPaymentHistory> customerPaymentHistories,
			Set<InvoiceItem> invoiceItems) {
		this.order = order;
		this.user = user;
		this.userCompany = userCompany;
		this.aliasId = aliasId;
		this.customerId = customerId;
		this.customerEmail = customerEmail;
		this.customerCompanyName = customerCompanyName;
		this.customerCompanyAddress = customerCompanyAddress;
		this.customerCompanyPhone = customerCompanyPhone;
		this.customerCompanyFax = customerCompanyFax;
		this.customerCompanyMobile = customerCompanyMobile;
		this.userCompanyName = userCompanyName;
		this.userCompanyAddress = userCompanyAddress;
		this.userCompanyWebsite = userCompanyWebsite;
		this.userCompanyEmail = userCompanyEmail;
		this.userCompanyTel = userCompanyTel;
		this.userCompanyFax = userCompanyFax;
		this.userCompanyMobile = userCompanyMobile;
		this.userCompanyBankAccount = userCompanyBankAccount;
		this.userCompanyGstNo = userCompanyGstNo;
		this.subtotal = subtotal;
		this.gst = gst;
		this.total = total;
		this.balance = balance;
		this.paid = paid;
		this.status = status;
		this.adminInfo = adminInfo;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.invoiceTermsConditions = invoiceTermsConditions;
		this.discount = discount;
		this.emailInfo = emailInfo;
		this.freight = freight;
		this.customerPaymentHistories = customerPaymentHistories;
		this.invoiceItems = invoiceItems;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_company_id")
	public UserCompany getUserCompany() {
		return this.userCompany;
	}

	public void setUserCompany(UserCompany userCompany) {
		this.userCompany = userCompany;
	}

	@Column(name = "alias_id", nullable = false, length = 11)
	public String getAliasId() {
		return this.aliasId;
	}

	public void setAliasId(String aliasId) {
		this.aliasId = aliasId;
	}

	@Column(name = "customer_id")
	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Column(name = "customer_email")
	public String getCustomerEmail() {
		return this.customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	@Column(name = "customer_company_name", length = 1024)
	public String getCustomerCompanyName() {
		return this.customerCompanyName;
	}

	public void setCustomerCompanyName(String customerCompanyName) {
		this.customerCompanyName = customerCompanyName;
	}

	@Column(name = "customer_company_address", length = 1024)
	public String getCustomerCompanyAddress() {
		return this.customerCompanyAddress;
	}

	public void setCustomerCompanyAddress(String customerCompanyAddress) {
		this.customerCompanyAddress = customerCompanyAddress;
	}

	@Column(name = "customer_company_phone")
	public String getCustomerCompanyPhone() {
		return this.customerCompanyPhone;
	}

	public void setCustomerCompanyPhone(String customerCompanyPhone) {
		this.customerCompanyPhone = customerCompanyPhone;
	}

	@Column(name = "customer_company_fax")
	public String getCustomerCompanyFax() {
		return this.customerCompanyFax;
	}

	public void setCustomerCompanyFax(String customerCompanyFax) {
		this.customerCompanyFax = customerCompanyFax;
	}

	@Column(name = "customer_company_mobile")
	public String getCustomerCompanyMobile() {
		return this.customerCompanyMobile;
	}

	public void setCustomerCompanyMobile(String customerCompanyMobile) {
		this.customerCompanyMobile = customerCompanyMobile;
	}

	@Column(name = "user_company_name")
	public String getUserCompanyName() {
		return this.userCompanyName;
	}

	public void setUserCompanyName(String userCompanyName) {
		this.userCompanyName = userCompanyName;
	}

	@Column(name = "user_company_address", length = 1024)
	public String getUserCompanyAddress() {
		return this.userCompanyAddress;
	}

	public void setUserCompanyAddress(String userCompanyAddress) {
		this.userCompanyAddress = userCompanyAddress;
	}

	@Column(name = "user_company_website")
	public String getUserCompanyWebsite() {
		return this.userCompanyWebsite;
	}

	public void setUserCompanyWebsite(String userCompanyWebsite) {
		this.userCompanyWebsite = userCompanyWebsite;
	}

	@Column(name = "user_company_email")
	public String getUserCompanyEmail() {
		return this.userCompanyEmail;
	}

	public void setUserCompanyEmail(String userCompanyEmail) {
		this.userCompanyEmail = userCompanyEmail;
	}

	@Column(name = "user_company_tel")
	public String getUserCompanyTel() {
		return this.userCompanyTel;
	}

	public void setUserCompanyTel(String userCompanyTel) {
		this.userCompanyTel = userCompanyTel;
	}

	@Column(name = "user_company_fax")
	public String getUserCompanyFax() {
		return this.userCompanyFax;
	}

	public void setUserCompanyFax(String userCompanyFax) {
		this.userCompanyFax = userCompanyFax;
	}

	@Column(name = "user_company_mobile")
	public String getUserCompanyMobile() {
		return this.userCompanyMobile;
	}

	public void setUserCompanyMobile(String userCompanyMobile) {
		this.userCompanyMobile = userCompanyMobile;
	}

	@Column(name = "user_company_bank_account")
	public String getUserCompanyBankAccount() {
		return this.userCompanyBankAccount;
	}

	public void setUserCompanyBankAccount(String userCompanyBankAccount) {
		this.userCompanyBankAccount = userCompanyBankAccount;
	}

	@Column(name = "user_company_gst_no")
	public String getUserCompanyGstNo() {
		return this.userCompanyGstNo;
	}

	public void setUserCompanyGstNo(String userCompanyGstNo) {
		this.userCompanyGstNo = userCompanyGstNo;
	}

	@Column(name = "subtotal", precision = 10)
	public Double getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	@Column(name = "gst", precision = 10)
	public Double getGst() {
		return this.gst;
	}

	public void setGst(Double gst) {
		this.gst = gst;
	}

	@Column(name = "total", precision = 10)
	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Column(name = "balance", precision = 10)
	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Column(name = "paid")
	public Byte getPaid() {
		return this.paid;
	}

	public void setPaid(Byte paid) {
		this.paid = paid;
	}

	@Column(name = "status")
	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	@Column(name = "admin_info", length = 65535)
	public String getAdminInfo() {
		return this.adminInfo;
	}

	public void setAdminInfo(String adminInfo) {
		this.adminInfo = adminInfo;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_time", length = 19)
	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@Column(name = "invoice_terms_conditions")
	public String getInvoiceTermsConditions() {
		return this.invoiceTermsConditions;
	}

	public void setInvoiceTermsConditions(String invoiceTermsConditions) {
		this.invoiceTermsConditions = invoiceTermsConditions;
	}

	@Column(name = "discount", precision = 255, scale = 0)
	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	@Column(name = "email_info", length = 65535)
	public String getEmailInfo() {
		return this.emailInfo;
	}

	public void setEmailInfo(String emailInfo) {
		this.emailInfo = emailInfo;
	}

	@Column(name = "freight", precision = 22, scale = 0)
	public Double getFreight() {
		return this.freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "invoice")
	public Set<CustomerPaymentHistory> getCustomerPaymentHistories() {
		return this.customerPaymentHistories;
	}

	public void setCustomerPaymentHistories(Set<CustomerPaymentHistory> customerPaymentHistories) {
		this.customerPaymentHistories = customerPaymentHistories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "invoice")
	public Set<InvoiceItem> getInvoiceItems() {
		return this.invoiceItems;
	}

	public void setInvoiceItems(Set<InvoiceItem> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

}
