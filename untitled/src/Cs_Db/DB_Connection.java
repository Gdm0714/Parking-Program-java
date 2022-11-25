package Cs_Db;

import java.sql.*;

public class DB_Connection {
    private static int cnt = 0;
    private Connection connection;
    private ResultSet rs;
    private Statement st;
    private String url = "jdbc:mysql://localhost:3306/login?serverTimezone=UTC";
    private String user = "root";
    private String password = "woals8115";
    private String driverName = "com.mysql.cj.jdbc.Driver";

    /** DB 연결 */
    public void connect() {
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, user, password);
            st = connection.createStatement();
            if (cnt == 0) {
                System.out.println("[Connection successful!]");
                cnt++;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("[로드 오류]\n" + e.getStackTrace());
        } catch (SQLException e) {
            System.out.println("[연결 오류]\n" + e.getStackTrace());
        }
    }

    /** sign_Dialog의 회원가입 버튼 */

    /** 데이터베이스에 정보 저장 */
    public boolean Enrollment(String myId, String myPw) {
        try {
            String SQL1 = "INSERT INTO log_info(user_id, user_pw) VALUES('" + myId + "','" + myPw + "');";
            PreparedStatement pstmt = connection.prepareStatement(SQL1);
            int re = pstmt.executeUpdate();
            if (re == 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /** sign_Dialog의 중복확인 버튼 */
    /** 데이터베이스에서 정보 탐색 */
    public boolean isAdmin(String id) {
        try {
            String SQL = "SELECT * FROM log_info WHERE user_id = '" + id + "';".toString();

            rs = st.executeQuery(SQL);
            if (rs.next()) {
                if (rs.getString("user_id").equals(id)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("[데이터베이스 검색 오류] : " + e.getMessage());
        }
        return false;
    }

    public boolean login(String id, String pw) {
        boolean id_c = false;
        boolean pw_c = false;
        try {
            String SQL = "SELECT * FROM log_info WHERE user_id = '" + id + "';".toString();
            System.out.println(SQL);
            rs = st.executeQuery(SQL);
            if(rs.next()) {
                if(rs.getString("user_id").equals(id)) {
                    id_c = true;
                }
            }
            SQL = "SELECT * FROM log_info WHERE user_pw = '" + pw + "';".toString();
            rs = st.executeQuery(SQL);
            if(rs.next()) {
                if(rs.getString("user_pw").equals(pw)) {
                    pw_c = true;
                }
            }
            if(id_c == true && pw_c == true) {
                System.out.println("로그인 성공");
                return true;
            }
        }
        catch(Exception e) {
            System.out.println("[데이터베이스 검색 오류] : " + e.getMessage());
        }System.out.println("로그인 실패");
        return false;
    }

    /** Login_GUI의 로그인 버튼 */

    /**
     * 1. 가져온 id가 일치하는지, 2. 가져온 pw가 일치하는지 boolean 체크하고 둘다 true일 시 로그인(return true)
     */

}