package com.zjy.models;


public class Menu {
	private String mid;
	private String pid;
	private String name;
	private String icon;
	private String url;
	private String flag;
	private String type;

	
	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Menu [mid=" + mid + ", pid=" + pid + ", name=" + name + ", icon="
				+ icon + ", url=" + url + ", flag=" + flag + ", type=" + type
				+ "]";
	}
}
