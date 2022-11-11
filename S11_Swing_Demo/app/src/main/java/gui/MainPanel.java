package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public MainPanel() {
		setBackground(Color.LIGHT_GRAY);
		
		// Create the components
		JLabel formLabel = new JLabel("Add User", JLabel.RIGHT);
		formLabel.setFont(new Font("Serif", Font.PLAIN, 20));
		
		JLabel nameLabel = new JLabel("Name:", JLabel.RIGHT);
		JLabel passLabel = new JLabel("Password:", JLabel.RIGHT);
		
		JTextField nameField = new JTextField(15);
		JTextField passField = new JTextField(15);
		
		JButton addButton = new JButton("Save");
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		var rightPadInsets = new Insets(0,0,0,10);
		var zeroPadInsets = new Insets(0,0,0,0);
		
		gc.gridx = 0;
		gc.gridy = 0;
		
		gc.weightx = 1;
		gc.weighty = 50;
		
		gc.gridwidth = 2;
		add(formLabel, gc);
		gc.gridwidth = 1;
		
		gc.gridy++;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = rightPadInsets;
		add(nameLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = zeroPadInsets;
		add(nameField, gc);
		
		gc.gridx = 0;
		gc.gridy++;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = rightPadInsets;
		add(passLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0,0,0,0);
		add(passField, gc);
		
		gc.gridy++;
		gc.weighty = 50;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0,75,0,0);
		add(addButton, gc);
	}
}
