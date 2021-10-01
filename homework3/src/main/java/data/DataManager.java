package data;

import managers.DriverManager;
import org.junit.Assert;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager INSTANCE = null;
    private List<Product> productList = new ArrayList<>();

    private DataManager() {
    }

    /**
     * Метод ленивой инициализации веб драйвера
     *
     * @return WebDriver - возвращает веб драйвер
     */
    public static DataManager getDataManager() {
        if (INSTANCE == null) {
            INSTANCE = new DataManager();
        }
        return INSTANCE;
    }

    public List<Product> getProductList() {

        return productList;
    }

    public Product getProductByName(String productName) {
        for (int i = 0; i < productList.size(); i++) {
            if (Utils.convertNameProduct(productList.get(i).getName()).contains(Utils.convertNameProduct(productName))) {
                return productList.get(i);
            }
        }
        Assert.fail("There is no this product in cart");
        return null;
    }
}
