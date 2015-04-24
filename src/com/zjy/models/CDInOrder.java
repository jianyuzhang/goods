package com.zjy.models;

public class CDInOrder {
	private String coid;//主键
	private String cname;//CD名
	private String singer;//歌手
	private double price;//定价
	private double currPrice;//当前价
	private String image_b;//小图路径
	private int sum;//数量
	private String oid;//对应订单的id（外键）
	public String getCoid() {
		return coid;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum; 
	}
	public void setCoid(String coid) {
		this.coid = coid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getCurrPrice() {
		return currPrice;
	}
	public void setCurrPrice(double currPrice) {
		this.currPrice = currPrice;
	}
	public String getImage_b() {
		return image_b;
	}
	public void setImage_b(String image_b) {
		this.image_b = image_b;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	@Override
	public String toString() {
		return "CDInOrder [coid=" + coid + ", cname=" + cname + ", singer="
				+ singer + ", price=" + price + ", currPrice=" + currPrice
				+ ", image_b=" + image_b + ", sum=" + sum + ", oid=" + oid
				+ "]";
	}

}
