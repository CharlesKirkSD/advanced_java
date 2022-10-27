package com.ck;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
		
		// usersLoaded.forEach(System.out::println);
		return usersLoaded;
	}
	
	@Before
	public void setUp() throws SQLException, IOException {
		// System.out.println("Before"); // Confirm these methods are called.
		
		users = loadUsers();
		// System.out.println(users);
		// System.out.println(users.size());
		var props = Profile.getProperties("db"); // Comes from the properties of the environment, the System properties, these need to be set before the test is run.
		var db = Database.instance();
		db.connect(props);
		conn = db.getConnection();
		conn.setAutoCommit(false);
	}
	
	@After
	public void tearDown() throws SQLException {
		// System.out.println("After");
		Database.instance().close();
	}
	
	private int getMaxId() throws SQLException {
		var stmt = conn.createStatement();
		
		var rs = stmt.executeQuery("select max(id) as id from user");
		rs.next();
		var id = rs.getInt(1);
		
		stmt.close();
		
		return id;
	}
	
	private List<User> getUsersInRange(int minId, int maxId) throws SQLException {
		List<User> retrieved = new ArrayList<>();
		
		var stmt = conn.prepareStatement("select id, name from user where id >=? and id <=?");
		
		stmt.setInt(1, minId);
		stmt.setInt(2, maxId);
		var rs = stmt.executeQuery();
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			
			var user = new User(id, name);
			retrieved.add(user);
		}
		
		
		stmt.close();
		
		return retrieved;
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
	
	@Test
	public void testSaveMultiple() throws SQLException {
		UserDao userDao = new UserDaoImpl();
		
		for (var u: users) {
			userDao.save(u);
		}
		
		var maxId = getMaxId();
		System.out.println("maxID: " + maxId);
		
		for (int i = 0; i < users.size(); i++) {
			int id = maxId - users.size() + i + 1;
			
			users.get(i).setId(id);
		}
		
		var retrievedUsers = getUsersInRange(maxId - users.size() + 1, maxId);
		
		assertEquals("Size of retrieved users not equal to number of test users", retrievedUsers.size(), NUM_TEST_USERS);
		assertEquals("Retrieved users don't match saved users", users, retrievedUsers);
	}
	
	@Test
	public void testFindAndUpdate() throws SQLException {
		var user = users.get(0);
		
		UserDao userDao = new UserDaoImpl();
		userDao.save(user);
		
		var maxId = getMaxId();
		user.setId(maxId);
		
		var retrievedUserOpt = userDao.findById(maxId);
		var retrievedUser = retrievedUserOpt.get();
		
		assertTrue("No user retrieved", retrievedUserOpt.isPresent());
		assertEquals("Retrieved user doesn't match saved users", user, retrievedUser); // Tests find by id
		
		user.setName("xyzabcde");
		userDao.update(user);
		
		retrievedUserOpt = userDao.findById(maxId);
		retrievedUser = retrievedUserOpt.get();
		
		assertTrue("No updated user retrieved", retrievedUserOpt.isPresent());
		assertEquals("Retrieved updated users doesn't match saved users", user, retrievedUser); // Tests find by id
		
		System.out.println("testFindAndUpdate: " + retrievedUser);
	}
}
