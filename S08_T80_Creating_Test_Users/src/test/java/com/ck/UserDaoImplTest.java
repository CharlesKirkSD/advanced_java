package com.ck;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserDaoImplTest {
	private Connection conn;
	private List<User> users;
	private static final int NUM_TEST_USERS = 1000;
	
	private List<User> loadUsers() throws IOException {
		// @formatter:off
		var usersLoaded = Files
				.lines(Paths.get("../greatexpectations.txt"))
				.map(line -> line.split("[^a-zA-Z]")) // Returns an array of strings
				.map(Arrays::asList)
				.flatMap(list -> list.stream()) // Flattens the input, a stream of lists, to a stream of strings, result is a collection of single words.
				.filter(word ->word.length() > 3 && word.length() < 20)
				.map(word -> new User(word)) // Convert the stream of words to a stream of type User
				.limit(NUM_TEST_USERS)
				.collect(Collectors.toList())
				;
		// @formatter:on
		
		
		usersLoaded.forEach(System.out::println);
		return usersLoaded;
	}
	
	@Before
	public void setUp() throws SQLException, IOException {
		System.out.println("Before"); // Confirm these methods are called.
		
		users = loadUsers();
		System.out.println(users);
		System.out.println(users.size());
		var props = Profile.getProperties("db"); // Comes from the properties of the environment, the System properties, these need to be set before the test is run.
		var db = Database.instance();
		db.connect(props);
		conn = db.getConnection();
		conn.setAutoCommit(false);
	}
	
	@After
	public void tearDown() throws SQLException {
		System.out.println("After");
		Database.instance().close();
	}
	
	@Test
	public void testSave() throws SQLException {
		User user = new User("Jupiter");
		
		UserDao userDao = new UserDaoImpl();
		
		userDao.save(user);
		
		var stmt = conn.createStatement();
		var rs = stmt.executeQuery("select id, name from user order by id");
		boolean result = rs.next();
		
		assertTrue("Cannot retrieve inserted user", result);
		
		String name = rs.getString("name");
		assertEquals("User name does not match retrieved", user.getName(), name);
		
		stmt.close();
	}
}
