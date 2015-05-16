package com.zjy.models;

public class Money {
private String mid;//主键
private String uid;//外键用户id
private int num;//帐户余额
public String getMid() {
	return mid;
}
public void setMid(String mid) {
	this.mid = mid;
}
public String getUid() {
	return uid;
}
public void setUid(String uid) {
	this.uid = uid;
}
public int getNum() {
	return num;
}
public void setNum(int num) {
	this.num = num;
}
@Override
public String toString() {
	return "money [mid=" + mid + ", uid=" + uid + ", num=" + num + "]";
}

}
