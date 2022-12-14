package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

	@Override
	public void save(User u) {
		Connection conn = Database.instance().getConnection();
		
		try {
			var stmt = conn.prepareStatement("insert into user(name, password) values(?, ?)");
			
			stmt.setString(1, u.getName());
			stmt.setString(2, u.getPassword());
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Optional<User> findById(int id) {
		Connection conn = Database.instance().getConnection();
		
		try {
			var stmt = conn.prepareStatement("select name, password from user where id=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				String name = rs.getString("name");
				String password = rs.getString("password");
				User user = new User(id, name, password);
				return Optional.of(user);
			}
			
			stmt.close();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		
		return Optional.empty();
	}

	@Override
	public void update(User u) {
		Connection conn = Database.instance().getConnection();
		
		try {
			var stmt = conn.prepareStatement("update user set name = ?, password = ? where id = ?");
			
			stmt.setString(1, u.getName());
			stmt.setString(2, u.getPassword());
			stmt.setInt(3, u.getId());
			
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void delete(User u) {
		Connection conn = Database.instance().getConnection();
		
		try {
			var stmt = conn.prepareStatement("delete from user where id=?");
			stmt.setInt(1, u.getId());
			stmt.executeUpdate();
			
			stmt.close();

		}
		catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<User> getAll() {
		List<User> users = new ArrayList<>();
		
		Connection conn = Database.instance().getConnection();
		
		try {
			var stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("select id, name, password from user order by id");
			
			while(rs.next()) {
				var id = rs.getInt("id");
				var name = rs.getString("name");
				var password = rs.getString("password");
				users.add(new User(id, name, password));
			}
			
			
			stmt.close();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		
		return users;
	}

}
