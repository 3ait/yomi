package com.datas.easyorder.controller.administrator.product.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.datas.easyorder.controller.BaseLogic;
import com.datas.easyorder.controller.administrator.product.view.ProductEditView;
import com.datas.easyorder.controller.administrator.product.view.ProductForm;
import com.datas.easyorder.controller.administrator.product.view.ProductMulitPrice;
import com.datas.easyorder.controller.administrator.product.view.RankPrice;
import com.datas.easyorder.controller.web.product.view.ProductView;
import com.datas.easyorder.db.dao.AttachmentRepository;
import com.datas.easyorder.db.dao.BranchProductRepository;
import com.datas.easyorder.db.dao.BranchRepository;
import com.datas.easyorder.db.dao.MenuRepository;
import com.datas.easyorder.db.dao.ProductAttrRepository;
import com.datas.easyorder.db.dao.ProductRepository;
import com.datas.easyorder.db.dao.ProductSpecifications;
import com.datas.easyorder.db.dao.RankCustomerRepository;
import com.datas.easyorder.db.dao.RankProductPriceRepository;
import com.datas.easyorder.db.entity.Attachment;
import com.datas.easyorder.db.entity.Branch;
import com.datas.easyorder.db.entity.BranchProduct;
import com.datas.easyorder.db.entity.Customer;
import com.datas.easyorder.db.entity.Menu;
import com.datas.easyorder.db.entity.Product;
import com.datas.easyorder.db.entity.ProductAttr;
import com.datas.easyorder.db.entity.ProductAttrKey;
import com.datas.easyorder.db.entity.ProductAttrValue;
import com.datas.easyorder.db.entity.RankCustomer;
import com.datas.easyorder.db.entity.RankProductPrice;
import com.datas.utils.SearchForm;
import com.plugin.utils.DateHelper;

/**
 * 
 * @author leo
 *  ProductLogic
 */
@Component
public class ProductLogic extends BaseLogic<Product>{

	
	@Autowired
	ProductRepository productRepository;
	@Autowired
	AttachmentRepository attachmentRepository;
	@Autowired
	ProductAttrRepository productAttrRepository;
	@Autowired
	MenuRepository menuRepository;
	@Autowired
	BranchRepository branchRepository;
	@Autowired
	BranchProductRepository branchProductRepository;
	@Autowired
	RankCustomerRepository rankCustomerRepository;
	@Autowired
	RankProductPriceRepository rankProductPriceRepository;
	
	/**
	 * search
	 * @param searchForm
	 * @param pageable
	 * @return
	 */
	@Transactional(rollbackOn = Exception.class)
	public Page<Product> productSearch(SearchForm searchForm,Pageable pageable){
		
		Page<Product> page = productRepository.findAll(ProductSpecifications.getSearchSpecification(searchForm),pageable);
		
		page.getContent().forEach(p ->{
			String[] sortBy = {"rankCustomer.rankLevel"};
			Pageable rankCustomerSort = new PageRequest(0, Integer.MAX_VALUE,Direction.ASC,sortBy);
			p.setRankProductPriceList(rankProductPriceRepository.findAllByProductId(p.getId(),rankCustomerSort));
		});
		
		return page;
	}
	
	/**
	 * search
	 * @param searchForm
	 * @param pageable
	 * @return
	 */
	@Transactional(rollbackOn = Exception.class)
	public List<ProductMulitPrice> getProductAndMulitiplePrice(SearchForm searchForm,Pageable pageable){
		
		List<ProductMulitPrice> list = new ArrayList<>();
		Page<Product> page = productRepository.findAll(ProductSpecifications.getSearchSpecification(searchForm),pageable);
		
		page.getContent().forEach( p -> {
			ProductMulitPrice pmp = new ProductMulitPrice();
			pmp.setProduct(p);
			
			List<RankPrice> rankPriceList = new ArrayList<>();
			p.getRankProductPrices().stream().sorted((a,b)->a.getId().compareTo(b.getId())).collect(Collectors.toList()).forEach(rankProductPrice ->{
				RankPrice rp = new RankPrice();
				rp.setRankProductPriceId(rankProductPrice.getId());
				rp.setRankCustomerId(rankProductPrice.getRankCustomer().getId());
				rp.setRankLevel(rankProductPrice.getRankCustomer().getRankLevel());
				rp.setPrice(rankProductPrice.getPrice());
				rp.setRankDesc(rankProductPrice.getRankCustomer().getRankDesc());
				rp.setDescription(rankProductPrice.getDescription());
				
				rankPriceList.add(rp);
			});
			pmp.setRankPriceList(rankPriceList);
			list.add(pmp);
		});
		return list;
	}
	
	
	
	
	/**
	 * new Product Save
	 * 
	 * @param categoryIds
	 */
	@Transactional(rollbackOn = Exception.class)
	public void newSave(ProductForm productForm) {
		
		//SAVE PRODUCT
		Product product = productForm.getProduct();
		Menu menu = null;
		Long categoryId = -1L;
		if(productForm.getMenu3Id()!=-1){
			categoryId  = productForm.getMenu3Id();
		}else if(productForm.getMenu2Id()!=-1){
			categoryId =  productForm.getMenu2Id();
		}else if(productForm.getMenu1Id()!=-1){
			categoryId =  productForm.getMenu1Id();
		}
		if(categoryId!=-1){
			menu = new Menu();
			menu.setId(categoryId);
			product.setMenu(menu);
		}
		
		String defaultImgSrc = "/img/" + DateHelper.getYYYYMMDD();
		if(!productForm.getDefaultImg().isEmpty()){
			
			product.setDefaultSrc(contentPath + defaultImgSrc + "/" + productForm.getDefaultImg().getOriginalFilename());
			//save img
			super.saveImg(productForm.getDefaultImg(), defaultImgSrc);
		}
		
		product.setPrice2(product.getPrice2()==null?0:product.getPrice2());
		
		product.setCreateTime(Calendar.getInstance().getTime());
		product.setModifyTime(Calendar.getInstance().getTime());
		productRepository.save(product);
		
		super.saveImgs(productForm.getSliderImg(), defaultImgSrc);
		
		//save product attr
		String[] keyValues = productForm.getAttrKeyValue();
		for(String keyvalue : keyValues){
			String[] str = keyvalue.split("-");
			if(str.length==2){
				ProductAttr productAttr = new ProductAttr();
				productAttr.setProduct(product);
				ProductAttrKey productAttrKey = new ProductAttrKey();
				productAttrKey.setId(Long.valueOf(str[0]));
				productAttr.setProductAttrKey(productAttrKey);
				ProductAttrValue productAttrValue = new ProductAttrValue();
				productAttrValue.setId(Long.valueOf(str[1]));
				productAttr.setProductAttrValue(productAttrValue);
				
				productAttrRepository.save(productAttr);
			}
		}
		
		//save slider
		for(MultipartFile mf : productForm.getSliderImg()){
			if(mf.isEmpty()){
				continue;
			}
			Attachment attachment = new Attachment();
			attachment.setTitle(product.getProductName());
			attachment.setProduct(product);
			attachment.setMenu(menu);
			attachment.setFileName(mf.getOriginalFilename());
			attachment.setFilePath(contentPath + defaultImgSrc + "/" + mf.getOriginalFilename());
			attachment.setCreateTime(Calendar.getInstance().getTime());
			
			attachmentRepository.save(attachment);
		}
		
		//增加多库存
		Iterable<Branch> itr = branchRepository.findAll();
		List<BranchProduct> list = new ArrayList<>();
		itr.forEach(b -> {
			BranchProduct bp = new BranchProduct();
			bp.setProduct(product);
			bp.setBranch(b);
			bp.setStock(0);
			bp.setPrice1(product.getPrice1());
			bp.setPrice2(product.getPrice2());
			list.add(bp);
		});
		branchProductRepository.save(list);
		//增加多价格
		Iterable<RankCustomer> itrRankCustomer = rankCustomerRepository.findAll();
		List<RankProductPrice> listRankProductPrice = new ArrayList<>();
		itrRankCustomer.forEach(r ->{
			RankProductPrice rpp = new RankProductPrice();
			rpp.setProduct(product);
			rpp.setRankCustomer(r);
			rpp.setPrice(0D);
			rpp.setPrice1(0D);
			listRankProductPrice.add(rpp);
		});
		rankProductPriceRepository.save(listRankProductPrice);
		
	}

	/**
	 * get Product Edit 
	 * @param productId
	 * @return ProductEditView
	 */
	@Transactional(rollbackOn = Exception.class)
	public ProductEditView getProductEditView(Long productId) {
		
		Product product = productRepository.findOne(productId);
		List<Attachment> attachmentList = attachmentRepository.findAllByProductId(productId);
		List<ProductAttr> productAttrList = productAttrRepository.findByProductId(productId);
		
		ProductEditView productEditView = new ProductEditView();
		productEditView.setProduct(product);
		productEditView.setAttachmentList(attachmentList);
		 
		Set<String> keyvalueSet = new HashSet<>();
		productAttrList.forEach(productAttr -> keyvalueSet.add( productAttr.getProductAttrKey().getId()+ "," + productAttr.getProductAttrValue().getId()));
		productEditView.setKeyvalueSet(keyvalueSet);
		
		return productEditView;
	}

	/**
	 * 编辑保存
	 * @param productForm
	 */
	@Transactional(rollbackOn = Exception.class)
	public void editSave(ProductForm productForm) {
		
		//SAVE PRODUCT
		Product product = productForm.getProduct();
		Product productFromDb = productRepository.findOne(productForm.getProduct().getId());
		
		if(productFromDb==null){
			return ;
		}
		
		//构建新的 product
		Menu menu = null;
		Long categoryId = -1L;
		if(productForm.getMenu3Id()!=-1){
			categoryId  = productForm.getMenu3Id();
		}else if(productForm.getMenu2Id()!=-1){
			categoryId =  productForm.getMenu2Id();
		}else if(productForm.getMenu1Id()!=-1){
			categoryId =  productForm.getMenu1Id();
		}
		if(categoryId!=-1){
			menu = new Menu();
			menu.setId(categoryId);
			product.setMenu(menu);
		}
		
		//拷贝 属性
//		BeanUtils.copyProperties(product, productFromDb);
		productFromDb.setMpn(product.getMpn());
		productFromDb.setProductName(product.getProductName());
		productFromDb.setProductNameAlias(product.getProductNameAlias());
		productFromDb.setCost(product.getCost());
		productFromDb.setPrice1(product.getPrice1());
		productFromDb.setPrice2(product.getPrice2()==null?0:product.getPrice2());
		productFromDb.setStatus(product.getStatus());
		productFromDb.setHot(product.getHot());
		productFromDb.setPromote(product.getPromote());
		productFromDb.setFrontPage(product.getFrontPage());
		productFromDb.setRecommend(product.getRecommend());
		productFromDb.setSoldNum(product.getSoldNum());
		productFromDb.setStock(product.getStock());
		productFromDb.setWeight(product.getWeight());
		productFromDb.setClickNum(product.getClickNum());
		productFromDb.setSummary(product.getSummary());
		productFromDb.setDescription(product.getDescription());
		productFromDb.setSeoDesc(product.getSeoDesc());
		productFromDb.setSeoKeywords(product.getSeoKeywords());
		productFromDb.setLocation(product.getLocation());
		productFromDb.setLabel(product.getLabel());
		productFromDb.setMenu(menu);
		productFromDb.setNorms(product.getNorms());
		productFromDb.setMobileDefaultDesc(product.getMobileDefaultDesc());
		productFromDb.setMobileDefaultSrc(product.getMobileDefaultSrc());
		//save default img
		//判定如果上传默认图片， 则删除旧的default img
		String defaultImgSrc = "/img/" + DateHelper.getYYYYMMDD();
		if(!productForm.getDefaultImg().isEmpty()){
			File f = new File(absoultPath.replace(contentPath, productFromDb.getDefaultSrc()+""));
			if(f.exists()){
				f.delete();
			}
			productFromDb.setDefaultSrc(contentPath + defaultImgSrc + "/" + productForm.getDefaultImg().getOriginalFilename());
			super.saveImg(productForm.getDefaultImg(), defaultImgSrc);
		}
		product.setModifyTime(Calendar.getInstance().getTime());
		
		productRepository.save(productFromDb);
		
		
		
		
		//删除所有旧的Product Attr
		productAttrRepository.delete(productAttrRepository.findByProductId(productFromDb.getId()));
		
		//添加新 product attr
		String[] keyValues = productForm.getAttrKeyValue();
		for(String keyvalue : keyValues){
			String[] str = keyvalue.split("-");
			if(str.length==2){
				ProductAttr productAttr = new ProductAttr();
				productAttr.setProduct(product);
				ProductAttrKey productAttrKey = new ProductAttrKey();
				productAttrKey.setId(Long.valueOf(str[0]));
				productAttr.setProductAttrKey(productAttrKey);
				ProductAttrValue productAttrValue = new ProductAttrValue();
				productAttrValue.setId(Long.valueOf(str[1]));
				productAttr.setProductAttrValue(productAttrValue);
				
				productAttrRepository.save(productAttr);
			}
		}
		
		//save slider img
		super.saveImgs(productForm.getSliderImg(), defaultImgSrc);
		//save slider
		for(MultipartFile mf : productForm.getSliderImg()){
			if(!mf.isEmpty()){
				Attachment attachment = new Attachment();
				attachment.setTitle(product.getProductName());
				attachment.setProduct(product);
				attachment.setMenu(menu);
				attachment.setFileName(mf.getOriginalFilename());
				attachment.setFilePath(contentPath + defaultImgSrc + "/" + mf.getOriginalFilename());
				attachment.setCreateTime(Calendar.getInstance().getTime());
				
				attachmentRepository.save(attachment);
				
			}
		}
	}

	/**
	 * 删除产品
	 * @param productId
	 * @return
	 */
	@Transactional(rollbackOn = Exception.class)
	public void delProductById(Long productId) {
		//删除所有旧的Product Attr
		productAttrRepository.delete(productAttrRepository.findByProductId(productId));
		//删除附件
		
		List<Attachment> attachmentList = attachmentRepository.findAllByProductId(productId);
		attachmentList.forEach(attachment -> {
			File f = new File(absoultPath.replace(contentPath, attachment.getFilePath()));
			if(f.exists()){
				f.delete();
			}
			attachmentRepository.delete(attachment);
		});
		// 删除属性
		productAttrRepository.delete(productAttrRepository.findByProductId(productId));
		//删除库存
		branchProductRepository.delete(branchProductRepository.findAllByProductId(productId));
		//删除多价格
		rankProductPriceRepository.delete(rankProductPriceRepository.findAllByProductId(productId));
		//删除产品
		productRepository.delete(productId);
	}

	
	/**
	 * Web
	 * get Promote Product
	 * @return Page<Product> 
	 */
	public Page<Product> getPromoteProduct(Pageable pageable) {
		return productRepository.findByPromoteAndStatusNot((byte)1,(byte)0, pageable);
	}
	/**
	 * Web
	 * get Front Page Product
	 * @return Page<Product> 
	 */
	public Page<Product> getFrontPageProduct(Pageable pageable) {
		return productRepository.findByFrontPageAndStatusNot((byte)1,(byte)0, pageable);
	}
	
	
	/**
	 * Web
	 * get hot
	 * @return Page<Product> 
	 */
	@Transactional(rollbackOn = Exception.class)
	public Page<Product> getHotProduct(Pageable pageable) {
		return productRepository.findByHotAndStatusNot((byte)1,(byte)0, pageable);
	}
	
	/**
	 * Web
	 * get hot
	 * @return Page<Product> 
	 */
	public Page<Product> getRecommendProduct(Pageable pageable) {
		return productRepository.findByRecommendAndStatusNot((byte)1,(byte)0, pageable);
	}

	
	/**
	 * Web
	 * get prodcut By Menu Id
	 * @return Page<Product> 
	 */
	@Transactional(rollbackOn = Exception.class)
	public Page<Product> getProductByMenuId(Long categoryId,Pageable pageable) {
		
		Menu menu = menuRepository.findOne(categoryId);
		Pageable menuPageable = new PageRequest(0, Integer.MAX_VALUE,Direction.ASC,"position");
		
		List<Long> menuIds = new ArrayList<>();
		if(menu!=null){
			if(menu.getLevel()==1){
				menuIds.add(menu.getId());
				menuRepository.findByMenuId(categoryId, menuPageable).forEach(m2 -> {
					menuIds.add(m2.getId());
					menuRepository.findByMenuId(m2.getId(), menuPageable).forEach(m3 -> menuIds.add(m3.getId()));
				});
				
				
			}else if(menu.getLevel()==2){
				menuIds.add(menu.getId());
				menuRepository.findByMenuId(categoryId, menuPageable).forEach(m3 -> {
					menuIds.add(m3.getId());
				});
			}else if(menu.getLevel()==3){
				menuIds.add(menu.getId());
			}
		}
		 return productRepository.findByStatusNotAndMenuIdIn(ProductRepository.status_cancelled,menuIds,pageable);
		
	}

	/**
	 * Web
	 * search product By Attrbute & categoryId;
	 * @param q
	 * @param attrValueIds
	 * @param pageable
	 * @return Page<Product> 
	 */
	@Transactional(rollbackOn = Exception.class)
	public Page<Product> searchProductByAttrAndCategory(Long categoryId,  Long[] attrValueIds, Pageable pageable) {
		
		if(attrValueIds==null || attrValueIds.length==0){
			return getProductByMenuId(categoryId, pageable);
		}
		Pageable productAttrPageRequest = new PageRequest(0,Integer.MAX_VALUE);
		Page<ProductAttr> productAttrPage = productAttrRepository.findAllByProductAttrValueIdIn(attrValueIds,productAttrPageRequest);
		
		List<Long> productIds = new ArrayList<>();
		productAttrPage.forEach(pa -> productIds.add(pa.getProduct().getId()));
		productIds.stream().distinct().collect(Collectors.toList());
		
		Menu menu = menuRepository.findOne(categoryId);
		Pageable menuPageable = new PageRequest(0, Integer.MAX_VALUE,Direction.ASC,"position");
		
		List<Long> menuIds = new ArrayList<>();
		if(menu!=null){
			if(menu.getLevel()==1){
				menuIds.add(menu.getId());
				menuRepository.findByMenuId(categoryId, menuPageable).forEach(m2 -> {
					menuIds.add(m2.getId());
					menuRepository.findByMenuId(m2.getId(), menuPageable).forEach(m3 -> menuIds.add(m3.getId()));
				});
			}else if(menu.getLevel()==2){
				menuIds.add(menu.getId());
				menuRepository.findByMenuId(categoryId, menuPageable).forEach(m3 -> {
					menuIds.add(m3.getId());
				});
			}else if(menu.getLevel()==3){
				menuIds.add(menu.getId());
			}
		}
		Page<Product>  page = productRepository.findByStatusInAndMenuIdInAndIdIn(new Byte[]{(byte)1,(byte)2}, menuIds,productIds , pageable);
		page.getContent().forEach(p -> p = productRepository.findOne(p.getId()));
		return page;
	}



	
	/**
	 * Web
	 * search product By Attrbute & q;
	 * @param q
	 * @param attrValueIds
	 * @param pageable
	 * @return Page<Product> 
	 */
	public Page<Product> searchProductByAttrAndQ(String q, Long[] attrValueIds, Pageable pageable) {
		
		if(attrValueIds==null){
			SearchForm sf = new SearchForm();
			sf.setQ(q);
			sf.setStatus(ProductRepository.status_cancelled);
			return productRepository.findAll(ProductSpecifications.getSearchSpecification(sf),pageable);
		}
		
		Page<ProductAttr> productAttrPage = productAttrRepository.findAllByProductAttrValueIdIn(attrValueIds,pageable);
		
		List<Long> productIds = new ArrayList<>();
		productAttrPage.forEach(pa -> productIds.add(pa.getProduct().getId()));
		productIds.stream().distinct().collect(Collectors.toList());
		
		if(productIds.size()==0){
			return new PageImpl<>(new ArrayList<>());
		}
		
		return productRepository.findAll(ProductSpecifications.getByIdsInAndStatusInAndQ(productIds,new Byte[]{ProductRepository.status_acitve,ProductRepository.status_out_stock},q),pageable);
	}

	/**
	 * 
	 * @param productId
	 * @return
	 */
	@Transactional(rollbackOn = Exception.class)
	public ProductEditView getProdcutViewById(Long productId) {	
		ProductEditView productEditView = new ProductEditView();
		
		Product product = productRepository.findOneByIdAndStatusNot(productId,(byte)0);
		if(product==null){
			return null;
		}
		product.setClickNum(product.getClickNum()+1);
		
		if(product!=null){
			List<Attachment> attachmentList = attachmentRepository.findAllByProductId(productId);
			List<ProductAttr> productAttrList = productAttrRepository.findByProductId(productId);
			
			productEditView.setProduct(product);
			productEditView.setAttachmentList(attachmentList);
			
			Set<String> keyvalueSet = new HashSet<>();
			productAttrList.forEach(productAttr -> {
				if(productAttr.getProductAttrKey()!=null){
					keyvalueSet.add( productAttr.getProductAttrKey().getId()+ "," + productAttr.getProductAttrValue().getId());
				}
			});
			productEditView.setKeyvalueSet(keyvalueSet);
		}
		productRepository.save(product);
		return productEditView;
	}

	/**
	 * 
	 * @return
	 */
	public Product getProdcutById(Long productId){
		return productRepository.findOne(productId);
	}

	@Override
	public CrudRepository<Product, Long> getRepository() {
		return productRepository;
	}

	/**
	 * List<Product>
	 * @param attrValueIds
	 * @param pageable
	 * @return Page<Product>
	 */
	@Transactional(rollbackOn = Exception.class)
	public Page<Product> getNewArrivalProdcutsByAttrId(Long[] attrValueIds,Pageable pageable){
		
		Page<Product> page ;
		if(attrValueIds!=null){
			page = productRepository.findByProductAttrValueIdInAndProductFrontPage(attrValueIds, (byte)1, pageable);
		}else{
			page = getFrontPageProduct(pageable);
		}
		
		return page;
	}
	
	/**
	 * List<Product>
	 * @param attrValueIds
	 * @param pageable
	 * @return Page<Product>
	 */
	@Transactional(rollbackOn = Exception.class)
	public Page<Product> getPromoteProdcutsByAttrId(Long[] attrValueIds,Pageable pageable){
		
		Page<Product> page ;
		if(attrValueIds!=null){
			page = productRepository.findByProductAttrValueIdInAndProductPromote(attrValueIds, (byte)1, pageable);
		}else{
			page = getPromoteProduct(pageable);
		}
		
		
		return page;
	}
	/**
	 * List<Product>
	 * @param attrValueIds
	 * @param pageable
	 * @return Page<Product>
	 */
	@Transactional(rollbackOn = Exception.class)
	public Page<Product> getSpecialProdcutsByAttrId(Long[] attrValueIds,Pageable pageable){
		
		Page<Product> page ;
		if(attrValueIds!=null){
			page = productRepository.findByProductAttrValueIdInAndProductRecommend(attrValueIds, (byte)1, pageable);
		}else{
			page = getPromoteProduct(pageable);
		}
		
		return page;
	}

	public Product getPrductByMpn(String mpn) {
		Product product = null;
		Page<Product> page = productRepository.findByMpn(mpn, new PageRequest(0, 1));
		if(page.getContent().size()==1){
			product = page.getContent().get(0);
		}
		return product;
	}



	/**
	 * 根据productAttrValueId 查询product
	 * @param productAttrValueId
	 * @param pageable
	 * @return
	 */
	public Page<Product> getProductByAttrValueId(Long productAttrValueId, Pageable pageable) {
		
		Page<Product> ret = productRepository.findByProductAttrValueIdInAndStatus(new Long[]{productAttrValueId}, ProductRepository.status_acitve, pageable);
		return ret;
	}



	/**
	 * duplicate product
	 * @param productId
	 */
	@Transactional(rollbackOn = Exception.class)
	public void duplicate(Long productId) {
		Product product =  productRepository.findOne(productId);
		if(productId!=null){
			Product newProduct = new Product();
			BeanUtils.copyProperties(product, newProduct);
			
			newProduct.setId(null);
			newProduct.setCreateTime(Calendar.getInstance().getTime());
			newProduct.setModifyTime(Calendar.getInstance().getTime());
			
			newProduct.setDefaultSrc("");
			newProduct.setReplies(null);
			newProduct.setOrderItems(null);
			newProduct.setAttachments(null);
			newProduct.setProductAttrs(null);
			newProduct.setComments(null);
			newProduct.setBranchProducts(null);
			
			productRepository.save(newProduct);
			
			List<Attachment> attachmentList = attachmentRepository.findAllByProductId(productId);
			
			List<Attachment> attachmentListNew = new ArrayList<>();
			attachmentList.forEach(at ->{
				
				Attachment da = new Attachment();
				BeanUtils.copyProperties(at, da);
				da.setId(null);
				da.setProduct(newProduct);
				
				attachmentListNew.add(da);
			});
			attachmentRepository.save(attachmentListNew);
			
			List<ProductAttr> attrList = productAttrRepository.findByProductId(productId);
			List<ProductAttr> attrListNew = new ArrayList<>();
			attrList.forEach(a ->{
				ProductAttr dpa = new ProductAttr();
				BeanUtils.copyProperties(a, dpa);
				dpa.setId(null);
				dpa.setProduct(newProduct);
				dpa.setProductAttrKey(a.getProductAttrKey());
				dpa.setProductAttrValue(a.getProductAttrValue());
				attrListNew.add(dpa);
				
			});
			productAttrRepository.save(attrListNew);
			
			//增加多库存
			Iterable<Branch> itr = branchRepository.findAll();
			List<BranchProduct> list = new ArrayList<>();
			itr.forEach(b -> {
				BranchProduct bp = new BranchProduct();
				bp.setProduct(newProduct);
				bp.setBranch(b);
				bp.setStock(0);
				bp.setPrice1(product.getPrice1());
				bp.setPrice2(product.getPrice2());
				list.add(bp);
			});
			branchProductRepository.save(list);
		}
	}
	
	
	/**
	 * product 根据Customer rank 获取产品价格
	 * @param productPage
	 * @return Page<Product> -> Page<ProductView>
	 * 
	 */
	@Deprecated
	@Transactional(rollbackOn = Exception.class)
	public Page<ProductView> productTransfer(Page<Product> productPage,Customer customer){
		
		List<ProductView> pvList = new ArrayList<>();
		productPage.getContent().forEach(p -> {
			
			ProductView productView = new ProductView();
			productView.setProduct(p);
			if(customer.getRankCustomer()!=null){
				RankProductPrice rankProductPrice = rankProductPriceRepository.findOneByProductIdAndRankCustomerId(p.getId(),customer.getRankCustomer().getId());
				
				productView.setRankProductPriceId(rankProductPrice.getId());
				productView.setRankProductPricePrice(rankProductPrice.getPrice());
				
				RankCustomer rankCustomer = rankCustomerRepository.findOne(rankProductPrice.getRankCustomer().getId());
				productView.setRankCustomerId(rankCustomer.getId());
				productView.setRankCustomerRankLevel(rankCustomer.getRankLevel());
				productView.setRankCustomerRankDesc(rankCustomer.getRankDesc());
				
			}else{
				productView.setRankProductPricePrice(p.getPrice1());
			}
			pvList.add(productView);
		});
		
		Pageable pageable = new PageRequest(productPage.getNumber(),productPage.getSize(), productPage.getSort());
		Page<ProductView> page = new PageImpl<>(pvList,pageable,productPage.getTotalElements());
		
		return page;
	}
	
	
	/**
	 * 根据客户等级价格获取单独价格
	 * @param productEditView
	 * @param customer
	 * @return ProductEditView
	 */
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public ProductEditView getSingleProductCustomerPrice(ProductEditView productEditView,Customer customer){
		if(customer==null){
			productEditView.getProduct().setPrice3(0D);
		}else{
			if(customer.getRankCustomer()!=null){
				RankProductPrice rankProductPrice = rankProductPriceRepository.findOneByProductIdAndRankCustomerId(productEditView.getProduct().getId(),customer.getRankCustomer().getId());
				if(rankProductPrice!=null){
					productEditView.getProduct().setPrice3(rankProductPrice.getPrice());
				}
			}else{
				productEditView.getProduct().setPrice3(productEditView.getProduct().getPrice1());
			}
		}
		
		return productEditView;
	}
	
	
	/**
	 * 根据custmer Rank获取产品价格，放在product.price3里面
	 * @param productPage
	 * @param customer
	 * @return Page<Product>
	 */
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public Page<Product> getCustomerRankPrice(Page<Product> productPage,Customer customer){
		
		if(customer==null){
			productPage.getContent().forEach(p -> {
				p.setPrice3(0D);
			});
		}else{
			productPage.getContent().forEach(p -> {
				if(customer.getRankCustomer()!=null){
					RankProductPrice rankProductPrice = rankProductPriceRepository.findOneByProductIdAndRankCustomerId(p.getId(),customer.getRankCustomer().getId());
					if(rankProductPrice!=null){
						p.setPrice3(rankProductPrice.getPrice());
					}
				}else{
					p.setPrice3(p.getPrice1());
				}
			});
		}
		
		return productPage;
	}
}
