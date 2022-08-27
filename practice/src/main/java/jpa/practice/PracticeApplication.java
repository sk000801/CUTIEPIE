package jpa.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class PracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);

		Connection conn = null;

		try{
			//1. 드라이버 로딩 : mysql 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			//드라이버들이 읽히기만 하면 자동 객체가 생성되고 DriverManager에 등록된다.

			//2. mysql과 연결시키기
			String url = "jdbc:mysql://localhost:3305/CUTIEPIE?useSSL=false";

			conn = DriverManager.getConnection(url, "root", "judyserver0801*");
			System.out.println("Successfully Connection!");
		}

		catch(ClassNotFoundException e){
			System.out.println("Failed because of not loading driver");
		}
		catch(SQLException e){
			System.out.println("error : " + e);
		}

		finally{
			try{
				if(conn != null && !conn.isClosed()){
					conn.close();
				}
			}

			catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	}


