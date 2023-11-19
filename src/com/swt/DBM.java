package com.swt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBM {
	static final String urlString = "jdbc:sqlserver://localhost:1433;DatabaseName=SMSystem;encrypt=false";
	static final String us = "sa";
	static final String pw = "123";
	static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static {
		try {
			//创建驱动
			Class.forName(driver);
		} catch (Exception e) {
			//处理异常
			e.printStackTrace();
		}
	}//静态驱动
    static private String getUnit(String args1, int args2) throws ClassNotFoundException {
    	try {
    			Class.forName(driver);
    			Connection con = DriverManager.getConnection(urlString,us,pw);
    			Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(args1);
                rs.next();
                return rs.getString(args2);
            } catch (SQLException ignored) {
                //ignored.printStackTrace();
                return "empty";
            }
    }//获取表中某一属性的信息
    

    	public static Connection getcon() {
    		//定义连接
    		Connection con = null;
    		try {
    			//连接驱动
    			con = DriverManager.getConnection(urlString, us, pw);
    		} catch (Exception e) {
    			//处理异常
    			e.printStackTrace();
    		}
    		//返回连接
    		return con;
    	
    }
    
    static boolean getAuth(String acc, String pwd) throws ClassNotFoundException {
        return (getUnit("SELECT * FROM SMS_USER WHERE USER_ID = '" + acc + "';", 2).equals(acc)
                && getUnit("SELECT * FROM SMS_USER WHERE USER_PSW = '" + pwd + "';", 3).equals(pwd));
    }//获取登陆认证
    
    static boolean getBAuth(String acc, String pwd) throws ClassNotFoundException {
        return (getUnit("SELECT * FROM SMS_BUSINESS WHERE BSS_ID = '" + acc + "';", 2).equals(acc)
                && getUnit("SELECT * FROM SMS_BUSINESS WHERE BSS_PSW = '" + pwd + "';", 3).equals(pwd));
    }//获取商家登陆认证
    
    static private boolean isExistUser_name(String us) throws ClassNotFoundException {
        return getUnit("SELECT * FROM SMS_USER WHERE USER_NAME = " + us + ";", 1).equals(us);
    }
    static private boolean isExistBUser_name(String us) throws ClassNotFoundException {
        return getUnit("SELECT * FROM SMS_BUSINESS WHERE BSS_NAME = " + us + ";", 1).equals(us);
    }
    static private boolean isExistUser_id(String id) throws ClassNotFoundException {
        return getUnit("SELECT * FROM SMS_USER WHERE USER_ID = " + id + ";", 2).equals(id);
    }
    static private boolean isExistBUser_id(String id) throws ClassNotFoundException {
        return getUnit("SELECT * FROM SMS_BUSINESS WHERE BSS_ID = " + id + ";", 2).equals(id);
    }
    
    static String addUser(String[] temp) throws ClassNotFoundException, SQLException {
        if (isExistUser_name(temp[0])) return "该用户已存在";
        if (isExistUser_id(temp[1])) return "该用户已存在";
        for (String x:temp){
            if (null == x) return "必要信息未填写完整。";
        }
        String arg = "INSERT INTO SMS_USER "+"VALUES('"+temp[0]+"','"+temp[1]+"','"+temp[2]+"','"+temp[3]+"','"+temp[4]+"');";
        Class.forName(driver);
    	Connection con = DriverManager.getConnection(urlString,us,pw);
    	Statement stmt = con.createStatement();
        stmt.executeUpdate(arg);
        return "注册成功";
    }//注册用户
    
    static String addBUser(String[] temp) throws ClassNotFoundException, SQLException {
        if (isExistBUser_name(temp[0])) return "该商家已存在";
        if (isExistBUser_id(temp[1])) return "该商家已存在";
        for (String x:temp){
            if (null == x) return "必要信息未填写完整。";
        }
        String arg = "INSERT INTO SMS_BUSINESS "+"VALUES('"+temp[0]+"','"+temp[1]+"','"+temp[2]+"');";
        Class.forName(driver);
    	Connection con = DriverManager.getConnection(urlString,us,pw);
    	Statement stmt = con.createStatement();
        stmt.executeUpdate(arg);
        return "注册成功";
    }//注册商家用户
    
    static String[][] getProduct(String pdt) throws ClassNotFoundException {
        try {
        	String[][] x = new String[12][100];
            int temp = 0;
        	Class.forName(driver);
        	Connection con = DriverManager.getConnection(urlString,us,pw); Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SMS_PRODUCTS WHERE PDT_NAME LIKE '%"+pdt+"%';");
            while (rs.next()) {
                for (int i = 0; i < 4; i++) 
                    x[temp][i] = rs.getString(i + 1);
                ++temp;
            }
            return x;
           
        } catch (SQLException ignored) {
            //ignored.printStackTrace();
        	System.out.println("1");
            return new String[][]{{"empty"}};
        }
    }//搜索物品
    

    public static void getclose(Connection con,PreparedStatement ps,ResultSet rs) {
		try {
			if(con!=null) {//关闭连接
				con.close();
			}
			if(ps!=null) {//关闭执行对象
				ps.close();
			}
			if(rs!=null) {//关闭结果集
				rs.close();
			}
		} catch (Exception e) {
			//处理异常
			e.printStackTrace();
		}
	}
    
    public static String getName(String sql) throws ClassNotFoundException
    {
    	return(getUnit(sql,2));
    }
    public static String getPrice(String sql) throws ClassNotFoundException {
    	return(getUnit(sql,3));
    }
    public static String getID(String sql) throws ClassNotFoundException {
    	return(getUnit(sql,1));
    }
    public static String getUName(String sql) throws ClassNotFoundException
    {
    	return(getUnit(sql,1));
    }
    public static String getKeeper(String sql) throws ClassNotFoundException
    {
    	return(getUnit(sql,4));
    }
    
    public static String addSHcart(String[] temp) throws ClassNotFoundException, SQLException {
        String arg = "INSERT INTO SMS_SHCART "+"VALUES('"+temp[0]+"','"+temp[1]+"','"+temp[2]+"','"+temp[3]+"','"+temp[4]+"');";
        Class.forName(driver);
    	Connection con = DriverManager.getConnection(urlString,us,pw);
    	Statement stmt = con.createStatement();
        stmt.executeUpdate(arg);
        return "加入购物车成功";
    }
    public static void changepsw(String sql) throws ClassNotFoundException, SQLException {
    	Class.forName(driver);
    	Connection con = null;
		try {
			con = DriverManager.getConnection(urlString,us,pw);
	    	Statement stmt = con.createStatement();
	        stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
	
