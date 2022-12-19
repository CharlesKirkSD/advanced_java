package app;

import javax.swing.SwingUtilities;

import app.controllers.Controller;

public class App {
	
	public static void main(String [] args) {
		
		System.out.println("Hello World! from S15_T162_Split_Panes");
		
		SwingUtilities.invokeLater(Controller::new);
	}
}