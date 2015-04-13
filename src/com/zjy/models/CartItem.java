package com.zjy.models;

public class CartItem {
	private String cartItemId;// 主键
	private int quantity;// 数量
	private String uid;//对应用户的id
	private String cid;//对应cd的id
	private String cname;//CD名
	private String singer;//作者
	private double price;//定价
	private double currPrice;//当前价
	private String image_b;//小图路径
	
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
	public String getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(String cartItemId) {
		this.cartItemId = cartItemId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	@Override
	public String toString() {
		return "CartItem [cartItemId=" + cartItemId + ", quantity=" + quantity
				+ ", uid=" + uid + ", cid=" + cid + ", cname=" + cname
				+ ", singer=" + singer + ", price=" + price + ", currPrice="
				+ currPrice + ", image_b=" + image_b + "]";
	}
	
}
