package com.zjy.web;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zjy.models.Menu;
import com.zjy.models.MenuDTO;
import com.zjy.service.MenuService;


@Controller
@RequestMapping("/operate/menu")
public class MenuController{
	
	@Resource
	private MenuService menuService;
	
    @RequestMapping("/showMenus.do")
    public void showMenus(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	System.out.println("111");
    	List<Menu> parents=menuService.findParentMenus();
    	List<MenuDTO> MenuDTOParents=new ArrayList<MenuDTO>();
        for(Menu parent: parents){
        	MenuDTO menuDto=new MenuDTO();
        	menuDto.setId(parent.getMid());
        	menuDto.setName(parent.getName());
        	menuDto.setPid(parent.getPid());
        	menuDto.setIcon(parent.getIcon());
        	menuDto.setUrl(parent.getUrl());
        	menuDto.setFlag(parent.getFlag());
        	menuDto.setType(parent.getType());
        	String[] properties1={"pid"};
        	Object[] values1={parent.getMid()};
        	List<Menu> children=menuService.findChildMenus(properties1, values1);
        	menuDto.setChildren(children);
        	MenuDTOParents.add(menuDto);
        }
        response.getWriter().print(JSONArray.fromObject(MenuDTOParents));
		}
}

