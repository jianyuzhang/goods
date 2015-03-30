package com.zjy.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zjy.daos.CDDao;
import com.zjy.models.CD;
import com.zjy.service.CDService;

@Service("cdService")
public class CDServiceImpl implements CDService {
	
	@Resource
	private CDDao cdDao;
    public List<CD> showAllCDs(){
    	List<CD> cds = cdDao.selectAll();
    	return cds;
    }
	@Override
	public List<CD> showSomeCDs(String[] properties,Object[] propertyValues) {
		// TODO Auto-generated method stub
		List<CD> cds=cdDao.findByStatementPostfix(".findSomeCDs", properties, propertyValues, null, null);
		return cds;
	}
   
}
