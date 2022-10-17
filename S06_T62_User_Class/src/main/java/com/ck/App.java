package com.ck;

import java.sql.SQLException;

public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!\nfrom S06_T62");
		
		Database db = Database.instance();
		try {
			db.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot connect to the database.");
			e.printStackTrace();
		}
		
		System.out.println("Connected");
		
		try {
			db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot close database connection.");
			e.printStackTrace();
		}
		
	}
}
