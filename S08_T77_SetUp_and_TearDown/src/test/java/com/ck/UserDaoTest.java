package com.ck;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserDaoTest {
	private Connection conn;
	
	@Before
	public void setUp() throws SQLException {
		System.out.println("Before"); // Confirm these methods are called.
		var props = Profile.getProperties("db"); // Comes from the properties of the environment, the System properties, these need to be set before the test is run.
		var db = Database.instance();
		db.connect(props);
		conn = db.getConnection();
	}
	
	@After
	public void tearDown() throws SQLException {
		System.out.println("After");
		Database.instance().close();
	}
	
	@Test
	public void testSave() {
		assertTrue("Hello", true);
	}
}
