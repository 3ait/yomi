package com.datas.easyorder.controller.web.index;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.controller.administrator.content.logic.ContentLogic;
import com.datas.easyorder.controller.administrator.product.logic.ProductLogic;
import com.datas.easyorder.db.entity.Attachment;
import com.datas.easyorder.db.entity.Product;
import com.datas.utils.SearchForm;

/**
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/api/web")
public class ApiIndexController extends BaseController {

	@Autowired
	ProductLogic productLogic;
	@Autowired
	ContentLogic contentLogic;

	@RequestMapping("/slider/{title}")
	public ResponseEntity<List<Attachment>> getSilder(@PathVariable("title") String title) {

		Pageable pageable = new PageRequest(0, Integer.MAX_VALUE, Direction.ASC, "position");
		List<Attachment> list = contentLogic.getAttachmentByTitle(title,pageable);

		return new ResponseEntity<List<Attachment>>(list, HttpStatus.OK);
	}
	
	

	@RequestMapping("/product/search")
	public ResponseEntity<Page<Product> > productList(@PathVariable("categoryId") Long categoryId,
			@ModelAttribute SearchForm searchform) {

		searchform.setSortBy("productName");
		searchform.setSort("ASC");
		Pageable pageable = new PageRequest(searchform.getPage()-1<1?0:searchform.getPage()-1, searchform.getSize(),Direction.fromString(searchform.getSort()),searchform.getSortBy());
		Page<Product> productPage = productLogic.getProductByMenuId(categoryId, pageable);


		return new ResponseEntity<Page<Product>>(productPage, HttpStatus.OK);
	}
}
