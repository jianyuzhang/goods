package com.zjy.web;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjy.service.MenuService;


@Controller
@RequestMapping("/operate/menu")
public class MenuController{
	
	@Resource
	private MenuService menuService;
	
 
}

