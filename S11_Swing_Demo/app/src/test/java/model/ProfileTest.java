package model;

// import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ProfileTest {
	@Test
	public void testLoadDBConfig() {
		var props = Profile.getProperties("db"); // Comes from the properties of the environment, the System properties, these need to be set before the test is run.
		assertNotNull("Cannot load db properties", props); // May fail if the environment property has not been set.
		var dbName = props.getProperty("database");
		assertEquals("dbName incorrect", "people_test", dbName);
	}
}
