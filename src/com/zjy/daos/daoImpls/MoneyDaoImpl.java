package com.zjy.daos.daoImpls;

import org.springframework.stereotype.Repository;

import com.zjy.baseDao.BaseDao;
import com.zjy.daos.MoneyDao;
import com.zjy.models.Money;

@Repository("moneyDao")
public class MoneyDaoImpl extends BaseDao<Money,String> implements MoneyDao {


}
