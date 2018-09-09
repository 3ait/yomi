package com.datas.easyorder.controller.administrator.setting.logic;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.datas.easyorder.controller.BaseLogic;
import com.datas.easyorder.db.dao.UserCompanyRepository;
import com.datas.easyorder.db.entity.UserCompany;

@Component
@Service
public class SettingLogic extends BaseLogic<UserCompany>{

	@Autowired
	private UserCompanyRepository userCompanyRepository;
	
	

	@Override
	public CrudRepository<UserCompany,Long> getRepository() {
		return userCompanyRepository;
	}


	/**
	 * getUserCompany
	 * @return UserCompany
	 */
	public UserCompany getUserCompany() {
		UserCompany userCompany =  userCompanyRepository.findAll().get(0);
		
		return userCompany;
	}


	@Transactional(rollbackOn=Exception.class)
	public UserCompany save(UserCompany userCompany) {

		UserCompany userCompanyDb = userCompanyRepository.findOne(userCompany.getId());
		
		BeanUtils.copyProperties(userCompany, userCompanyDb);
		userCompanyDb.setCreateTime(Calendar.getInstance().getTime());
		userCompanyRepository.save(userCompanyDb);
		return userCompanyDb;
	}

		
}
