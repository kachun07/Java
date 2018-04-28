package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Cart;
import model.Product;
import model.User;

public enum UserDAO {
	instance;

	public User save(User user) {
		Connection connection = Utils.getConnection();

		try {
			PreparedStatement psmt = connection.prepareStatement(
					"INSERT INTO USER (name, password, address, email) VALUES (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			psmt.setString(1, user.getName());
			psmt.setString(2, user.getPassword());
			psmt.setString(3, user.getAddress());
			psmt.setString(4, user.getEmail());

			psmt.executeUpdate();

			ResultSet rs = psmt.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			user.setId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User checkLogin(String name, String password) {
		Connection connection = Utils.getConnection();
		User user = null;

		try {
			PreparedStatement psmt = connection.prepareStatement("SELECT * FROM USER WHERE NAME = ? AND PASSWORD = ?");
			psmt.setString(1, name);
			psmt.setString(2, password);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("password"),
						rs.getString("address"), rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User linkCart(User user, Cart cart) {
		user.setCart(cart);

		Connection connection = Utils.getConnection();
		try {
			PreparedStatement psmt = connection.prepareStatement(
					"INSERT INTO USER_CART (USER_ID, CART_ID) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
			psmt.setInt(1, user.getId());
			psmt.setInt(2, cart.getId());

			psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
