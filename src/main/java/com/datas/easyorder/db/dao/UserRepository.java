package com.datas.easyorder.db.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.datas.easyorder.db.entity.User;

@Component("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {
	 
	public static final String STATUS_SUPER_ADMIN = "Superadmin";
	public static final String STATUS_ADMIN = "Admin";
	public static final String STATUS_STAFF = "Staff";
	public static final String STATUS_CANCELED = "Canceled";
	
	public User findOneByEmailAndPasswordAndStatus(String email, String md5String,String status);

	public Page<User> findByNameOrEmailOrPhone(String name, String email,String phone, Pageable pageable);

	public Page<User> findAll(Specification<User> searchSpecification, Pageable pageable);

	public User findOneByEmail(String registerEmail);

	public User findOneByEmailAndStatusNot(String registerEmail,String status);
}
