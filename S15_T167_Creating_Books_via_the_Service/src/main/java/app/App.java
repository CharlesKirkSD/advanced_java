package app;

import javax.swing.SwingUtilities;

import app.controllers.Controller;

public class App {
	
	public static void main(String [] args) {
		
		System.out.println("S15_T167_Creating_Books_via_the_Service");
		
		SwingUtilities.invokeLater(Controller::new);
	}
}