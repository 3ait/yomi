package com.datas.easyorder.db.entity;
// Generated 9/05/2018 3:20:40 PM by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
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
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Coupon generated by hbm2java
 */
@Entity
@Table(name = "coupon", catalog = "yomi", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
public class Coupon implements java.io.Serializable {

	private Long id;
	@JsonIgnore
	private Customer customer;
	private String code;
	private Integer reg;
	private String type;
	private BigDecimal value;
	private Integer status;
	private Date createTime;
	private Date expiredTime;
	private String label;
	private String name;
	@JsonIgnore
	private Set<CouponOrder> couponOrders = new HashSet<CouponOrder>(0);
	@JsonIgnore
	private Set<CouponCustomer> couponCustomers = new HashSet<CouponCustomer>(0);

	public Coupon() {
	}

	public Coupon(String code) {
		this.code = code;
	}

	public Coupon(Customer customer, String code, Integer reg, String type, BigDecimal value, Integer status,
			Date createTime, Date expiredTime, String label, String name, Set<CouponOrder> couponOrders,
			Set<CouponCustomer> couponCustomers) {
		this.customer = customer;
		this.code = code;
		this.reg = reg;
		this.type = type;
		this.value = value;
		this.status = status;
		this.createTime = createTime;
		this.expiredTime = expiredTime;
		this.label = label;
		this.name = name;
		this.couponOrders = couponOrders;
		this.couponCustomers = couponCustomers;
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
	@JoinColumn(name = "sales_id")
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name = "code", unique = true, nullable = false)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "reg")
	public Integer getReg() {
		return this.reg;
	}

	public void setReg(Integer reg) {
		this.reg = reg;
	}

	@Column(name = "type", length = 9)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "value", precision = 10)
	public BigDecimal getValue() {
		return this.value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
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
	@Column(name = "expired_time", length = 19)
	public Date getExpiredTime() {
		return this.expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	@Column(name = "label")
	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "coupon")
	public Set<CouponOrder> getCouponOrders() {
		return this.couponOrders;
	}

	public void setCouponOrders(Set<CouponOrder> couponOrders) {
		this.couponOrders = couponOrders;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "coupon")
	public Set<CouponCustomer> getCouponCustomers() {
		return this.couponCustomers;
	}

	public void setCouponCustomers(Set<CouponCustomer> couponCustomers) {
		this.couponCustomers = couponCustomers;
	}

}
