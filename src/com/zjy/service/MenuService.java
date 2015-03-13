package com.zjy.service;

import java.util.List;

import com.zjy.models.Menu;


public interface MenuService {
	   public List<Menu> findParentMenus();
	   public List<Menu> findChildMenus(String[] properties,Object[] values);
}