package com.ck;

import java.sql.SQLException;

public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!\nfrom S06_T65");
		
		Database db = Database.instance();
		try {
			db.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot connect to the database.");
			e.printStackTrace();
		}
		
		System.out.println("Connected");
		
		UserDao userDao = new UserDaoImpl();
		userDao.save(new User("Uranus"));
		userDao.save(new User("Mercury"));
		
		try {
			db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot close database connection.");
			e.printStackTrace();
		}
		
	}
}
