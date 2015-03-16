package com.zjy.service.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zjy.daos.CDDao;
import com.zjy.service.CDService;

@Service("cdService")
public class CDServiceImpl implements CDService {
	
	@Resource
	private CDDao cdDao;

   
}
