package app.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import app.model.Book;

public class BookService {
	private static final String DATA_URL = "http://localhost:8080/books";
	
	public List<Book> getAll() throws IOException {
		var url = new URL(DATA_URL);
		
		var conn = (HttpURLConnection)url.openConnection();
		
		conn.setDoInput(true);
		conn.setRequestProperty("Accept", "application/json,text/plain");
		conn.setRequestMethod("GET");
		
		conn.connect();
		
		StringBuilder sb = new StringBuilder();
		try(var br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
			String line = "";
			
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		}
		
		conn.disconnect();
		
		System.out.println(sb.toString()); // TEMPORARY CODE FOR TESTING
		
		Gson gson = new Gson();
		Book [] book = gson.fromJson(sb.toString(), Book[].class);
		List<Book> bookList = Arrays.asList(book);
		
		return bookList;
	}

}
