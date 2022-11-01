package app;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;

class User {
	private int id;	
}

class Employee extends User {
	public String name;
	private String password;
	
	public boolean setUpdated(int sequence) {
		System.out.println("Employee.setUpdated method called with sequence = " + sequence);
		return true;
	}
	
	private boolean setStatus(int sequence) {
		System.out.println("Employee.setStatus method called with sequence = " + sequence);
		return true;
	}	
}


public class App {
	
	public static void main(String [] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException	{
		System.out.println("Hello World from S09_T92");
		
		
		Class<Employee> clazz = Employee.class;

		var nameField = clazz.getField("name");
		
		System.out.println(nameField);
		
		// Public Method example
		var setUpdatedMethod = clazz.getDeclaredMethod("setUpdated", int.class);
		setUpdatedMethod.invoke(new Employee(), 7);
		
		// Private Method example
		var setStatusMethod = clazz.getDeclaredMethod("setStatus", int.class);
		setStatusMethod.setAccessible(true);
		setStatusMethod.invoke(new Employee(), 12);
	}
}