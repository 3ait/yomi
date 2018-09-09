package com.datas.easyorder.controller.mobile.product;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datas.easyorder.controller.administrator.attachment.logic.AttachmentLogic;
import com.datas.easyorder.controller.administrator.product.logic.ProductLogic;
import com.datas.easyorder.db.entity.Attachment;
import com.datas.easyorder.db.entity.Product;
import com.datas.utils.SearchForm;
import com.payment.IPayment;
import com.payment.latipay.Latipay;

/**
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/m/api/products")
public class MobileProductController{
	
	@Autowired
	ProductLogic productLogic;
	@Autowired
	AttachmentLogic attachmentLogic;
	@Autowired
	IPayment latipay;
	/**
	 * product search
	 * @param searchForm
	 * @return
	 */
	@RequestMapping(value={"/",""},method=RequestMethod.GET)
	public ResponseEntity<Page<Product>> productList(
			@RequestParam(value="categoryId", defaultValue="-1",required = false) Long categoryId,
			@RequestParam(value="attrValueIds", required = false) String attrValueIds,
			@ModelAttribute(value = "searchForm") SearchForm searchForm
			) {
		
		PageRequest pageable = new PageRequest(searchForm.getPage(), searchForm.getSize(),Direction.fromString(searchForm.getSort()),searchForm.getSortBy());
		
		Page<Product> productPage = null;
		JSONArray jsonArray = new JSONArray(attrValueIds);
		
		if(categoryId>0 ){
			Long[] ids = null;
			if(attrValueIds!=null ){
				ids = new Long[jsonArray.length()];
				for(int i=0;i<jsonArray.length();i++){
					ids[i] = jsonArray.getLong(i);
				}
			}
			
			productPage = productLogic.searchProductByAttrAndCategory(categoryId, ids, pageable);
		}else{
			searchForm.setStatus(0);
			productPage = productLogic.productSearch(searchForm,pageable);
		}
	
		return new ResponseEntity<Page<Product>>(productPage, HttpStatus.OK);
	}
	
	/**
	 * 产品明细
	 * @param productId
	 * @return ResponseEntity<Product>
	 */
	@RequestMapping(value={"/{productId}"},method=RequestMethod.GET)
	public  ResponseEntity<Product>  apiProductDetail(@PathVariable("productId") Long productId) {
		return new ResponseEntity<Product>(productLogic.getProdcutById(productId),HttpStatus.OK);
	}
	
	/**
	 * 汇率
	 * @param 汇率
	 * @return ResponseEntity<Product>
	 */
	@RequestMapping(value={"/rate"},method=RequestMethod.GET)
	public  ResponseEntity<Double>  apiFxRate() {
		return new ResponseEntity<Double>(latipay.getRate(),HttpStatus.OK);
	}
	
	/**
	 * 产品明细--slider show
	 * @param productId
	 * @return ResponseEntity<Product>
	 */
	@RequestMapping(value={"/{productId}/attachments"},method=RequestMethod.GET)
	public  ResponseEntity<List<Attachment>>  productDetailsSilders(@PathVariable("productId") Long productId) {
		return new ResponseEntity<List<Attachment>>(attachmentLogic.getByProductId(productId) ,HttpStatus.OK);
	}
	
	
	/**
	 * Hot
	 * @param searchForm
	 * @return
	 */
	@RequestMapping(value="/hot",method=RequestMethod.GET)
	public ResponseEntity<Page<Product>> hotProduct(@ModelAttribute(value = "searchForm") SearchForm searchForm) {
		PageRequest pageRequest = new PageRequest(searchForm.getPage(), searchForm.getSize(),Direction.fromString(searchForm.getSort()),searchForm.getSortBy());
		
		Page<Product> pageProduct = productLogic.getHotProduct(pageRequest);
		return new ResponseEntity<Page<Product>>(pageProduct, HttpStatus.OK);
	}
	
	/**
	 * recommend
	 * @param searchForm
	 * @return
	 */
	@RequestMapping(value="/recommend" , method=RequestMethod.GET)
	public ResponseEntity<Page<Product>> recommendProduct(@ModelAttribute(value = "searchForm") SearchForm searchForm) {
		PageRequest pageRequest = new PageRequest(searchForm.getPage(), searchForm.getSize(),Direction.fromString(searchForm.getSort()),searchForm.getSortBy());
		Page<Product> pageProduct = productLogic.getRecommendProduct(pageRequest);
		return new ResponseEntity<Page<Product>>(pageProduct, HttpStatus.OK);
	}
}
