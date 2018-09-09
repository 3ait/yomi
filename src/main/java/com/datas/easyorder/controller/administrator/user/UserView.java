package com.datas.easyorder.controller.administrator.user;

import java.util.Date;

import com.datas.easyorder.db.entity.UserCompany;
import com.datas.easyorder.db.entity.UserGroup;

public class UserView {

	private long id;
	private String name;
	private String email;
	private String password;
	private String type;
	private String phone;
	private String status;
	private Date createTime;
	private Date modifyTime;
	
	private UserCompany userCompany;
	private UserGroup userGroup;
	
	
	
	public UserGroup getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}
	public UserCompany getUserCompany() {
		return userCompany;
	}
	public void setUserCompany(UserCompany userCompany) {
		this.userCompany = userCompany;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	
}
