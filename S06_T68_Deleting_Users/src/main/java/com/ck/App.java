package com.ck;

import java.sql.SQLException;
import java.util.Optional;

public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!\nfrom S06_T67");
		
		Database db = Database.instance();
		try {
			db.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot connect to the database.");
			e.printStackTrace();
		}
		
		System.out.println("Connected");

		// Add users to the database
		UserDao userDao = new UserDaoImpl();
		
		/*
		userDao.save(new User("Saturn"));
		userDao.save(new User("Mercury"));
		*/
		
		var users = userDao.getAll();
		
		users.forEach(System.out::println);
		
		var userOpt = userDao.findById(1);

		if (userOpt.isPresent()) {
			System.out.println("Retrieved " + userOpt.get());			
		} else {
			System.out.println("No user retrieved");
		}
		
		
		userDao.delete(new User(6, null));
		
		try {
			db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot close database connection.");
			e.printStackTrace();
		}
		
	}
}
