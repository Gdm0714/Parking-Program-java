package team_car_surprise;
import java.awt.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class Car_Surpirse_Db {
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
	    
	    
	    public void Enrollment() {
	    	try {
	    			String SQL1 = "INSERT INTO car_info(ID) VALUE('1234');";
		    		PreparedStatement pstmt = connection.prepareStatement(SQL1);
		    		int re = pstmt.executeUpdate();
		    		if(re == 1) {System.out.println("clear!");
		    		}
		    		else {System.out.println("retry");
		    		}
	    		}
	    		
	    	catch(Exception e) {
	    		System.out.println(e.getMessage());
	    	}
	    }
	    
	   
	    public static void main(String[] args) {
			Car_Surpirse_Db d = new Car_Surpirse_Db();
			d.connect();
			d.Enrollment();
			
		}   

}