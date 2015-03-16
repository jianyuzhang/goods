package com.zjy.daos.daoImpls;

import org.springframework.stereotype.Repository;

import com.zjy.baseDao.BaseDao;
import com.zjy.daos.CDDao;
import com.zjy.models.CD;

@Repository("cdDao")
public class CDDaoImpl extends BaseDao<CD,String>implements CDDao{

}
