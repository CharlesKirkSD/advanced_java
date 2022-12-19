package app.controllers;

import app.gui.BooksPanel;
import app.gui.CreateBookPanel;
import app.gui.MainFrame;

public class Controller {

	private MainFrame mainFrame;
	private CreateBookPanel createPanel;
	private BooksPanel booksPanel;
	
	public Controller() {
		
		createPanel = new CreateBookPanel();
		
		createPanel.setFormListener((author, title) -> {
			System.out.println(author + ": " + title);
		});
		
		booksPanel = new BooksPanel();
		
		mainFrame = new MainFrame(createPanel, booksPanel);
	}
}
