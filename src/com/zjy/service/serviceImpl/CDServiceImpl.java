package com.zjy.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zjy.daos.CDDao;
import com.zjy.models.CD;
import com.zjy.service.CDService;

@Service("cdService")
public class CDServiceImpl implements CDService {
	/*
	 * 展示所有的CD
	 */
	@Resource
	private CDDao cdDao;
    public List<CD> showAllCDs(){
    	List<CD> cds = cdDao.selectAll();
    	return cds;
    }
    /*
     * 展示当前点击菜单对应的CD
     */
	@Override
	public List<CD> showSomeCDs(String[] properties,Object[] propertyValues) {
		// TODO Auto-generated method stub
		List<CD> cds=cdDao.findByStatementPostfix(".findSomeCDs", properties, propertyValues, null, null);
		return cds;
	}
	
	/*
	 * 分页展示
	 */
	@Override
	public List<CD> showSomeCDsByPage(String[] properties,Object[] propertyValues) {
		// TODO Auto-generated method stub
		List<CD> cds=cdDao.findByStatementPostfix(".selectListByPage", properties, propertyValues, null, null);
		return cds;
	}
	/*
	 * 获取CD的总数
	 */
	@Override
	public Integer countCDs(String propertyName, Object propertyValue) {
		// TODO Auto-generated method stub
		Integer i =cdDao.count( propertyName, propertyValue);
		return i;
	}
	@Override
	public CD showCDDetial(String cid) {
		CD cd=cdDao.selectById(cid);
		return cd;
	}
	@Override
	public void updateCD(CD cd) {
		// TODO Auto-generated method stub
		cdDao.update(cd);
	}
	@Override
	public List<CD> searchSomeCDsByPage(String[] properties,
			Object[] propertyValues) {
		List<CD> cds=cdDao.findByStatementPostfix(".searchListByPage", properties, propertyValues, null, null);
		return cds;
	}
	@Override
	public void deleteCD(String cid) {
		cdDao.deleteById(cid);
		
	}
	@Override
	public int addCD(CD cd) {
		int i =cdDao.insert(cd);
		return i ;
	}
   
}
