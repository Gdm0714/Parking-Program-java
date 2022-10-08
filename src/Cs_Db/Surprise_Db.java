package Cs_Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
public class Surprise_Db {
	 	private String url = "jdbc:mysql://localhost:3306/car_surprise?serverTimezone=UTC";
	    private String user = "root";
	    private String password = "woals8115";
	    private String driverName = "com.mysql.cj.jdbc.Driver";
	    
	    private Connection connection;
	    private ResultSet rs;
	    private Statement st;
	    int re;
		PreparedStatement pstmt;
	    public void connect() {
			try {
				Class.forName(driverName);
				connection = DriverManager.getConnection(url, user, password);
				st = connection.createStatement();
				System.out.println("[Connection suceesful!]");

			} catch (ClassNotFoundException e) {
				System.out.println("[로드 오류]\n" + e.getStackTrace());
			} catch (SQLException e) {
				System.out.println("[연결 오류]\n" + e.getStackTrace());
			}
		}
	    
	    public void init_car(int area_id, String c_num) {//area_num 주차할 공간 인덱스 
	    	try {
	    		area_id +=1; 
	    		System.out.println("1");
	    		String SQL1 = "update car_info set Car_Num='"+c_num+"' where ID="+area_id+";";
	    		//System.out.println(area_id+c_num);
	    		System.out.println(SQL1);
	    		pstmt = connection.prepareStatement(SQL1);
	    		re = pstmt.executeUpdate();
	    		SQL1 ="update car_info set Parking="+1+" where ID="+area_id+";";
	    		pstmt = connection.prepareStatement(SQL1);
	    		re = pstmt.executeUpdate();
	    		
	    		if(re == 1) {System.out.println("clear!");
	    		}
	    		else {System.out.println("retry");
	    		}
	    	
	    	}
	    	catch(Exception e) {
	    		
	    		System.out.println("err");
	    		
	    	}
	    	
	    }
	    public void default_create() {
	    	try {
	    		
	    		System.out.println("1");
	    		for(int i=1;i<13;i++) {
	    			String SQL1 = "insert into car_info (ID,Parking) values ("+i+","+0+");";
		    		//System.out.println(area_id+c_num);
		    		System.out.println(SQL1);
		    		pstmt = connection.prepareStatement(SQL1);
		    		re = pstmt.executeUpdate();
		    		System.out.println(SQL1);
		    		if(re == 1) {System.out.println("clear!");
		    		}
		    		else {System.out.println("retry");
		    		}
		    		
		    		
	    		}
	    			
	    	}
	    	catch(Exception e) {
	    		
	    		System.out.println("err");
	    	}
	    	
	    }
	    public boolean set_default(int i) {
	    	try {
	    		String SQL1 = "select Parking from car_info where id="+i+";";
	    		//System.out.println(area_id+c_num);
	    		System.out.println(SQL1);
	    		rs= st.executeQuery(SQL1);
	    		if(rs.next()) {
	    			int rt_value =rs.getInt("Parking");
		    		if(rt_value == 1) {return true;}
		    		else return false;
	    		}
	    		else {return false;}
	    		
	    			
	    	}
	    	catch(Exception e) {
	    		System.out.println("err");
	    		return false;
	    	}
    		
	    }
	    public String getCar_Num(int i) {
	    	
	    	try {
	    		String SQL1 = "select Car_Num from car_info where id="+i+";";
	    		rs= st.executeQuery(SQL1);
	    		if(rs.next()) {
	    			String car_num=rs.getString("Car_Num");
		    		return car_num;
	    		}
	    		else {
	    			return " ";
	    		}
	    	}
	    	catch(Exception e) {
	    		System.out.println(e.getMessage());
	    		return " ";
	    	}	
	    }
	    public LocalTime lt() {
	    	LocalTime l =  LocalTime.now();
	    	
	    	return l;
	    }
	  
	    public static void main(String[] args) {
			Surprise_Db d = new Surprise_Db();
			
			
		}   

}