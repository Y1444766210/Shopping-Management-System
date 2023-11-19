package com.smsystem.entity;

public class KeeperList {
	private String name;
	private String condition;
	private String belong;
	
	public KeeperList(String name,String condition,String belong){
		this.name = name;
		this.condition = condition;
		this.belong = belong;
	}

	public String getName() {
		return name;
	}

	public String getCondition() {
		return condition;
	}

	public String getBelong() {
		return belong;
	}
	
}
