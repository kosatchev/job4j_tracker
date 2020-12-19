package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class TrackerSQLTest {
	public Connection init() {
		try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
			Properties config = new Properties();
			config.load(in);
			Class.forName(config.getProperty("driver-class-name"));
			return DriverManager.getConnection(
					config.getProperty("url"),
					config.getProperty("username"),
					config.getProperty("password")
			);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	@Test
	public void createItem() {
		SqlTracker tracker = new SqlTracker();
		tracker.add(new Item("name", "desc", 123));
		assertThat(tracker.findByName("name").get(0).getName(), is("name"));
	}
}
