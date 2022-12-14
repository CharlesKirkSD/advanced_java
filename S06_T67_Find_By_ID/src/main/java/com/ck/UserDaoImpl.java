package com.ck;

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
			var stmt = conn.prepareStatement("insert into user(name) values(?)");
			
			stmt.setString(1, u.getName());
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
			var stmt = conn.prepareStatement("select name from user where id=?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				String name = rs.getString("name");
				User user = new User(id, name);
				return Optional.of(user);
			}
			
			stmt.close();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		
		return Optional.empty();
	}

	@Override
	public void update(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<User> getAll() {
		List<User> users = new ArrayList<>();
		
		Connection conn = Database.instance().getConnection();
		
		try {
			var stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("select id, name from user");
			
			while(rs.next()) {
				var id = rs.getInt("id");
				var name = rs.getString("name");
				users.add(new User(id, name));
			}
			
			
			stmt.close();
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		
		return users;
	}

}
