package com.datas.easyorder.controller.administrator.content.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.datas.easyorder.controller.BaseLogic;
import com.datas.easyorder.controller.administrator.category.ArticleEditView;
import com.datas.easyorder.controller.administrator.content.ArticleForm;
import com.datas.easyorder.db.dao.ArticleRepository;
import com.datas.easyorder.db.dao.ArticleSpecifications;
import com.datas.easyorder.db.dao.AttachmentRepository;
import com.datas.easyorder.db.dao.MenuRepository;
import com.datas.easyorder.db.entity.Article;
import com.datas.easyorder.db.entity.Attachment;
import com.datas.easyorder.db.entity.Menu;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

/**
 * 
 * @author leo
 * 
 */
@Component
public class ContentLogic extends BaseLogic<Article>{

	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	AttachmentRepository attachmentRepository;
	@Autowired
	MenuRepository menuRepository;
	
	/**
	 * search
	 * @param searchForm
	 * @param pageable
	 * @return
	 */
	public Page<Article> articleSearch(SearchForm searchForm,Pageable pageable){
		
		return articleRepository.findAll(ArticleSpecifications.getSearchSpecification(searchForm),pageable);
		
	}
	
	
	
	/**
	 * new Product Save
	 * 
	 * @param categoryIds
	 */
	@Transactional(rollbackOn = Exception.class)
	public void newSave(ArticleForm articleForm) {
		
		//SAVE PRODUCT
		Article article = articleForm.getArticle();
		Menu menu = null;
		Long categoryId = -1L;
		if(articleForm.getMenu3Id()!=-1){
			categoryId  = articleForm.getMenu3Id();
		}else if(articleForm.getMenu2Id()!=-1){
			categoryId =  articleForm.getMenu2Id();
		}else if(articleForm.getMenu1Id()!=-1){
			categoryId =  articleForm.getMenu1Id();
		}
		if(categoryId!=-1){
			menu = new Menu();
			menu.setId(categoryId);
			article.setMenu(menu);
		}
		
		article.setCreateTime(Calendar.getInstance().getTime());
		article.setModifyTime(Calendar.getInstance().getTime());
		articleRepository.save(article);
		
		String defaultImgSrc = "/img/" + DateHelper.getYYYYMMDD() + "/" + article.getId();
		if(!articleForm.getDefaultImg().isEmpty()){
			article.setDefaultSrc(contentPath + defaultImgSrc + "/" + articleForm.getDefaultImg().getOriginalFilename());
		}
		articleRepository.save(article);
		//save img
		super.saveImg(articleForm.getDefaultImg(), defaultImgSrc);
		super.saveImgs(articleForm.getSliderImg(), defaultImgSrc);
		
	
		
		//save slider
		for(MultipartFile mf : articleForm.getSliderImg()){
			Attachment attachment = new Attachment();
			attachment.setTitle(article.getTitle());
			attachment.setArticle(article);
			attachment.setMenu(menu);
			attachment.setFileName(mf.getOriginalFilename());
			attachment.setFilePath(contentPath + defaultImgSrc + "/" + mf.getOriginalFilename());
			attachment.setCreateTime(Calendar.getInstance().getTime());
			
			attachmentRepository.save(attachment);
		}
	}


	
	/**
	 * 删除文章
	 * @param productId
	 * @return
	 */
	@Transactional(rollbackOn = Exception.class)
	public void delAritcleById(Long articleId) {
		
		//删除附件
		List<Attachment> attachmentList = attachmentRepository.findAllByArticleId(articleId);
		attachmentList.forEach(attachment -> {
			File f = new File(absoultPath.replace(contentPath, attachment.getFilePath()));
			if(f.exists()){
				f.delete();
			}
			attachmentRepository.delete(attachment);
		});
		
		articleRepository.delete(articleId);
	}


	@Override
	public CrudRepository<Article, Long> getRepository() {
		return articleRepository;
	}


	/**
	 * ArticleEditView {@link ArticleEditView}
	 * @param articleId
	 * @return ArticleEditView
	 */
	@Transactional(rollbackOn = Exception.class)
	public ArticleEditView getArticleEditViewById(Long articleId) {
		
		Article article = articleRepository.findOne(articleId);
		List<Attachment> attachmentList = attachmentRepository.findAllByArticleId(articleId);
		
		ArticleEditView articleView = new ArticleEditView();
		articleView.setArticle(article);
		articleView.setAttachmentList(attachmentList);
		
		return articleView;
	}


	/**
	 * edit save
	 * @param articleForm
	 */
	@Transactional(rollbackOn = Exception.class)
	public void editSave(ArticleForm articleForm) {

		
		//edit SAVE PRODUCT
		Article articleFromDb = articleRepository.findOne(articleForm.getArticle().getId());
		if(articleFromDb==null){
			return;
		}
		BeanUtils.copyProperties(articleForm.getArticle(), articleFromDb, "id","createTime","defaultSrc");
		
		Menu menu = null;
		Long categoryId = -1L;
		if(articleForm.getMenu3Id()!=-1){
			categoryId  = articleForm.getMenu3Id();
		}else if(articleForm.getMenu2Id()!=-1){
			categoryId =  articleForm.getMenu2Id();
		}else if(articleForm.getMenu1Id()!=-1){
			categoryId =  articleForm.getMenu1Id();
		}
		if(categoryId!=-1){
			menu = new Menu();
			menu.setId(categoryId);
			articleFromDb.setMenu(menu);
		}

		
		articleFromDb.setModifyTime(Calendar.getInstance().getTime());
		articleRepository.save(articleFromDb);
		
		String defaultImgSrc = "/img/" + DateHelper.getYYYYMMDD() + "/" + articleFromDb.getId();
		if(!articleForm.getDefaultImg().getOriginalFilename().isEmpty()){
			articleFromDb.setDefaultSrc(contentPath + defaultImgSrc + "/" + articleForm.getDefaultImg().getOriginalFilename());
		}
		articleRepository.save(articleFromDb);
		
		//save img
		super.saveImg(articleForm.getDefaultImg(), defaultImgSrc);
		super.saveImgs(articleForm.getSliderImg(), defaultImgSrc);
		
	
		
		//save slider
		for(MultipartFile mf : articleForm.getSliderImg()){
			if(mf.isEmpty()){
				continue;
			}
			Attachment attachment = new Attachment();
			attachment.setTitle(articleFromDb.getTitle());
			attachment.setArticle(articleFromDb);
			attachment.setMenu(menu);
			attachment.setFileName(mf.getOriginalFilename());
			attachment.setFilePath(contentPath + defaultImgSrc + "/" + mf.getOriginalFilename());
			attachment.setCreateTime(Calendar.getInstance().getTime());
			
			attachmentRepository.save(attachment);
		}
	
		
	}


	/**
	 * web  根据 categoryId获取article
	 * @param categoryId
	 * @param status 0隐藏|1显示
	 * @param pageable
	 * @return Page<Article>
	 */
	public Page<Article> listArticle(Long categoryId, byte status,Pageable pageable) {
		
		return articleRepository.findByMenuIdAndStatus(categoryId,status,pageable);
	}
	
	/**
	 * 获取所有品牌
	 * @param categoryId
	 * @param status 0隐藏|1显示
	 * @param pageable
	 * @return Page<Article>
	 */
	public Page<Article> listAllBrand(byte status,Pageable pageable) {
		
		return articleRepository.findByProductAttrValueIdNotNullAndStatus(status,pageable);
	}

	/**
	 *  web
	 * @param subname
	 * @param status status 0隐藏|1显示
	 * @param pageable
	 * @return Article
	 */
	public ArticleEditView getArtitleByUrlTitle(String urlTitle, byte status,Pageable pageable){
		
		Page<Article> page = articleRepository.findByUrlTitleAndStatus(urlTitle,status,pageable);
		if(page.getContent().size()>0){
			
			Article article = page.getContent().get(0);
			List<Attachment> attachmentList = attachmentRepository.findAllByArticleId(article.getId());
			
			ArticleEditView articleView = new ArticleEditView();
			articleView.setArticle(article);
			articleView.setAttachmentList(attachmentList);
			return articleView;
		}else{
			return new ArticleEditView();
		}
	}
	
	/**
	 * 
	 * @param title
	 * @param pageable
	 * @return
	 */
	public List<Attachment> getAttachmentByTitle(String title,Pageable pageable){
		return attachmentRepository.findAllByTitle(title,pageable).getContent();
	}


	/**
	 * 根据 productAttrValueId获取 文章列表
	 * @param productAttrValueId
	 * @param statusActive
	 * @param pageable
	 * @return
	 */
	public Page<Article> getArticleByProductAttrValueId(Long productAttrValueId, byte status, Pageable pageable) {
		return articleRepository.findByProductAttrValueIdAndStatus(productAttrValueId,status,pageable);
	}


	/**
	 * 根据分类查找推荐文章
	 * @param categoryId
	 * @param recommend
	 * @param statusActive
	 * @param pageable
	 * @return
	 */
	public Page<Article> getRecommendArticle(Long categoryId, byte recommend, byte status, Pageable pageable) {
		return articleRepository.findByMenuIdAndStatusAndRecommend(categoryId, recommend,status, pageable);
	}


	/**
	 * 文章拷贝
	 * @param articleId
	 */
	@Transactional(rollbackOn = Exception.class)
	public void duplicate(Long articleId) {
		Article articleDb = articleRepository.findOne(articleId);
		
		if(articleDb!=null){
			Article articleNew = new Article();
			BeanUtils.copyProperties(articleDb, articleNew);
			
			articleNew.setId(null);
			articleNew.setAttachments(null);
			articleNew.setReplies(null);
			articleNew.setComments(null);
			
			articleRepository.save(articleNew);
			
			
			List<Attachment> attachmentList = attachmentRepository.findAllByArticleId(articleId);
			List<Attachment> attachmentListNew = new ArrayList<>();
			attachmentList.forEach(att ->{
				Attachment attNew = new Attachment();
				BeanUtils.copyProperties(att, attNew);
				attNew.setId(null);
				attNew.setArticle(articleNew);
				
				attachmentListNew.add(attNew);
			});
			attachmentRepository.save(attachmentListNew);
		}
		
		
	}


	/**
	 * 获取Hot artcile
	 * @param pageable
	 * @return
	 */
	public List<Article> getHotArticle(Pageable pageable) {
		
		Page<Article> page = articleRepository.findByHotAndStatus(ArticleRepository.hot, ArticleRepository.status_active,pageable);
		return page.getContent();
	}


	/**
	 * 获取recommend artcile
	 * @param pageable
	 * @return ArticleEditView
	 */
	public List<Article> getRecommendArticle(Pageable pageable) {
		
		Page<Article> page = articleRepository.findByRecommendAndStatus(ArticleRepository.recommend, ArticleRepository.status_active,pageable);
		return page.getContent();
	}
}
