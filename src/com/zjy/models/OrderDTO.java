package com.zjy.models;

import java.util.Date;
import java.util.List;

public class OrderDTO {
	private String oid;//主键
	private Date ordertime;//下单时间
	private double total;//总计
	private int status;//订单状态：1未付款, 2已付款但未发货, 3已发货未确认收货, 4确认收货了交易成功, 5已取消(只有未付款才能取消)
	private String address;//收货地址
	private String uname;
	private String phoneNumber;
	private List<CartItem> carts;
	public List<CartItem> getCarts() {
		return carts;
	}
	public void setCarts(List<CartItem> carts) {
		this.carts = carts;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "OrderDTO [oid=" + oid + ", ordertime=" + ordertime + ", total="
				+ total + ", status=" + status + ", address=" + address
				+ ", uname=" + uname + ", phoneNumber=" + phoneNumber
				+ ", carts=" + carts + "]";
	}
	
}
