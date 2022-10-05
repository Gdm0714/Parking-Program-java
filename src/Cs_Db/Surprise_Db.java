package Cs_Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Surprise_Db {
	 	private String url = "jdbc:mysql://localhost:3306/car_surprise?serverTimezone=UTC";
	    private String user = "root";
	    private String password = "woals8115";
	    private String driverName = "com.mysql.cj.jdbc.Driver";
	    
	    private Connection connection;
	    private ResultSet rs;
	    private Statement st;
	    
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
	    		System.out.println("1");
	    		String SQL1 = "insert into car_info(ID,Car_Num) value("+area_id+",'"+c_num+"');";
	    		
	    		//System.out.println(area_id+c_num);
	    		System.out.println(SQL1);
	    		
	    		
	    		PreparedStatement pstmt = connection.prepareStatement(SQL1);
		    	System.out.println("e");
	    		int re = pstmt.executeUpdate();
	    		if(re == 1) {System.out.println("clear!");
	    		}
	    		else {System.out.println("retry");
	    		}
	    	}
	    	catch(Exception e) {
	    		System.err.println();
	    		System.out.println("err");
	    	}
	    	
	    }
	    
	    public static void main(String[] args) {
			Surprise_Db d = new Surprise_Db();
			
			
		}   

}