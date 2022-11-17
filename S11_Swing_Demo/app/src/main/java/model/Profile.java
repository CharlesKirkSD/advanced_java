package model;

import java.util.Properties;

public class Profile {
	public static Properties getProperties(String name) {
		Properties props = new Properties();
		String env = System.getProperty("env");
		
		// For testing with -D in the run configurations the following servers are used which may be differentiated in the propertiesFile string output to the console.
		// db.dev.properties uses server=localhost
		// db.prod.properties uses server=127.0.0.1
		if (env == null) {
			env = "dev"; // Supply a default
		}
				
		String propertiesFile = String.format("/config/%s.%s.properties", name, env);
		System.out.println("Config used : " +propertiesFile);
		try {
			props.load(Profile.class.getResourceAsStream(propertiesFile));
		} catch (Exception e1) {
			throw new RuntimeException("Cannot load properties file : " + propertiesFile);
		}
		
		return props;
	}
}
