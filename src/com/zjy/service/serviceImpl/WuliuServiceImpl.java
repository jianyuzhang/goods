package com.zjy.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zjy.daos.WuliuDao;
import com.zjy.models.Wuliu;
import com.zjy.service.WuliuService;

@Service("wuliuService")
public class WuliuServiceImpl implements WuliuService{
 
	@Resource
	private WuliuDao wuliuDao;
	
	@Override
	public Wuliu selectByOid(String[] properties, Object[] propertyValues) {
    Wuliu wuliu = new Wuliu();
    wuliu = wuliuDao.findOneByStatementPostfix(".selectWuliuByOid", properties, propertyValues, null, null);
		return wuliu;
	}

	@Override
	public void insert(Wuliu wuliu) {
		wuliuDao.insert(wuliu);
		
	}

}
