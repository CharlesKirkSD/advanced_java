package app;

import javax.swing.SwingUtilities;

import app.controllers.Controller;

public class App {
	
	public static void main(String [] args) {
		
		System.out.println("S15_T169_Handling_Errors");
		
		SwingUtilities.invokeLater(Controller::new);
	}
}