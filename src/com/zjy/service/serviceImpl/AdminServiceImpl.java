package com.zjy.service.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zjy.daos.AdminDao;
import com.zjy.service.AdminService;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
	
	@Resource
	private AdminDao adminDao;

	@Override
	public int login(String[] properties, Object[] values) {
		int  i = adminDao.selectByProperties(".findAdminByNameAndPassword", properties, values);
		return i;
	}

}
