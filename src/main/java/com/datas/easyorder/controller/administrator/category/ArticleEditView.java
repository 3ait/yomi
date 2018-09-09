package com.datas.easyorder.controller.administrator.category;

import java.util.List;

import com.datas.easyorder.db.entity.Article;
import com.datas.easyorder.db.entity.Attachment;

public class ArticleEditView {

	private Article article;
	private List<Attachment> attachmentList;
	
	
	public List<Attachment> getAttachmentList() {
		return attachmentList;
	}
	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	
	
}
