package com.datas.easyorder.controller.mobile.index;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.datas.easyorder.controller.administrator.content.logic.ContentLogic;
import com.datas.easyorder.controller.administrator.customer.logic.CustomerLogic;
import com.datas.easyorder.controller.administrator.product.logic.ProductLogic;
import com.datas.easyorder.db.dao.UserCompanyRepository;
import com.datas.easyorder.db.entity.Attachment;
import com.datas.easyorder.db.entity.Customer;
import com.datas.easyorder.db.entity.UserCompany;
import com.plugin.mail.logic.SendMail;
import com.plugin.utils.Md5;

/**
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/m/api/index")
public class MobileIndexApi {

	@Autowired
	ProductLogic productLogic;
	@Autowired
	ContentLogic contentLogic;
	@Autowired
	SendMail sendMail;
	@Autowired
	UserCompanyRepository userCompanyRepository;
	@Autowired
	CustomerLogic customerLogic;
	@Value("${project.name}")
	String projectName;
	
	
	
	/**
	 * Slider show
	 * @param title
	 * @return
	 */
	@RequestMapping(value="/silder/{title}", method= RequestMethod.GET)
	public ResponseEntity<List<Attachment>> getSilder(@PathVariable("title") String title) {

		Pageable pageable = new PageRequest(0, Integer.MAX_VALUE, Direction.ASC, "position");
		List<Attachment> list = contentLogic.getAttachmentByTitle(title,pageable);

		return new ResponseEntity<List<Attachment>>(list, HttpStatus.OK);
	}
	
	/**
	 * 找回密码
	 * @return Boolean
	 */
	@RequestMapping(value={"/password/retrieve/email"}, method= RequestMethod.GET)
	@ResponseBody
	public Boolean retrieve(@RequestParam("email")String email) {
		
		boolean ret = false;
		UserCompany userCompany = userCompanyRepository.findAll().get(0);
		sendMail.init(userCompany, email);
		
		String newPassword = Md5.getMd5String(Calendar.getInstance().getTimeInMillis()+"").substring(0, 10);
		String content = projectName + " update password:" + newPassword;
		try {
			
			Customer customer = customerLogic.findByEmail(email);
			if(customer!=null){
				ret = sendMail.send(projectName + " update password:", content);
				if(ret){
					customerLogic.update(customer.getId(), "password", newPassword);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ret;
	} 

}
