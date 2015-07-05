package com.zjy.daos.daoImpls;

import org.springframework.stereotype.Repository;

import com.zjy.baseDao.BaseDao;
import com.zjy.daos.CDInOrderDao;
import com.zjy.models.CDInOrder;
@Repository("cdInOrderDao")
public class CDInOrderDaoImpl extends BaseDao<CDInOrder, String> implements CDInOrderDao{

}
