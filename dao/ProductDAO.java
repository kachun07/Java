package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.CartLine;
import model.Product;

public enum ProductDAO {
   instance;

   public Product save(Product product) {
      Connection connection = Utils.getConnection();

      try {
         PreparedStatement psmt = connection.prepareStatement(
               "INSERT INTO PRODUCT (artist ,name, year, genre, origin, price) VALUES (?,?,?,?,?,?)",
               Statement.RETURN_GENERATED_KEYS);
         psmt.setString(1, product.getArtist());
         psmt.setString(2, product.getName());
         psmt.setInt(3, product.getYear());
         psmt.setString(4, product.getGenre());
         psmt.setString(5, product.getOrigin());
         psmt.setDouble(6, product.getPrice());
         
         
         psmt.executeUpdate();

         ResultSet rs = psmt.getGeneratedKeys();
         rs.next();
         int id = rs.getInt(1);
         product.setId(id);
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return product;
   }

   public List<Product> list() {
      Connection connection = Utils.getConnection();

      List<Product> productList = new ArrayList<Product>();
      try {
         PreparedStatement psmt = connection
               .prepareStatement("SELECT * FROM PRODUCT");

         ResultSet rs = psmt.executeQuery();

         while (rs.next()) {
            Product p = new Product(rs.getInt("id"), rs.getString("artist"),
                  rs.getString("name"), rs.getInt("year"), rs.getString("genre"), rs.getString("origin"), rs.getDouble("price"));
            productList.add(p);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return productList;
   }

   public Product get(int productId) {
      Product product = null;

      Connection connection = Utils.getConnection();
      try {
         String sql = "SELECT * FROM PRODUCT WHERE ID = ?";
         PreparedStatement psmt = connection.prepareStatement(sql);
         psmt.setInt(1, productId);

         ResultSet rs = psmt.executeQuery();
         rs.next();

         String artist = rs.getString("ARTIST");
         String name = rs.getString("NAME");
         int year = rs.getInt("YEAR");
 		 String genre = rs.getString("GENRE");
 		 String origin = rs.getString("ORiGIN");
         Double price = rs.getDouble("PRICE");
         product = new Product(productId, artist ,name, year, genre, origin, price);
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return product;
   }

}
