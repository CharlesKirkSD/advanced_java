package app;

import javax.swing.SwingUtilities;

import app.controllers.Controller;

public class App {
	
	public static void main(String [] args) {
		
		System.out.println("Hello World! from S15_T166_Parsing_the_JSON");
		
		SwingUtilities.invokeLater(Controller::new);
	}
}