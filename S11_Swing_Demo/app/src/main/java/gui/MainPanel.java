package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public MainPanel() {
		setBackground(Color.LIGHT_GRAY);
		
		// Create the components
		JLabel formLabel = new JLabel("Add User");
		JLabel nameLabel = new JLabel("Name:");
		JLabel passLabel = new JLabel("Password");
		
		JTextField nameField = new JTextField();
		JTextField passField = new JTextField();
		
		JButton addButton = new JButton("Save");
		
		setLayout(new BorderLayout());
		add(formLabel, BorderLayout.NORTH);
		add(nameLabel, BorderLayout.CENTER);
	}
}
