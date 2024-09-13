package in.sp.dbcon;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection getConnection() {
		
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/mvc_db";
			con=DriverManager.getConnection(url,"root","8576");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

}
