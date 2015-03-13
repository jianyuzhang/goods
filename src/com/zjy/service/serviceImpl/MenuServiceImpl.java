package com.zjy.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zjy.daos.MenuDao;
import com.zjy.models.Menu;
import com.zjy.service.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService {
	
	@Resource
	private MenuDao menuDao;

    public List<Menu> findParentMenus(){
    	List<Menu> parents =new ArrayList<Menu>();
    	parents=menuDao.selectAll();
		return parents;
		
    }
	public List<Menu> findChildMenus(String[] properties,Object[] propertyValues){
		List<Menu> children =new ArrayList<Menu>();
		children = menuDao.findByStatementPostfix(".findChildMenus", properties, propertyValues, null, null);
		return children;
	}
}
