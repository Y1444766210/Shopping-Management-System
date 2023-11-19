package com.smsystem.entity;

public class SMS_USER {
	private static String userName;
	private String userPwd;
	private String payPwd;
	private static String phone;
	private static String Logname;
	public void setName(String name) {
		userName = name;
	}
	public String getName() {
		return userName;
	}
	public String getphone()
	{
		return phone;
	}
	public void setphone(String pho) {
		phone = pho;
	}
	public String getLogname()
	{
		return Logname;
	}
	public void setLogname(String logn) {
		Logname = logn;
	}
}
