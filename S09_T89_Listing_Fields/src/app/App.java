package app;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

class User {
	private int id;	
}

class Employee extends User {
	public String name;
	private String password;
	
}


public class App {
	
	public static void main(String [] args) throws ClassNotFoundException	{
		
		System.out.println("Hello World from S09_T89");
		
		Class<Employee> clazz = Employee.class;
		System.out.println(clazz);
		
		Field [] fields = clazz.getFields();
		List<Field> fieldsList = Arrays.asList(fields);
		System.out.println(fieldsList);
		
		// Simplified
		System.out.println(Arrays.asList(clazz.getFields()));
		
		System.out.println(Arrays.asList(clazz.getDeclaredFields()));
	}
}