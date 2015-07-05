package com.zjy.service;

import java.util.List;

import com.zjy.models.User;


public interface UserService {
	/**
	 * 修改密码
	 * @param uid
	 * @param newPass
	 * @param oldPass
	 * @throws UserException 
	 */
	public void updatePassword(String uid, String newPass, String oldPass) ;
	
	/**
	 * 登录功能
	 * @param user
	 * @return
	 */
	public List<User> login(String[] properties,Object[] values) ;
	
	/**
	 * 激活功能
	 * @param code
	 * @throws UserException 
	 */
	public void activatioin(String code) ;
		/*
		 * 1. 通过激活码查询用户
		 * 2. 如果User为null，说明是无效激活码，抛出异常，给出异常信息（无效激活码）
		 * 3. 查看用户状态是否为true，如果为true，抛出异常，给出异常信息（请不要二次激活）
		 * 4. 修改用户状态为true
		 */
		
	
	/**
	 * 用户名注册校验
	 * @param loginname
	 * @return
	 */
	public int ajaxValidateLoginname(String loginname);
	/**
	 * Email校验
	 * @param email
	 * @return
	 */
	public int ajaxValidateEmail(String email) ;
	
	/**
	 * 注册功能
	 * @param user
	 */
	public int regist(User user) ;
}