package com.smsystem.entity;

public class UserList {
	private String name;
	private String condition;
	private String keeper;
	
	public UserList(String name,String condition,String keeper){
		this.name = name;
		this.condition = condition;
		this.keeper = keeper;
	}

	public String getName() {
		return name;
	}


	public String getCondition() {
		return condition;
	}

	public String getKeeper() {
		return keeper;
	}
	
}
