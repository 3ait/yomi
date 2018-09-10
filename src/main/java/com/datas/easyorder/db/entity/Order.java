package com.datas.easyorder.db.entity;
// Generated 2018-8-23 17:08:24 by Hibernate Tools 4.3.1.Final

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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Order generated by hbm2java
 */
@Entity
@Table(name = "order", catalog = "yomi")
public class Order implements java.io.Serializable {

	private Long id;
	@JsonIgnore
	private Branch branch;
	@JsonIgnore
	private Customer customerBySalesId;
	@JsonIgnore
	private Customer customerByCustomerId;
	@JsonIgnore
	private User user;
	private String trackingNum;
	private String payType;
	private Integer isPaid;
	private double totalProductPrice;
	private Double totalFreight;
	private Integer status;
	private Date createTime;
	private Date modifyTime;
	private String fromName;
	private String fromPhone;
	private String fromAddress;
	private String toCustomerName;
	private String toCustomerCompanyName;
	private String toTel;
	private String toPhone;
	private String toAddress;
	private String toShippingAddress;
	private String toProvince;
	private String toCity;
	private String toDistrict;
	private String toEmail;
	private String customerMsg;
	private String adminMsg;
	private Integer settled;
	private Byte invoiced;
	private Double salesCommissionPercentage;
	private Integer salesCommissionStatus;
	private Double discount;
	@JsonIgnore
	private Set<CustomerPaymentHistory> customerPaymentHistories = new HashSet<CustomerPaymentHistory>(0);
	@JsonIgnore
	private Set<OrderItem> orderItems = new HashSet<OrderItem>(0);
	@JsonIgnore
	private Set<CouponOrder> couponOrders = new HashSet<CouponOrder>(0);
	@JsonIgnore
	private Set<Invoice> invoices = new HashSet<Invoice>(0);

	public Order() {
	}

	public Order(Customer customerByCustomerId, double totalProductPrice) {
		this.customerByCustomerId = customerByCustomerId;
		this.totalProductPrice = totalProductPrice;
	}

	public Order(Branch branch, Customer customerBySalesId, Customer customerByCustomerId, User user,
			String trackingNum, String payType, Integer isPaid, double totalProductPrice, Double totalFreight,
			Integer status, Date createTime, Date modifyTime, String fromName, String fromPhone, String fromAddress,
			String toCustomerName, String toCustomerCompanyName, String toTel, String toPhone, String toAddress,
			String toShippingAddress, String toProvince, String toCity, String toDistrict, String toEmail,
			String customerMsg, String adminMsg, Integer settled, Byte invoiced, Double salesCommissionPercentage,
			Integer salesCommissionStatus, Double discount, Set<CustomerPaymentHistory> customerPaymentHistories,
			Set<OrderItem> orderItems, Set<CouponOrder> couponOrders, Set<Invoice> invoices) {
		this.branch = branch;
		this.customerBySalesId = customerBySalesId;
		this.customerByCustomerId = customerByCustomerId;
		this.user = user;
		this.trackingNum = trackingNum;
		this.payType = payType;
		this.isPaid = isPaid;
		this.totalProductPrice = totalProductPrice;
		this.totalFreight = totalFreight;
		this.status = status;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.fromName = fromName;
		this.fromPhone = fromPhone;
		this.fromAddress = fromAddress;
		this.toCustomerName = toCustomerName;
		this.toCustomerCompanyName = toCustomerCompanyName;
		this.toTel = toTel;
		this.toPhone = toPhone;
		this.toAddress = toAddress;
		this.toShippingAddress = toShippingAddress;
		this.toProvince = toProvince;
		this.toCity = toCity;
		this.toDistrict = toDistrict;
		this.toEmail = toEmail;
		this.customerMsg = customerMsg;
		this.adminMsg = adminMsg;
		this.settled = settled;
		this.invoiced = invoiced;
		this.salesCommissionPercentage = salesCommissionPercentage;
		this.salesCommissionStatus = salesCommissionStatus;
		this.discount = discount;
		this.customerPaymentHistories = customerPaymentHistories;
		this.orderItems = orderItems;
		this.couponOrders = couponOrders;
		this.invoices = invoices;
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
	@JoinColumn(name = "branch_id")
	public Branch getBranch() {
		return this.branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sales_id")
	public Customer getCustomerBySalesId() {
		return this.customerBySalesId;
	}

	public void setCustomerBySalesId(Customer customerBySalesId) {
		this.customerBySalesId = customerBySalesId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	public Customer getCustomerByCustomerId() {
		return this.customerByCustomerId;
	}

	public void setCustomerByCustomerId(Customer customerByCustomerId) {
		this.customerByCustomerId = customerByCustomerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "tracking_num", length = 128)
	public String getTrackingNum() {
		return this.trackingNum;
	}

	public void setTrackingNum(String trackingNum) {
		this.trackingNum = trackingNum;
	}

	@Column(name = "pay_type", length = 45)
	public String getPayType() {
		return this.payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	@Column(name = "is_paid")
	public Integer getIsPaid() {
		return this.isPaid;
	}

	public void setIsPaid(Integer isPaid) {
		this.isPaid = isPaid;
	}

	@Column(name = "total_product_price", nullable = false, precision = 10)
	public double getTotalProductPrice() {
		return this.totalProductPrice;
	}

	public void setTotalProductPrice(double totalProductPrice) {
		this.totalProductPrice = totalProductPrice;
	}

	@Column(name = "total_freight", precision = 10)
	public Double getTotalFreight() {
		return this.totalFreight;
	}

	public void setTotalFreight(Double totalFreight) {
		this.totalFreight = totalFreight;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	@Column(name = "from_name", length = 1024)
	public String getFromName() {
		return this.fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	@Column(name = "from_phone", length = 1024)
	public String getFromPhone() {
		return this.fromPhone;
	}

	public void setFromPhone(String fromPhone) {
		this.fromPhone = fromPhone;
	}

	@Column(name = "from_address", length = 1024)
	public String getFromAddress() {
		return this.fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	@Column(name = "to_customer_name", length = 1024)
	public String getToCustomerName() {
		return this.toCustomerName;
	}

	public void setToCustomerName(String toCustomerName) {
		this.toCustomerName = toCustomerName;
	}

	@Column(name = "to_customer_company_name", length = 1024)
	public String getToCustomerCompanyName() {
		return this.toCustomerCompanyName;
	}

	public void setToCustomerCompanyName(String toCustomerCompanyName) {
		this.toCustomerCompanyName = toCustomerCompanyName;
	}

	@Column(name = "to_tel")
	public String getToTel() {
		return this.toTel;
	}

	public void setToTel(String toTel) {
		this.toTel = toTel;
	}

	@Column(name = "to_phone")
	public String getToPhone() {
		return this.toPhone;
	}

	public void setToPhone(String toPhone) {
		this.toPhone = toPhone;
	}

	@Column(name = "to_address", length = 1024)
	public String getToAddress() {
		return this.toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	@Column(name = "to_shipping_address", length = 1024)
	public String getToShippingAddress() {
		return this.toShippingAddress;
	}

	public void setToShippingAddress(String toShippingAddress) {
		this.toShippingAddress = toShippingAddress;
	}

	@Column(name = "to_province")
	public String getToProvince() {
		return this.toProvince;
	}

	public void setToProvince(String toProvince) {
		this.toProvince = toProvince;
	}

	@Column(name = "to_city")
	public String getToCity() {
		return this.toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	@Column(name = "to_district")
	public String getToDistrict() {
		return this.toDistrict;
	}

	public void setToDistrict(String toDistrict) {
		this.toDistrict = toDistrict;
	}

	@Column(name = "to_email")
	public String getToEmail() {
		return this.toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	@Column(name = "customer_msg", length = 1024)
	public String getCustomerMsg() {
		return this.customerMsg;
	}

	public void setCustomerMsg(String customerMsg) {
		this.customerMsg = customerMsg;
	}

	@Column(name = "admin_msg", length = 1024)
	public String getAdminMsg() {
		return this.adminMsg;
	}

	public void setAdminMsg(String adminMsg) {
		this.adminMsg = adminMsg;
	}

	@Column(name = "settled")
	public Integer getSettled() {
		return this.settled;
	}

	public void setSettled(Integer settled) {
		this.settled = settled;
	}

	@Column(name = "invoiced")
	public Byte getInvoiced() {
		return this.invoiced;
	}

	public void setInvoiced(Byte invoiced) {
		this.invoiced = invoiced;
	}

	@Column(name = "sales_commission_percentage", precision = 10)
	public Double getSalesCommissionPercentage() {
		return this.salesCommissionPercentage;
	}

	public void setSalesCommissionPercentage(Double salesCommissionPercentage) {
		this.salesCommissionPercentage = salesCommissionPercentage;
	}

	@Column(name = "sales_commission_status")
	public Integer getSalesCommissionStatus() {
		return this.salesCommissionStatus;
	}

	public void setSalesCommissionStatus(Integer salesCommissionStatus) {
		this.salesCommissionStatus = salesCommissionStatus;
	}

	@Column(name = "discount", precision = 10)
	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	public Set<CustomerPaymentHistory> getCustomerPaymentHistories() {
		return this.customerPaymentHistories;
	}

	public void setCustomerPaymentHistories(Set<CustomerPaymentHistory> customerPaymentHistories) {
		this.customerPaymentHistories = customerPaymentHistories;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	public Set<OrderItem> getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	public Set<CouponOrder> getCouponOrders() {
		return this.couponOrders;
	}

	public void setCouponOrders(Set<CouponOrder> couponOrders) {
		this.couponOrders = couponOrders;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	public Set<Invoice> getInvoices() {
		return this.invoices;
	}

	public void setInvoices(Set<Invoice> invoices) {
		this.invoices = invoices;
	}

}
