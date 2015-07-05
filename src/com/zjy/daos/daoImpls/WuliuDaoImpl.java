package com.zjy.daos.daoImpls;

import org.springframework.stereotype.Repository;

import com.zjy.baseDao.BaseDao;
import com.zjy.daos.WuliuDao;
import com.zjy.models.Wuliu;
@Repository("wuliuDao")
public class WuliuDaoImpl extends BaseDao<Wuliu,String> implements WuliuDao {

}
