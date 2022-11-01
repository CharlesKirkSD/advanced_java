package app;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;

class User {
	private int id;	
}

class Employee extends User {
	private String name;
	private String password;
	
	public boolean setUpdated(int sequence) {
		System.out.println("Employee.setUpdated method called with sequence = " + sequence);
		return true;
	}
	
	private boolean setStatus(int sequence) {
		System.out.println("Employee.setStatus method called with sequence = " + sequence);
		return true;
	}
	
	@Override
	public String toString() {
		return "Employee [name=" + name + ", password=" + password + "]";
	}
}


public class App {
	
	public static void main(String [] args) throws ClassNotFoundException, NoSuchFieldException, SecurityException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException	{
		System.out.println("Hello World from S09_T93");
		
		
		Class<Employee> clazz = Employee.class;

		var nameField = clazz.getDeclaredField("name");

		var user = new Employee();
		
		nameField.setAccessible(true);
		nameField.set(user, "Poseidon");
		System.out.println(user);
	}
}