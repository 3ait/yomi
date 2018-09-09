package com.datas.easyorder.db.entity;
// Generated 2018-3-7 11:20:55 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * PageView generated by hbm2java
 */
@Entity
@Table(name = "page_view", catalog = "ultimatelife", uniqueConstraints = @UniqueConstraint(columnNames = "date"))
public class PageView implements java.io.Serializable {

	private Long id;
	private Date date;
	private Integer viewNumber;

	public PageView() {
	}

	public PageView(Date date, Integer viewNumber) {
		this.date = date;
		this.viewNumber = viewNumber;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "date", unique = true, length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "view_number")
	public Integer getViewNumber() {
		return this.viewNumber;
	}

	public void setViewNumber(Integer viewNumber) {
		this.viewNumber = viewNumber;
	}

}
