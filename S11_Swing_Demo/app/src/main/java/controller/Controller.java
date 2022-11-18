package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Properties;

import gui.MainFrame;
import gui.MainPanel;
import model.Database;
import model.Profile;
import model.User;
import model.UserDao;
import model.UserDaoImpl;

public class Controller {
	private MainFrame mainFrame;
	private MainPanel mainPanel;
	
	public Controller() {
		// Set up the database connection
		Properties props = Profile.getProperties("db");
		Database db = Database.instance();
		try {
			db.connect(props);
		} catch (SQLException e) {
			System.out.println("Cannot connect to the database.");
			e.printStackTrace();
			return;
		}
		
		System.out.println("Connected");
		
		// Add users to the database
		UserDao userDao = new UserDaoImpl();

		
		// Set up the gui
		mainPanel = new MainPanel();
		// Set a UserFromListener event on the MainPanel
		mainPanel.setFormListener((username, password) -> {
			System.out.println(username + " :" + password);
			
			userDao.save(new User(username, password));
		});
		
		mainFrame = new MainFrame();
		
		mainFrame.setContentPane(mainPanel);
		
		// Close the Database Connection
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				System.out.println("Window Closing");
				
				// Close the database connection
				try {
					db.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Cannot close database connection.");
					e.printStackTrace();
				}
			}
			
		});
	}
}
