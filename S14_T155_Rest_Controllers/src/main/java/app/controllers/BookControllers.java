package app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import app.entities.Book;

@RestController
public class BookControllers {

	@GetMapping("/books")
	public Book books() {
		Book book = new Book("Great Expectations", "Charles Dickens");
		return book;
	}
}
