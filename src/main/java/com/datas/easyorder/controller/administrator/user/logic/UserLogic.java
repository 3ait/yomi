package com.datas.easyorder.controller.administrator.user.logic;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.datas.easyorder.controller.BaseLogic;
import com.datas.easyorder.controller.administrator.user.UserView;
import com.datas.easyorder.db.dao.UserCompanyRepository;
import com.datas.easyorder.db.dao.UserRepository;
import com.datas.easyorder.db.dao.UserSpecifications;
import com.datas.easyorder.db.entity.User;
import com.datas.easyorder.db.entity.UserCompany;
import com.datas.utils.SearchForm;
import com.plugin.utils.Md5;

@Component("userLogic")
@Service
public class UserLogic extends BaseLogic<User>{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserCompanyRepository userCompanyRepository;
	



	public User add(User user) {
		return userRepository.save(user);
	}
	
	public User findOneById(Long id) {
		User user = userRepository.findOne(id);
		return user;
	}


	public User update(User user) {
		return userRepository.save(user);
	}

	
	public Page<User> findAll(SearchForm searchForm , Pageable pageable) {
		return userRepository.findAll(UserSpecifications.getSearchSpecification(searchForm), pageable);
	}


	public List<UserCompany> findUserCompanyByName(String name) {
		return null;
	}

	/**
	 * USER LOGIN
	 * @param email
	 * @param md5String
	 * @return
	 */
	public User adminLogin(String email, String password,String status) {
		
		return userRepository.findOneByEmailAndPasswordAndStatus(email,Md5.getMd5String(password),status);
	}

	@Override
	public CrudRepository<User, Long> getRepository() {
		return userRepository;
	}

	/**
	 * email 检查
	 * @param registerEmail
	 * @return
	 */
	public User findByEmail(String registerEmail) {
		return userRepository.findOneByEmail(registerEmail);
	}

	/**
	 * 保存用户
	 * @param userview
	 * @return
	 */
	public User saveUser(UserView userview) {
		
		User user = new User();
		user.setEmail(userview.getEmail());
		user.setName(userview.getName());
		user.setPassword(Md5.getMd5String(userview.getPassword()));
		user.setPhone(userview.getPhone());
		user.setStatus(userview.getStatus());
		user.setType(userview.getType());
		user.setUserCompany(userview.getUserCompany());
		user.setUserGroup(userview.getUserGroup());
		user.setCreateTime(Calendar.getInstance().getTime());
		user.setModifyTime(Calendar.getInstance().getTime());
		return userRepository.save(user);
	}
	
}
