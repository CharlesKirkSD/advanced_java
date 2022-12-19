package app.controllers;

import gui.BooksPanel;
import gui.CreateBookPanel;
import gui.MainFrame;

public class Controller {

	private MainFrame mainFrame;
	private CreateBookPanel createPanel;
	private BooksPanel booksPanel;
	
	public Controller() {
		
		createPanel = new CreateBookPanel();
		booksPanel = new BooksPanel();
		
		mainFrame = new MainFrame(createPanel, booksPanel);
	}
}
