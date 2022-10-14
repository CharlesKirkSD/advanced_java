package com.ck;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private static Database db = new Database();
	private static final String DBURL = "jdbc:mysql://localhost:3306/people?serverTimeZone=UTC";
	private Connection conn;
	
	public static Database instance() {
		return db;
	}
	
	private Database() {}
	
	public void connect() throws SQLException {
		conn = DriverManager.getConnection(DBURL, "root", "jupiter"); // The login details would normally be placed in a properties file.
	}
	
	public void close() throws SQLException {
		conn.close();
	}
}
