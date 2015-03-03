package com.zjy.daos.daoImpls;

import org.springframework.stereotype.Repository;

import com.zjy.baseDao.BaseDao;
import com.zjy.daos.UserDao;
import com.zjy.models.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDao<User,String> implements UserDao{
		
}
