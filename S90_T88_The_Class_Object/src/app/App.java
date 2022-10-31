package app;

class User {
	
}

class Employee extends User {
	
}


public class App {
	
	public static void main(String [] args) throws ClassNotFoundException	{
		
		System.out.println("Hello World from S09_T88");
		
		// Getting the Class object directly from a Class.
		Class<Employee> clazz = Employee.class;
		System.out.println(clazz);
		
		// Getting the Class object using the Class.forName method.
		Class<?> clazz2 = Class.forName("app.Employee");
		System.out.println(clazz2);
		
		// Getting the Class object by calling the getClass method on an instance of a class.
		User u = new Employee();
		Class<? extends User> clazz3 = u.getClass();
		System.out.println(clazz3);
		
	}
}