package app;

import java.util.Arrays;
import java.util.Date;

class User {
	private int id;	
}

class Employee extends User {
	public String name;
	private String password;
	
	private boolean setUpdated(Date updated, int sequence) {
		return true;
	}
}


public class App {
	
	public static void main(String [] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException, NoSuchMethodException	{
		
		System.out.println("Hello World from S09_T91");
		
		Class<Employee> clazz = Employee.class;

		var nameField = clazz.getField("name");
		
		System.out.println(nameField);
		
		var setUpdatedMethod = clazz.getDeclaredMethod("setUpdated", Date.class, int.class);
		
		System.out.println(setUpdatedMethod);
		
		var methodExists = Arrays.stream(clazz.getDeclaredMethods()).anyMatch(m -> m.getName().equals("setUpdated"));
		
		System.out.println(methodExists);
	}
}