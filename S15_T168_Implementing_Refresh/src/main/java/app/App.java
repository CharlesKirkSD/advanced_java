package app;

import javax.swing.SwingUtilities;

import app.controllers.Controller;

public class App {
	
	public static void main(String [] args) {
		
		System.out.println("S15_T168_Implementing_Refresh");
		
		SwingUtilities.invokeLater(Controller::new);
	}
}