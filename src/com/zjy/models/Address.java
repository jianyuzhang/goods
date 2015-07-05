package com.zjy.models;

public class Address {
 private String aid;//主键
 private String uid;//用户
 private String uname;//收件人姓名
 private String phoneNumber;//手机号码
 private String address;//地址
 private int status;//是否为默认
public String getAid() {
	return aid;
}
public void setAid(String aid) {
	this.aid = aid;
}
public String getUid() {
	return uid;
}
public void setUid(String uid) {
	this.uid = uid;
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
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
@Override
public String toString() {
	return "Address [aid=" + aid + ", uid=" + uid + ", uname=" + uname
			+ ", phoneNumber=" + phoneNumber + ", address=" + address
			+ ", status=" + status + "]";
}
 
}
