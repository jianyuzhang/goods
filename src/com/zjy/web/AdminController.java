package com.zjy.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zjy.models.Admin;
import com.zjy.service.AdminService;

@Controller
@RequestMapping("/operate/admin")
public class AdminController{
	
	@Resource
	private AdminService adminService;

    /**
     * 登陆
     * @throws Exception 
     */
    @RequestMapping("/login.do")
    public void Login(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	String loginName=request.getParameter("loginname");
    	String loginPass=request.getParameter("loginpass");
    	String[] properties={"loginname","loginpass"};
    	Object[] values={loginName,loginPass};
    	int i=adminService.login(properties, values);
    	System.out.println(i);
    	response.getWriter().print(i>0);
    }
    
   /**
    * 退出
    */
    @RequestMapping("/logout.do")
    public void logout(HttpServletRequest request,HttpServletResponse response){
    	request.getSession().removeAttribute("user");
    }
 
}

