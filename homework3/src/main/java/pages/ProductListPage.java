package pages;

import data.Product;
import managers.DriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utils;

import java.util.List;

import static utils.Utils.convertNameProduct;
import static utils.Utils.convertPrice;

public class ProductListPage extends BasePage {

    public static String gamePrice;



    @FindBy(xpath = "//h1")
    private WebElement title;

    @FindBy(xpath = "//div[@data-id =  'product']")
    private List<WebElement> productMenu;

    public ProductListPage clickBuyButton(String nameProduct) {
        for (WebElement productMenuItem : productMenu) {
          WebElement title = productMenuItem.findElement(By.xpath(".//a[contains(@class, 'name')]/span"));
            if (convertNameProduct(title.getText()).contains(convertNameProduct(nameProduct))) {

                waitUtilElementToBeVisible(productMenuItem.findElement(By.xpath(".//button[contains(@class,'buy')]")));
                waitUtilElementToBeClickable(productMenuItem.findElement(By.xpath(".//button[contains(@class,'buy')]"))).click();
                checkAddProductToCart();

                gamePrice = productMenuItem.findElement(By
                        .xpath(".//div[@class='product-buy__price' or contains(@class,'product-buy__price_active')]")).getText();

                Product product = new Product(title.getText(), convertPrice(gamePrice));
                dataManager.getProductList().add(product);

                checkPurchasesSum();

                return this;
            }
        }
        Assert.fail("Продукт с именем '" + nameProduct + "' не был найден на Подстранице с Играми!");
        return this;
    }

    public ProductInfoPage selectProductByName(String productName) {
        for (WebElement productMenuItem : productMenu) {
            WebElement title = productMenuItem.findElement(By.xpath(".//a[contains(@class, 'name')]/span"));
            if (convertNameProduct(title.getText()).contains(convertNameProduct(productName))) {
                title.click();

                return pageManager.getProductInfoPage().checkOpenPage(productName);
            }
        }
        Assert.fail("Продукт с именем '" + productName + "' не был найден на Подстранице с Играми!");

        return pageManager.getProductInfoPage().checkOpenPage(productName);
    }


    public ProductListPage checkOpenPage(String nameSubCategoryMenu) {
        Assert.assertTrue("Заголовок не содержит искомой строки!",
                title.getText().contains(nameSubCategoryMenu));
        return this;
    }

}
