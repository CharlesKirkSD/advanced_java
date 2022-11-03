package app;

public class App {
	
	public static void main(String [] args) {
		
		System.out.println("Hello World, from S10_T100");
		
		var user = new User(0L, "Thor");
		
		var rep = new Repository<User>();
		rep.save(user);

	}
}