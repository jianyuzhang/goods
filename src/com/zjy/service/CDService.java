package com.zjy.service;

import java.util.List;

import com.zjy.models.CD;


public interface CDService {
	  public List<CD> showAllCDs();
	  public List<CD> showSomeCDs(String[] properties,Object[] propertyValues);
	  public List<CD> showSomeCDsByPage(String[] properties,Object[] propertyValues);
	  public List<CD> searchSomeCDsByPage(String[] properties,Object[] propertyValues);
	  public Integer countCDs(String propertyName, Object propertyValue);
	  public CD showCDDetial(String cid);
	  public void updateCD(CD cd);
	  public void deleteCD(String cid);
	  public int addCD(CD cd);
}