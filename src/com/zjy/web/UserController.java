package com.zjy.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.itcast.commons.CommonUtils;
import com.zjy.models.User;
import com.zjy.service.UserService;

@Controller
@RequestMapping("/operate/user")
public class UserController{
	
	@Resource
	private UserService userService;

 /**
  * 登陆页面跳转到注册页面
  * @param arg0
  * @param arg1
  * @return
 * @throws IOException 
  */
    @RequestMapping("/findUser.do")
    public @ResponseBody  User index(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
    	User user =(User) request.getSession().getAttribute("user");
		return user;
    }
   
    /**
     * 登陆
     * @throws Exception 
     */
    @RequestMapping("login.do")
    public void Login(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	String loginName=request.getParameter("loginname");
    	String loginPass=request.getParameter("loginpass");
    	String[] properties={"loginname","loginpass"};
    	Object[] values={loginName,loginPass};
    	List<User> users=userService.login(properties, values);
    	int i = users.size();
    	System.out.println(i);
    	if(i>0){
    		request.getSession().setAttribute("user", users.get(0));
    	}
    	response.getWriter().print(i>0);
    }
    
   /**
    * 退出
    */
    @RequestMapping("/logout.do")
    public void logout(HttpServletRequest request,HttpServletResponse response){
    	request.getSession().removeAttribute("user");
    }
    /**
     * 验证用户名是否重名
     * @throws Exception 
     */
    @RequestMapping("/ajaxValidateLoginname.do")
    public void ajaxValidateLoginname(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	String loginName=request.getParameter("loginname");
    	System.err.println(loginName);
    	int i=userService.ajaxValidateLoginname(loginName);
    	response.getWriter().print(i>0);
    }
    /**
     * 验证邮箱是否已被注册
     * @throws Exception 
     */
    @RequestMapping("/ajaxValidateEmail.do")
    public void ajaxValidateEmail(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	String email=request.getParameter("email");
    	boolean flag=userService.ajaxValidateEmail(email)>0;
    	response.getWriter().print(flag);
    }
    /**
     * 验证验证码
     * @throws Exception 
     */
    @RequestMapping("/ajaxValidateVerifyCode.do")
    public void ajaxValidateVerifyCode(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	String verifyCode=request.getParameter("verifyCode");
    	boolean flag=((String) request.getSession().getAttribute("vCode")).equalsIgnoreCase(verifyCode);
    	response.getWriter().print(flag);
    }
    /**
     * 注册
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    
    @RequestMapping("/registUser.do")
    @ResponseBody
    public void Regist(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	User user=new User();
    	String loginname=request.getParameter("loginname");
    	String loginpass=request.getParameter("loginpass");
    	String email=request.getParameter("email");
    	String verifyCode=request.getParameter("verifyCode");
    	//boolean flag=((String) request.getSession().getAttribute("vCode")).equalsIgnoreCase(verifyCode);
    	boolean flag1=userService.ajaxValidateLoginname(loginname)<1;
    	boolean flag2=userService.ajaxValidateEmail(email)<1;
    	System.out.println(loginname+loginpass+email);
    	String uid=CommonUtils.uuid();
    	System.out.println(uid);
		boolean status=false;
		String activationCode=CommonUtils.uuid() + CommonUtils.uuid();
		user.setUid(uid);
		user.setLoginname(loginname);
		user.setLoginpass(loginpass);
		user.setEmail(email);
		user.setStatus(status);
		user.setActivationCode(activationCode);
		System.out.println(user.toString());
		int i=0;
		if(flag1&&flag2){
		i=userService.regist(user);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		if(i==0){
			map.put("data","err");
			if(!flag1){
				map.put("error", "用户名不能使用");
			}
			if(!flag2){
				map.put("error", "邮箱不能使用");
			}
			else{
				map.put("error", "注册失败");
			}
			map.put("user", user);
		}else{
			map.put("data", "success");
			map.put("success", "注册成功");
		}
		
		response.getWriter().print(JSONObject.fromObject(map));
    }
}

