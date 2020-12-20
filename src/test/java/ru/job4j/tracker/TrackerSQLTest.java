package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

public class TrackerSQLTest {

	public Connection init() {
		try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
			Properties config = new Properties();
			config.load(in);
			Class.forName(config.getProperty("jdbc.driver"));
			return DriverManager.getConnection(
					config.getProperty("jdbc.url"),
					config.getProperty("jdbc.user"),
					config.getProperty("jdbc.password")
			);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	@Test
	public void createItem() throws SQLException, Exception {
		try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
			tracker.add(new Item("name1", "desc", 123));
			System.out.println(tracker);
			assertThat(tracker.findByName("name1").size(), is(1));
		}
	}
}
