package com.dollop.app.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
	private static String DB_NAME = null;
	private static String DB_URL = null;
	private static String DB_UNAME = null;
	private static String DB_PWD = null;
	private static String DB_DRIVER = null;
	private static Connection con = null;

	static {
		File file = new File("C:\\Users\\Sande\\OneDrive\\Desktop\\PrasoonServlet\\HostelManagementSystem\\HostelManagementSystem\\application.properties");
		try {
			FileInputStream fis = new FileInputStream(file);
			Properties p = new Properties();
			p.load(fis);
			DB_NAME = p.getProperty("DB_NAME");
			DB_URL = p.getProperty("DB_URL");
			DB_UNAME = p.getProperty("DB_UNAME");
			DB_PWD = p.getProperty("DB_PWD");
			DB_DRIVER = p.getProperty("DB_DRIVER");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {
		if (con == null) {
			return getConnection(DB_URL + DB_NAME, DB_UNAME, DB_PWD, DB_DRIVER);
		}
		return con;
	}

	private static Connection getConnection(String db_url, String db_uname, String db_pwd, String db_driver) {

		try {
			Class.forName(db_driver);
			con = DriverManager.getConnection(db_url, db_uname, db_pwd);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
