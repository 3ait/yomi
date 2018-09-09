package com.plugin.utils;

public class Seo {

	/**
		1)首页title写法，一般是“网站名称-主关键词或一句含有主关键词的描述”。
		做“招聘”这个词，就这样写“企业招聘_创业公司招聘技巧-蚂蚁招聘”。
		2）栏目页title写法，一般有2种：“栏目名称-网站名称”、“栏目名称栏目关键词-网站名称”。
		小编建议用“栏目名称-网站名称”。
		3）分类列表页title写法，一般是“分类列表页名称-栏目名称-网站名称”，这个和栏目页差不多。
	 */
	private String title;
	/**
	 *  1）首页keywords写法，一般是“网站名称,主要栏目名,主要关键词”。
		2）栏目页keywords写法，一般是“栏目名称,栏目关键字,栏目分类列表名称”。
		3）分类列表页keywords写法，这个就比较简单了，只要将你这个栏目中的主要关键字写入即可。
	 */
	private String keywords;
	
	/**
	 * 1）首页description写法，一般是将首页的标题、关键词和一些特殊栏目的内容融合到里面，写成简单的介绍。
		2）栏目页description写法，一般是将栏目的标题、关键字、分类列表名称融合到里面，写成简单的介绍。
		3）分类列表页description，这个就比较简单了，一般只需要把分类列表的标题、关键词融合在一起，写成简单的介绍。
	 */
	private String description;
	
	public Seo(){
		
	}
	public Seo(String title, String keywords,String description){
		this.title = title;
		this.keywords = keywords;
		this.description = description;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
