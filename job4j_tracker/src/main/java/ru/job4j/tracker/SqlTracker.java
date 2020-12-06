package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SqlTracker implements Store {

	public SqlTracker() {
		init();
	}

	private Connection cn;

	public void init() {
		try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
			Properties config = new Properties();
			config.load(in);
			Class.forName(config.getProperty("jdbc.driver"));
			cn = DriverManager.getConnection(
					config.getProperty("jdbc.url"),
					config.getProperty("jdbc.user"),
					config.getProperty("jdbc.password")
			);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public void close() throws Exception {
		if (cn != null) {
			cn.close();
		}
	}

	@Override
	public Item add(Item item) {
		try (var st = this.cn.prepareStatement("INSERT INTO public.items (name,description,category_id,req_state_id,user_id) values (?,?,1,1,1) returning id;")) {
			st.setString(1, item.getName());
			st.setString(2, item.getDesc());
			try (var rs = st.executeQuery()) {
				rs.next();
				item.setId(String.valueOf(rs.getLong(1)));
			}
		} catch (SQLException ex) {
			Logger.getLogger(SqlTracker.class.getName()).log(Level.SEVERE, null, ex);
		}
		return item;
	}

	@Override
	public boolean replace(String id, Item item) {
		boolean result = false;
		try (var st = this.cn.prepareStatement("UPDATE public.items SET \"name\"=?,description=? WHERE id=?")) {
			st.setString(1, item.getName());
			st.setString(2, item.getDesc());
			st.setInt(3, Integer.parseInt(id));
			if (st.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException ex) {
			Logger.getLogger(SqlTracker.class.getName()).log(Level.SEVERE, null, ex);
		}

		return result;
	}

	@Override
	public boolean delete(String id) {
		boolean result = false;
		try (var st = this.cn.prepareStatement("DELETE FROM public.items WHERE id=?")) {
			st.setInt(1, Integer.parseInt(id));
			if (st.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException ex) {
			Logger.getLogger(SqlTracker.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	@Override
	public List<Item> findAll() {
		List<Item> result = new ArrayList<>();
		try (var st = this.cn.prepareStatement("SELECT id, name, description, round(extract(epoch from createdate)) createdate FROM items;")) {
			try (var rs = st.executeQuery()) {
				while (rs.next()) {
					result.add(new Item(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getLong("createdate")));
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(SqlTracker.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	@Override
	public List<Item> findByName(String key) {
		List<Item> result = new ArrayList<>();
		try (var st = this.cn.prepareStatement("SELECT id, name, description, round(extract(epoch from createdate)) createdate FROM items WHERE name LIKE ?")) {
			st.setString(1, "%" + key + "%");
			try (var rs = st.executeQuery()) {
				while (rs.next()) {
					result.add(new Item(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getLong("createdate")));
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(SqlTracker.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}

	@Override
	public Item findById(String id) {
		Item result = null;
		try (var st = this.cn.prepareStatement("SELECT id, name, description, round(extract(epoch from createdate)) createdate FROM items WHERE id = ?")) {
			st.setInt(1, Integer.parseInt(id));
			try (var rs = st.executeQuery()) {
				while (rs.next()) {
					result = new Item(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getLong("createdate"));
				}
			}
		} catch (SQLException ex) {
			Logger.getLogger(SqlTracker.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}
}
