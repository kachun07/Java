package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Cart;
import model.CartLine;

public enum CartDAO {
	instance;

	public Cart save(Cart cart) {
		Connection connection = Utils.getConnection();

		try {
			PreparedStatement psmt = connection.prepareStatement(
					"INSERT INTO Cart (NAME) VALUES (?)",
					Statement.RETURN_GENERATED_KEYS);
			psmt.setString(1, cart.getName());
			psmt.executeUpdate();
			ResultSet rs = psmt.getGeneratedKeys();
			rs.next();
			int id = rs.getInt(1);
			cart.setId(id);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cart;
	}

	public Cart get(int cartId) {
		Cart cart = null;

		Connection connection = Utils.getConnection();
		try {
			String sql = "SELECT * FROM Cart WHERE ID = ?";
			PreparedStatement psmt = connection.prepareStatement(sql);
			psmt.setInt(1, cartId);

			ResultSet rs = psmt.executeQuery();
			rs.next();
			String name = rs.getString("name");
			cart = new Cart(cartId, name);
			List<CartLine> lines = getLines(cartId);
			cart.setContents(lines);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cart;
	}

	public List<CartLine> getLines(int cartId) {
		List<CartLine> lines = new ArrayList<CartLine>();
		Connection connection = Utils.getConnection();
		try {
			String sql = "SELECT * FROM CART_CARTLINE WHERE CART_ID = ?";
			PreparedStatement psmt = connection.prepareStatement(sql);
			psmt.setInt(1, cartId);

			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				int cartLineId = rs.getInt("CARTLINE_ID");
				CartLine line = CartLineDAO.instance.get(cartLineId);
				lines.add(line);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lines;
	}

	public Cart getFromUserId(int userId) {
		Cart cart = null;

		Connection connection = Utils.getConnection();
		try {
			String sql = "SELECT * FROM USER_CART WHERE USER_ID = ?";
			PreparedStatement psmt = connection.prepareStatement(sql);
			psmt.setInt(1, userId);

			ResultSet rs = psmt.executeQuery();
			rs.next();
			int cartId = rs.getInt("CART_ID");
			cart = this.get(cartId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cart;
	}

	public void addLine(Cart cart, CartLine line) {
		Connection connection = Utils.getConnection();

		try {
			PreparedStatement psmt = connection
					.prepareStatement("INSERT INTO CART_CARTLINE (CART_ID, CARTLINE_ID) VALUES (?,?)");
			psmt.setInt(1, cart.getId());
			psmt.setInt(2, line.getId());
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeAllLines(Cart cart) {

		Connection connection = Utils.getConnection();

		try {
			PreparedStatement psmt = connection
					.prepareStatement("DELETE FROM CART_CARTLINE WHERE CART_ID = ?");
			psmt.setInt(1, cart.getId());
			psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
