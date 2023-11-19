package com.smsystem.entity;

public class SMS_PRODUCTS {
	static private String PDT_Logid;
	private String PDT_ID;
	private String PDT_NAME;
	private String PDT_PRICE;
	public SMS_PRODUCTS(String PDT_Id,String PDT_NAME,String PDT_PRICE) {
		PDT_ID = PDT_Id;
		this.PDT_NAME = PDT_NAME;
		this.PDT_PRICE = PDT_PRICE;
	}
	public SMS_PRODUCTS() {}
	public String getID() {
		return PDT_ID;
	}
	public void setID(String id) {
		PDT_ID = id;
	}
	public String getName() {
		return PDT_NAME;
	}
	public void setName(String name) {
		PDT_NAME = name;
	}
	public String getPrice() {
		return PDT_PRICE;
	}
	public void setPrice(String price) {
		PDT_PRICE = price;
	}
	public String getLogid()
	{
		return PDT_Logid;
	}
	public void setLogid(String ld) {
		PDT_Logid = ld;
	}
}
