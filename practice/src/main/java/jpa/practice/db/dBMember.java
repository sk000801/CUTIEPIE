//package jpa.practice.db;
//
//import jpa.practice.product.Product;
//import org.springframework.stereotype.Component;
//
//import java.sql.*;
//import java.util.List;
//
//@Component
//public class dBMember {
//    String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; //드라이버
//    String DB_URL = "jdbc:mysql://localhost:3305/CUTIEPIE?&useSSL=false&serverTimezone=UTC"; //접속할 DB 서버
//
//    String USER_NAME = "root"; //DB에 접속할 사용자 이름
//    String PASSWORD = "judyserver0801*"; //사용자의 비밀번호
//
//    public dBMember() {
//        Connection conn = null;
//        Statement state = null;
//        try { //Reflection 방식
//            Class.forName(JDBC_DRIVER);
//            conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD); //db 내의 데이터를 저장
//            state = conn.createStatement(); //sql 문을 실행하기 위해 conn 연결 정보를 state로 생성
//
//            String sql;
//            sql = "select * from members";
//            ResultSet rs = state.executeQuery(sql); // sql 실행결과를 rs에 저장
//            while (rs.next()) {
//                String memberId = rs.getString("memberId");
//                String pw = rs.getString("pw");
//                String name = rs.getString("name");
//                String member_id = rs.getString("member_id");
//                String status = rs.getString("status"); // select 로 가져온 속성을 int 타입으로 반환
//                System.out.println("Id: "+ memberId + "\npw: " + pw + "\nname: " + name);
//                System.out.println("member_id: "+ member_id + "\nstatus: " + status + "\n-------------\n");
//            }
//
//            rs.close();
//            state.close();
//            conn.close();
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try{
//                if(state != null)
//                    state.close();
//            } catch(SQLException e){
//                e.printStackTrace();
//            }
//            try{
//                if(conn != null)
//                    conn.close();
//            }catch (SQLException e){
//
//            }
//        }
//    }
//}
