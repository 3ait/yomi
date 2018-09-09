package com.datas.utils;

import org.hibernate.validator.constraints.Length;

public class SearchForm {
	//页码
	private Integer page = 0;
	//默认Size
	private Integer size = 40;
	private String sortBy = "id";
	private String sort = "DESC";
	
	@Length(min=0,max=50)
	private String q = "";
	
	@Length(min=1,max=50)
	private String dateFrom;
	
	@Length(min=1,max=50)
	private String dateTo;
	
	private Integer status = -1;
	
	private Integer isPaid = -1;
	
	private Integer adminStatus = -1;
	
	private Long menu1Id = -1L;
	
	private Long menu2Id = -1L;;
	
	private Long menu3Id = -1L;
	
	private Long salesId = -1L;
	
	
	public String getQ() {
		return q.trim();
	}
	public void setQ(String q) {
		this.q = q;
	}
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getIsPaid() {
		return isPaid;
	}
	public void setIsPaid(int isPaid) {
		this.isPaid = isPaid;
	}
	public Integer getAdminStatus() {
		return adminStatus;
	}
	public void setAdminStatus(Integer adminStatus) {
		this.adminStatus = adminStatus;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setIsPaid(Integer isPaid) {
		this.isPaid = isPaid;
	}
	public Long getMenu1Id() {
		return menu1Id;
	}
	public void setMenu1Id(Long menu1Id) {
		this.menu1Id = menu1Id;
	}
	public Long getMenu2Id() {
		return menu2Id;
	}
	public void setMenu2Id(Long menu2Id) {
		this.menu2Id = menu2Id;
	}
	public Long getMenu3Id() {
		return menu3Id;
	}
	public void setMenu3Id(Long menu3Id) {
		this.menu3Id = menu3Id;
	}
	public String getSortBy() {
		return sortBy;
	}
	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public Long getSalesId() {
		return salesId;
	}
	public void setSalesId(Long salesId) {
		this.salesId = salesId;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	
	
}
