package com.zjy.models;

public class CD {
	private String cid;//主键
	private String cname;//CD名
	private String singer;//作者
	private double price;//定价
	private double currPrice;//当前价
	private double discount;//折扣
	private String press;//出版社
	private String publishtime;//出版时间
	private int edition;//版次
	private String printtime;//刷新时间
	private String mid;//所属菜单id
	private String image_w;//大图路径
	private String image_b;//小图路径
	private int orderBy;//顺序
	private int sum;//库存数量
	private String language;//语种
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
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
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public String getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}
	public int getEdition() {
		return edition;
	}
	public void setEdition(int edition) {
		this.edition = edition;
	}
	public String getPrinttime() {
		return printtime;
	}
	public void setPrinttime(String printtime) {
		this.printtime = printtime;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getImage_w() {
		return image_w;
	}
	public void setImage_w(String image_w) {
		this.image_w = image_w;
	}
	public String getImage_b() {
		return image_b;
	}
	public void setImage_b(String image_b) {
		this.image_b = image_b;
	}
	public int getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	@Override
	public String toString() {
		return "CD [cid=" + cid + ", cname=" + cname + ", singer=" + singer
				+ ", price=" + price + ", currPrice=" + currPrice
				+ ", discount=" + discount + ", press=" + press
				+ ", publishtime=" + publishtime + ", edition=" + edition
				+ ", printtime=" + printtime + ", mid=" + mid + ", image_w="
				+ image_w + ", image_b=" + image_b + ", orderBy=" + orderBy
				+ ", sum=" + sum + ", language=" + language + "]";
	}
	
}
