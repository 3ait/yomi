package com.datas.easyorder.controller.administrator.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datas.easyorder.controller.BaseController;
import com.datas.easyorder.db.entity.User;

/**
 * 
 * @author leo
 *
 */
@RestController
@RequestMapping("/api/management/user")
public class ApiUserController extends BaseController{

	@RequestMapping(value={"/",""})
	public ResponseEntity<User> getUser(){
		return new ResponseEntity<User>(getLognUser(),HttpStatus.OK);
	}
}
