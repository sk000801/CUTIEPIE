package jpa.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
@EnableJpaAuditing
public class PracticeApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);

		String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; //드라이버
		String DB_URL = "jdbc:mysql://localhost:3305/CUTIEPIE?&useSSL=false&serverTimezone=UTC"; //접속할 DB 서버

		String USER_NAME = "root"; //DB에 접속할 사용자 이름
		String PASSWORD = "judyserver0801*"; //사용자의 비밀번호

		Connection conn = null;

		try { //Reflection 방식
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD); //db 내의 데이터를 저장



		} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
		} finally {
				try{
					if(conn != null)
						conn.close();
				}catch (SQLException e){

				}
			}

		}
}


