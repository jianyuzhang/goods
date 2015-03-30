package com.zjy.service;

import java.util.List;

import com.zjy.models.CD;


public interface CDService {
	  public List<CD> showAllCDs();
	  public List<CD> showSomeCDs(String[] properties,Object[] propertyValues);
}