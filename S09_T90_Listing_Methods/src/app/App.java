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
	
	private void calculate() {
		
	}
}


public class App {
	
	public static void main(String [] args) throws ClassNotFoundException	{
		
		System.out.println("Hello World from S09_T90");
		
		Class<Employee> clazz = Employee.class;
		System.out.println(clazz);
		
		Field [] fields = clazz.getFields();
		List<Field> fieldsList = Arrays.asList(fields);
		System.out.println(fieldsList);
		
		// Simplified
		System.out.println(Arrays.asList(clazz.getFields()));
		
		System.out.println(Arrays.asList(clazz.getDeclaredFields()));
		
		// Methods inherited from JObject
		System.out.println("Methods");
		Arrays.asList(clazz.getMethods()).forEach(System.out::println);
		
		System.out.println("Declared methods");
		Arrays.asList(clazz.getDeclaredMethods()).forEach(System.out::println);
	}
}