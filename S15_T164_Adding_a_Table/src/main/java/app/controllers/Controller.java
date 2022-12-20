package app.controllers;

import app.gui.ViewBooksPanel;
import app.gui.CreateBookPanel;
import app.gui.MainFrame;

public class Controller {

	private MainFrame mainFrame;
	private CreateBookPanel createPanel;
	private ViewBooksPanel booksPanel;
	
	public Controller() {
		
		createPanel = new CreateBookPanel();
		
		createPanel.setFormListener((author, title) -> {
			System.out.println(author + ": " + title);
		});
		
		booksPanel = new ViewBooksPanel();
		
		mainFrame = new MainFrame(createPanel, booksPanel);
	}
}
