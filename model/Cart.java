package model;

import java.util.ArrayList;
import java.util.List;

import dao.CartDAO;

public class Cart {
   int id;
   String name;
   List<CartLine> contents = new ArrayList<CartLine>();

   public Cart(int id, String name) {
      this.id = id;
      this.name = name;
   }

   public Cart(String name) {
      this.name = name;
   }

   public void addLine(CartLine line) {
      System.out.println("addLine()");
      contents.add(line);
      CartDAO.instance.addLine(this, line);
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public List<CartLine> getContents() {
      return contents;
   }

   public void setContents(List<CartLine> contents) {
      this.contents = contents;
   }
}
