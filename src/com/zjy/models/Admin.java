package com.zjy.models;

public class Admin {
	// 对应数据库表
		private String aid;//主键
		private String loginname;//登录名
		private String loginpass;//登录密码
		public String getAid() {
			return aid;
		}
		public void setAid(String aid) {
			this.aid = aid;
		}
		public String getLoginname() {
			return loginname;
		}
		public void setLoginname(String loginname) {
			this.loginname = loginname;
		}
		public String getLoginpass() {
			return loginpass;
		}
		public void setLoginpass(String loginpass) {
			this.loginpass = loginpass;
		}
		@Override
		public String toString() {
			return "Admin [aid=" + aid + ", loginname=" + loginname
					+ ", loginpass=" + loginpass + "]";
		}
		
		
}
