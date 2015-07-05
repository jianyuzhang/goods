package com.zjy.models;

public class Wuliu {
private String wid;
private String oid;
private String fromwho;
private String  fromwhere;
private String towho;
private String towhere;
private String city;
private String phonenumber;
public String getWid() {
	return wid;
}
public void setWid(String wid) {
	this.wid = wid;
}
public String getOid() {
	return oid;
}
public void setOid(String oid) {
	this.oid = oid;
}
public String getFromwho() {
	return fromwho;
}
public void setFromwho(String fromwho) {
	this.fromwho = fromwho;
}
public String getFromwhere() {
	return fromwhere;
}
public void setFromwhere(String fromwhere) {
	this.fromwhere = fromwhere;
}
public String getTowho() {
	return towho;
}
public void setTowho(String towho) {
	this.towho = towho;
}
public String getTowhere() {
	return towhere;
}
public void setTowhere(String towhere) {
	this.towhere = towhere;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getPhonenumber() {
	return phonenumber;
}
public void setPhonenumber(String phonenumber) {
	this.phonenumber = phonenumber;
}
@Override
public String toString() {
	return "Wuliu [wid=" + wid + ", oid=" + oid + ", fromwho=" + fromwho
			+ ", fromwhere=" + fromwhere + ", towho=" + towho + ", towhere="
			+ towhere + ", city=" + city + ", phonenumber=" + phonenumber + "]";
}

}
