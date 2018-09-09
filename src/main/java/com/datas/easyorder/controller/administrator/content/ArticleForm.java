package com.datas.easyorder.controller.administrator.content;

import org.springframework.web.multipart.MultipartFile;

import com.datas.easyorder.db.entity.Article;

public class ArticleForm {

	private Article article;
	
	private MultipartFile defaultImg;
	private MultipartFile[] sliderImg;
	
	private Long menu1Id = -1L;
	private Long menu2Id = -1L;
	private Long menu3Id = -1L;
	
	
	public MultipartFile getDefaultImg() {
		return defaultImg;
	}
	public void setDefaultImg(MultipartFile defaultImg) {
		this.defaultImg = defaultImg;
	}
	public MultipartFile[] getSliderImg() {
		return sliderImg;
	}
	public void setSliderImg(MultipartFile[] sliderImg) {
		this.sliderImg = sliderImg;
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
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	
	
	
}
