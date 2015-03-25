package com.zjy.models;

import java.util.List;


public class MenuDTO {
	private String mid;
	private String pid;//对应一级菜单id
	private String name;
	private String icon;
	private String url;
	private String flag;
	private String type;
    private List<Menu> children;//二级菜单list
	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}

	public String getId() {
		return mid;
	}

	public void setId(String mid) {
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
		return "MenuDTO [mid=" + mid + ", pid=" + pid + ", name=" + name
				+ ", icon=" + icon + ", url=" + url + ", flag=" + flag
				+ ", type=" + type + ", children=" + children + "]";
	}

	
}
