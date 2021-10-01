package utils;

import data.Product;

public class Utils {
    public static String convertNameProduct(String nameProduct) {

        return nameProduct.replaceAll("\n", "").replaceAll("\\s", "");
    }

    public static Integer convertPrice(String price) {
        String[] s = price.split("₽");

        return Integer.parseInt(s[0].replaceAll("\\D", ""));
    }

    public static int getProductTotalPrice(Product product) {

        return product.getPrice() + product.getWarrantyPrice();
    }

    public static String convertWarantee(String warantee) {


        return warantee.replaceAll("\\D", "");
    }

}
