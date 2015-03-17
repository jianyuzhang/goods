package com.zjy.web;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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
    public ModelAndView main(){
    	ModelAndView mv=new ModelAndView("main.html");
    	List<Menu> parents=menuService.findParentMenus();
    	List<MenuDTO> DTOParents=new ArrayList<MenuDTO>();
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
        	DTOParents.add(menuDto);
        }
        mv.addObject("menus", JSONArray.fromObject(DTOParents));
		return mv;
		}
}

