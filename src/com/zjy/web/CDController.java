package com.zjy.web;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjy.service.CDService;


@Controller
@RequestMapping("/operate/CD")
public class CDController{
	
	@Resource
	private CDService cdService;
	
 
}

