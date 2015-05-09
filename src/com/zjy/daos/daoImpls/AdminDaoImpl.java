package com.zjy.daos.daoImpls;

import org.springframework.stereotype.Repository;

import com.zjy.baseDao.BaseDao;
import com.zjy.daos.AdminDao;
import com.zjy.models.Admin;

@Repository("adminDao")
public class AdminDaoImpl extends BaseDao<Admin,String> implements AdminDao{
		
}
