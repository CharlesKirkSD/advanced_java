package com.ck;

import java.sql.SQLException;
import java.util.Properties;

public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!\nfrom S06_T71");
		
		Properties props = new Properties();
		String env = System.getProperty("env");
		
		// For testing with -D in the run configurations the following servers are used which may be differentiated in the propertiesFile string output to the console.
		// db.dev.properties uses server=localhost
		// db.prod.properties uses server=127.0.0.1
		if (env == null) {
			env = "dev"; // Supply a default
		}
				
		String propertiesFile = String.format("/config/db.%s.properties", env);
		System.out.println("Config used : " +propertiesFile);
		try {
			props.load(App.class.getResourceAsStream(propertiesFile));
		} catch (Exception e1) {
			System.out.println("Cannot load properties file : " + propertiesFile);
			return;
		}
		
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
