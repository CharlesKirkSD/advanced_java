package com.ck;

import java.sql.SQLException;
import java.util.Properties;

public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!\nfrom S06_T72");
		
		Properties props = Profile.getProperties("db");
		Database db = Database.instance();
		try {
			db.connect(props);
		} catch (SQLException e) {
			System.out.println("Cannot connect to the database.");
			e.printStackTrace();
			return;
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
		
		var userOpt = userDao.findById(16);

		if (userOpt.isPresent()) {
			User user = userOpt.get();
			System.out.println("Retrieved " + user);
			user.setName("Venus");
			userDao.update(user);
		} else {
			System.out.println("No user retrieved");
		}
		
		
		// userDao.delete(new User(6, null));
		
		
		try {
			db.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Cannot close database connection.");
			e.printStackTrace();
		}
		
	}
}
