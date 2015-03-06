package com.zjy.service.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zjy.daos.UserDao;
import com.zjy.models.User;
import com.zjy.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;



	@Override
	public void updatePassword(String uid, String newPass, String oldPass) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int login(String[] properties,Object[] values) {
		int i =userDao.selectByProperties(".findByNameAndPassword", properties, values);
		return i;
	}

	@Override
	public void activatioin(String code) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int ajaxValidateLoginname(String loginname) {
		String[] properties={"loginname"};
		Object[] values={loginname};
		int i=userDao.selectByProperties(".ajaxValidateLoginname", properties, values);
		return i;
	}

	@Override
	public int ajaxValidateEmail(String email) {
		String[] properties={"email"};
		Object[] values={email};
		int i=userDao.selectByProperties(".ajaxValidateEmail", properties, values);
		return i;
	}

	@Override
	public int regist(User user) {
		int i=userDao.insert(user);
		System.out.println("注册");
		return i;
	}

	
	
}
