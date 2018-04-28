package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.CartLine;
import model.Product;

public enum CartLineDAO {
   instance;

   public CartLine save(CartLine line) {
      Connection connection = Utils.getConnection();
      int id = 0;

      try {
         PreparedStatement psmt = connection.prepareStatement(
               "INSERT INTO CartLine (PRODUCT_ID, NUMBER_ITEMS) VALUES (?, ?)",
               Statement.RETURN_GENERATED_KEYS);
         psmt.setInt(1, line.getProduct().getId());
         psmt.setInt(2, line.getNumberItems());
         psmt.executeUpdate();
         ResultSet rs = psmt.getGeneratedKeys();
         rs.next();
         id = rs.getInt(1);
         line.setId(id);

      } catch (SQLException e) {
         e.printStackTrace();
      }
      return line;
   }

   public CartLine get(int id) {
      CartLine cartLine = null;

      Connection connection = Utils.getConnection();
      try {
         String sql = "SELECT * FROM CartLine WHERE ID = ?";
         PreparedStatement psmt = connection.prepareStatement(sql);
         psmt.setInt(1, id);

         ResultSet rs = psmt.executeQuery();
         rs.next();
         int productId = rs.getInt("PRODUCT_ID");
         int numberItems = rs.getInt("NUMBER_ITEMS");
         Product product = ProductDAO.instance.get(productId);
         cartLine = new CartLine(product, numberItems);
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return cartLine;
   }
}
