package com.datas.easyorder.controller.administrator.attachment;

import org.springframework.web.multipart.MultipartFile;

import com.datas.easyorder.db.entity.Attachment;

public class AttachmentForm {

	private Attachment attachment;
	
	private MultipartFile[] attachments;
	
	private Long menu1Id = -1L;
	private Long menu2Id = -1L;
	private Long menu3Id = -1L;
	
	
	public Attachment getAttachment() {
		return attachment;
	}
	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}
	public MultipartFile[] getAttachments() {
		return attachments;
	}
	public void setAttachments(MultipartFile[] attachments) {
		this.attachments = attachments;
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
	
	
	
	
}
