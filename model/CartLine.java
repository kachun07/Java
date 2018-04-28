package model;

public class CartLine {
   int id;
   Product product;
   int numberItems;

   public CartLine(Product product, int numberItems) {
      this.product = product;
      this.numberItems = numberItems;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public Product getProduct() {
      return product;
   }

   public void setProduct(Product product) {
      this.product = product;
   }

   public int getNumberItems() {
      return numberItems;
   }

   public void setNumberItems(int numberItems) {
      this.numberItems = numberItems;
   }
}
