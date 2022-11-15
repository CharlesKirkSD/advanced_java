package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private UserFormListener formListener;
	
	public MainPanel() {
		// setBackground(Color.LIGHT_GRAY);
		
		// Create the components
		JPanel nestedPanel = createFormPanel();
		
		JLabel formLabel = new JLabel("Add User", JLabel.RIGHT);
		formLabel.setFont(new Font("Serif", Font.PLAIN, 20));

		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.gridx = 0;
		gc.gridy = 0;
		
		gc.weighty = 1;

		add(formLabel, gc);
		
		gc.weighty = 2;
		gc.gridy++;
		gc.anchor = GridBagConstraints.NORTH;

		add(nestedPanel, gc);
	}
	
	public void setFormListener(UserFormListener formListener) {
		this.formListener = formListener;
	}
	
	private JPanel createFormPanel() {
		JPanel panel = new JPanel();
		
		var padding = 20;
		var etchedBorder = BorderFactory.createEtchedBorder();
		var emptyBorder = BorderFactory.createEmptyBorder(padding, padding, padding, padding);
		
		panel.setBorder(BorderFactory.createCompoundBorder(etchedBorder,emptyBorder));
		
		JLabel nameLabel = new JLabel("Name:", JLabel.RIGHT);
		JLabel passLabel = new JLabel("Password:", JLabel.RIGHT);
		
		JTextField nameField = new JTextField(15);
		JTextField passField = new JTextField(15);
		
		JButton addButton = new JButton("Save");
		
		addButton.addActionListener(e -> {
			String username = nameField.getText();
			String password = passField.getText();
			if (formListener != null) {
				formListener.formSubmitted(username, password);
			}
		});
		
		GridBagConstraints gc = new GridBagConstraints();

		panel.setLayout(new GridBagLayout());
		
		var rightPadInsets = new Insets(0,0,0,10);
		var zeroPadInsets = new Insets(0,0,0,0);
		
		gc.gridx = 0;
		gc.gridy = 0;
		
		gc.gridy++;
		gc.gridwidth = 1;
		gc.weighty = 0.1;
		
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = rightPadInsets;
		panel.add(nameLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = zeroPadInsets;
		panel.add(nameField, gc);
		
		gc.gridx = 0;
		gc.gridy++;
		gc.weighty = 0.1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = rightPadInsets;
		panel.add(passLabel, gc);
		
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = rightPadInsets;
		panel.add(passField, gc);
		
		gc.gridy++;
		gc.weighty = 50;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		panel.add(addButton, gc);

		
		return panel;
	}
}
