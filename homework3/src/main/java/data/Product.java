package data;

public class Product {
   private String name;
   private int warrantyPrice;
   private int price;

    public Product(String name, int price) {
        this(name,0, price);
    }


    public Product(String name, int warrantyPrice, int price) {
        this.name = name;
        this.warrantyPrice = warrantyPrice;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWarrantyPrice() {
        return warrantyPrice;
    }

    public void setWarrantyPrice(int warrantyPrice) {
        this.warrantyPrice = warrantyPrice;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProductTotalPrice(Product product) {

        return product.price + product.warrantyPrice;
    }
}



