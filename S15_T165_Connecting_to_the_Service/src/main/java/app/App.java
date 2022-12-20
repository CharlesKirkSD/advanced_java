package app;

import javax.swing.SwingUtilities;

import app.controllers.Controller;

public class App {
	
	public static void main(String [] args) {
		
		System.out.println("Hello World! from S15_T165_Connecting_to_the_Service");
		
		SwingUtilities.invokeLater(Controller::new);
	}
}