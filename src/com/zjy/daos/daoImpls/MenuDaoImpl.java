package com.zjy.daos.daoImpls;

import org.springframework.stereotype.Repository;

import com.zjy.baseDao.BaseDao;
import com.zjy.daos.MenuDao;
import com.zjy.models.Menu;

@Repository("menuDao")
public class MenuDaoImpl extends BaseDao<Menu,String>implements MenuDao{

}
