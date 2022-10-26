package Cs_Db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDateTime;
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
	    
	    public void init_car(int area_id, String c_num,LocalDateTime time) {//데이터 베이스에 차량 등록 함수
	    	try {
	    		area_id +=1; 
	    		System.out.println(time);
	    		String SQL1 = "update car_info set Car_Num='"+c_num+"' where ID="+area_id+";";
	    		//System.out.println(area_id+c_num);
	    		System.out.println(SQL1);
	    		pstmt = connection.prepareStatement(SQL1);
	    		re = pstmt.executeUpdate();
	    		SQL1 ="update car_info set Parking="+1+" where ID="+area_id+";";
	    		pstmt = connection.prepareStatement(SQL1);
	    		re = pstmt.executeUpdate();
	    		
	    		SQL1 ="update car_info set Init_Time='"+time+"' where ID="+area_id+";";
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
	    public void default_create() {//최초 1회만 실행
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
	    public boolean check_parking(int i) {//데이터 베이스에서 차가 등록 되었는지 확인 하는 함수
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
	    public String get_Car_Num(int i) {//데이터 베이스에서 차 이름 받아와서 리턴
	    	
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
	    public LocalDateTime get_Init_Time(int i) {//데이터 베이스에 등록된 차량의 입차 시간 리턴
	    	LocalDateTime Init_Time= LocalDateTime.now();
	    	
	    	try {
	    		String SQL1 = "select Init_Time from car_info where id="+i+";";
	    		rs= st.executeQuery(SQL1);
	    		if(rs.next()) {
	    			Init_Time=rs.getTimestamp("Init_Time").toLocalDateTime();
	    			Init_Time =Init_Time.plusHours(-9);
	    			System.out.println(Init_Time);
		    		return Init_Time;
	    		}
	    		else {
	    			return Init_Time;
	    		}
	    	}
	    		catch(Exception e) {
		    		System.out.println(e.getMessage());
		    		return Init_Time;
		    	}
	    }
	    public void Out_car(int i) {
	    	try {
	    		String SQL1 = "update car_info set Car_Num="+"default"+" where ID="+i+";";
	    		pstmt = connection.prepareStatement(SQL1);
	    		re = pstmt.executeUpdate();
	    		SQL1 ="update car_info set Parking="+0+" where ID="+i+";";
	    		pstmt = connection.prepareStatement(SQL1);
	    		pstmt = connection.prepareStatement(SQL1);
	    		re = pstmt.executeUpdate();
	    		SQL1 ="update car_info set Init_Time="+"default"+" where ID="+i+";";
	    		pstmt = connection.prepareStatement(SQL1);
	    		re = pstmt.executeUpdate();
	    		if(rs.next()) {
	    		}
	    		else {
	    		}
	    	}
	    	catch(Exception e) {
	    		System.out.println(e.getMessage());
	    		
	    	}	
	    }
	  
	    public static void main(String[] args) {
			Surprise_Db d = new Surprise_Db();
			
			
		}   
	   

}
