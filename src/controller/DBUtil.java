package controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost/shoppingmalldb";

	public static Connection getConnection() throws Exception {
		Class.forName(DRIVER);
		Connection con = DriverManager.getConnection(URL,"root","123456");
		System.out.println("DB ���� ����!");
		return con;
	}
}
