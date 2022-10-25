package com.ck;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserDaoImplTest {
	private Connection conn;
	private List<User> users;
	private static final int NUM_TEST_USERS = 1000;
	
	private List<User> loadUsers() throws IOException {
		var temp = Files.lines(Paths.get("../greatexpectations.txt"));
		temp.forEach(System.out::println);
		return null;
	}
	
	@Before
	public void setUp() throws SQLException, IOException {
		System.out.println("Before"); // Confirm these methods are called.
		
		users = loadUsers();
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
