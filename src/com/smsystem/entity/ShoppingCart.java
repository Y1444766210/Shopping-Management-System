package com.smsystem.entity;

public class ShoppingCart {
	private String id;
	private String name;
	private String belong;
	private String value;
	
	public ShoppingCart(String id,String name,String belong,String value){
		this.id = id;
		this.name = name;
		this.belong = belong;
		this.value = value;
	}
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getBelong() {
		return belong;
	}
	
	public String getValue() {
		return value;
	}
	
	
	
}
