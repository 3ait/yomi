package com.datas.easyorder.db.entity;
// Generated 2018-9-27 12:33:29 by Hibernate Tools 4.3.1.Final

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
 * Customer generated by hbm2java
 */
@Entity
@Table(name = "customer", catalog = "yomi")
public class Customer implements java.io.Serializable {

	private Long id;
	@JsonIgnore
	private Branch branch;
	@JsonIgnore
	private CustomerFromAddress customerFromAddress;
	@JsonIgnore
	private CustomerToAddress customerToAddress;
	@JsonIgnore
	private RankCustomer rankCustomer;
	private String email;
	private String name;
	private String companyEmail;
	private String phone;
	private String password;
	private byte status;
	private double discount;
	private String logoSrc;
	private Date createTime;
	private Date modifyTime;
	private String code;
	private String gstNo;
	private String website;
	private String tel;
	private String fax;
	private String address;
	private String mobile;
	private String bankAccount;
	private String companyName;
	private Double balance;
	private String shippingAddress;
	private String identity;
	private String customerType;
	private String memo;
	private Integer memberPoint;
	private Date memberPointExpiredDate;
	@JsonIgnore
	private Set<Coupon> coupons = new HashSet<Coupon>(0);
	@JsonIgnore
	private Set<CustomerToAddress> customerToAddresses = new HashSet<CustomerToAddress>(0);
	@JsonIgnore
	private Set<CustomerFromAddress> customerFromAddresses = new HashSet<CustomerFromAddress>(0);
	@JsonIgnore
	private Set<Order> ordersForSalesId = new HashSet<Order>(0);
	@JsonIgnore
	private Set<CustomerCommission> customerCommissions = new HashSet<CustomerCommission>(0);
	@JsonIgnore
	private Set<Order> ordersForCustomerId = new HashSet<Order>(0);
	@JsonIgnore
	private Set<CouponCustomer> couponCustomers = new HashSet<CouponCustomer>(0);
	@JsonIgnore
	private Set<CustomerPaymentHistory> customerPaymentHistories = new HashSet<CustomerPaymentHistory>(0);

	public Customer() {
	}

	public Customer(String phone, String password, byte status, double discount) {
		this.phone = phone;
		this.password = password;
		this.status = status;
		this.discount = discount;
	}

	public Customer(Branch branch, CustomerFromAddress customerFromAddress, CustomerToAddress customerToAddress,
			RankCustomer rankCustomer, String email, String name, String companyEmail, String phone, String password,
			byte status, double discount, String logoSrc, Date createTime, Date modifyTime, String code, String gstNo,
			String website, String tel, String fax, String address, String mobile, String bankAccount,
			String companyName, Double balance, String shippingAddress, String identity, String customerType,
			String memo, Integer memberPoint, Date memberPointExpiredDate, Set<Coupon> coupons,
			Set<CustomerToAddress> customerToAddresses, Set<CustomerFromAddress> customerFromAddresses,
			Set<Order> ordersForSalesId, Set<CustomerCommission> customerCommissions, Set<Order> ordersForCustomerId,
			Set<CouponCustomer> couponCustomers, Set<CustomerPaymentHistory> customerPaymentHistories) {
		this.branch = branch;
		this.customerFromAddress = customerFromAddress;
		this.customerToAddress = customerToAddress;
		this.rankCustomer = rankCustomer;
		this.email = email;
		this.name = name;
		this.companyEmail = companyEmail;
		this.phone = phone;
		this.password = password;
		this.status = status;
		this.discount = discount;
		this.logoSrc = logoSrc;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.code = code;
		this.gstNo = gstNo;
		this.website = website;
		this.tel = tel;
		this.fax = fax;
		this.address = address;
		this.mobile = mobile;
		this.bankAccount = bankAccount;
		this.companyName = companyName;
		this.balance = balance;
		this.shippingAddress = shippingAddress;
		this.identity = identity;
		this.customerType = customerType;
		this.memo = memo;
		this.memberPoint = memberPoint;
		this.memberPointExpiredDate = memberPointExpiredDate;
		this.coupons = coupons;
		this.customerToAddresses = customerToAddresses;
		this.customerFromAddresses = customerFromAddresses;
		this.ordersForSalesId = ordersForSalesId;
		this.customerCommissions = customerCommissions;
		this.ordersForCustomerId = ordersForCustomerId;
		this.couponCustomers = couponCustomers;
		this.customerPaymentHistories = customerPaymentHistories;
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
	@JoinColumn(name = "default_from_address_id")
	public CustomerFromAddress getCustomerFromAddress() {
		return this.customerFromAddress;
	}

	public void setCustomerFromAddress(CustomerFromAddress customerFromAddress) {
		this.customerFromAddress = customerFromAddress;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "default_to_address_id")
	public CustomerToAddress getCustomerToAddress() {
		return this.customerToAddress;
	}

	public void setCustomerToAddress(CustomerToAddress customerToAddress) {
		this.customerToAddress = customerToAddress;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rank_customer_id")
	public RankCustomer getRankCustomer() {
		return this.rankCustomer;
	}

	public void setRankCustomer(RankCustomer rankCustomer) {
		this.rankCustomer = rankCustomer;
	}

	@Column(name = "email", length = 128)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "name", length = 128)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "company_email")
	public String getCompanyEmail() {
		return this.companyEmail;
	}

	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	@Column(name = "phone", nullable = false, length = 128)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "password", nullable = false, length = 128)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "status", nullable = false)
	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	@Column(name = "discount", nullable = false, precision = 22, scale = 0)
	public double getDiscount() {
		return this.discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Column(name = "logo_src", length = 1024)
	public String getLogoSrc() {
		return this.logoSrc;
	}

	public void setLogoSrc(String logoSrc) {
		this.logoSrc = logoSrc;
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

	@Column(name = "code")
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "gst_no")
	public String getGstNo() {
		return this.gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	@Column(name = "website")
	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Column(name = "tel")
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "fax")
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "address", length = 1024)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "mobile")
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "bank_account")
	public String getBankAccount() {
		return this.bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	@Column(name = "company_name")
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "balance", precision = 255)
	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Column(name = "shipping_address", length = 1024)
	public String getShippingAddress() {
		return this.shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	@Column(name = "identity")
	public String getIdentity() {
		return this.identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	@Column(name = "customer_type", length = 8)
	public String getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	@Column(name = "memo", length = 1024)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "member_point")
	public Integer getMemberPoint() {
		return this.memberPoint;
	}

	public void setMemberPoint(Integer memberPoint) {
		this.memberPoint = memberPoint;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "member_point_expired_date", length = 19)
	public Date getMemberPointExpiredDate() {
		return this.memberPointExpiredDate;
	}

	public void setMemberPointExpiredDate(Date memberPointExpiredDate) {
		this.memberPointExpiredDate = memberPointExpiredDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<Coupon> getCoupons() {
		return this.coupons;
	}

	public void setCoupons(Set<Coupon> coupons) {
		this.coupons = coupons;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<CustomerToAddress> getCustomerToAddresses() {
		return this.customerToAddresses;
	}

	public void setCustomerToAddresses(Set<CustomerToAddress> customerToAddresses) {
		this.customerToAddresses = customerToAddresses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<CustomerFromAddress> getCustomerFromAddresses() {
		return this.customerFromAddresses;
	}

	public void setCustomerFromAddresses(Set<CustomerFromAddress> customerFromAddresses) {
		this.customerFromAddresses = customerFromAddresses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerBySalesId")
	public Set<Order> getOrdersForSalesId() {
		return this.ordersForSalesId;
	}

	public void setOrdersForSalesId(Set<Order> ordersForSalesId) {
		this.ordersForSalesId = ordersForSalesId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<CustomerCommission> getCustomerCommissions() {
		return this.customerCommissions;
	}

	public void setCustomerCommissions(Set<CustomerCommission> customerCommissions) {
		this.customerCommissions = customerCommissions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customerByCustomerId")
	public Set<Order> getOrdersForCustomerId() {
		return this.ordersForCustomerId;
	}

	public void setOrdersForCustomerId(Set<Order> ordersForCustomerId) {
		this.ordersForCustomerId = ordersForCustomerId;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<CouponCustomer> getCouponCustomers() {
		return this.couponCustomers;
	}

	public void setCouponCustomers(Set<CouponCustomer> couponCustomers) {
		this.couponCustomers = couponCustomers;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<CustomerPaymentHistory> getCustomerPaymentHistories() {
		return this.customerPaymentHistories;
	}

	public void setCustomerPaymentHistories(Set<CustomerPaymentHistory> customerPaymentHistories) {
		this.customerPaymentHistories = customerPaymentHistories;
	}

}
