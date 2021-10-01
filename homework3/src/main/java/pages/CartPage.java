package pages;

import data.DataManager;
import data.Product;
import managers.DriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utils;
import utils.Warantee;
import java.util.List;

import static utils.Utils.convertNameProduct;


public class CartPage extends BasePage {

    @FindBy(xpath = "//h1")
    private WebElement title;

    @FindBy(xpath = "//div[@class='cart-items__product']")
    private List<WebElement> purchasesList;

    @FindBy(xpath = "//div[@class = 'buttons']/a/span[@class='cart-link__lbl']/span[@class='cart-link__price']")
    private WebElement purchasesSum;

    @FindBy(xpath = "//span/span[contains(text(),'Вернуть удалённый товар')]")
    private WebElement getBackDeletedProductButton;


    public CartPage() {
        checkEveryProductPriceAndTotalSum();
    }

    public CartPage checkOpenPage() {
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                "Корзина", title.getText());
        return this;
    }

    public CartPage clickPlusButton(String productName) {
        for (WebElement productMenuItem : purchasesList) {
            WebElement title = productMenuItem.findElement(By.xpath(".//a[contains(@class, 'product-name')]"));
            if (convertNameProduct(title.getText()).contains(convertNameProduct(productName))) {
                waitUtilElementToBeVisible(productMenuItem.findElement(By.xpath(".//button[contains(@class, 'button_plus')]/i")));
                waitUtilElementToBeClickable(productMenuItem.findElement(By.xpath(".//button[contains(@class, 'button_plus')]/i"))).click();
                checkAddProductToCart();
                for (int i = 0; i < dataManager.getProductList().size(); i++) {
                    if (dataManager.getProductList().get(i).getName().equals(title.getText())) {
                        dataManager.getProductList().add(new Product(
                                        dataManager.getProductList().get(i).getName(),
                                        dataManager.getProductList().get(i).getWarrantyPrice(),
                                        dataManager.getProductList().get(i).getPrice()));
                    }
                }
                checkPurchasesSum();
                return this;
            }
        }
        Assert.fail("Продукт с именем '" + productName + "' не был найден в корзине!");
        return this;
    }


    public CartPage checkWaranteeOnProduct(String productName, Warantee warantee) {
        for (WebElement productItem : purchasesList) {
            WebElement title = productItem.findElement(By.xpath(".//a[contains(@class, 'product-name')]"));
            if (convertNameProduct(title.getText()).equals(convertNameProduct(productName))) {
                switch (warantee) {
                    case THIRTY_SIX_MONTH:
                        Assert.assertTrue("Гарантия товара не совпадает с указанной!",
                                productItem.findElement(By.xpath(".//span[contains(@class, 'button__icon_checked')]"))
                                        .getText().contains("24"));
                        return this;

                    case TWENTY_FOUR_MONTH:
                        String garatiya = productItem.findElement(By.xpath(".//span[contains(@class, 'button__icon_checked')]"))
                                .getText();
                        Assert.assertTrue("Гарантия товара не совпадает с указанной!",
                                garatiya.contains("12"));
                        return this;

                    case TWELVE_MONTH:
                        Assert.assertTrue("Гарантия товара не совпадает с указанной!",
                                productItem.findElement(By.xpath(".//span[contains(@class, 'button__icon_checked')]"))
                                        .getText().contains("Без доп. гарантии"));
                        return this;
                }
            }
        }
        Assert.fail("Товар с именем '" + productName + "' не был найден в корзине!");
        return this;
    }

    public void checkEveryProductPriceAndTotalSum() {
        for (WebElement productItem : purchasesList) {
            WebElement productPrice = productItem.findElement(By.xpath(".//div[@class='cart-items__product-price']//span[@class='price__current']"));
            WebElement title = productItem.findElement(By.xpath(".//a[contains(@class, 'product-name')]"));
            for (int i = 0; i < dataManager.getDataManager().getProductList().size(); i++) {
                if (Utils.convertNameProduct(title.getText()).equals(Utils.convertNameProduct(dataManager.getDataManager().getProductList().get(i).getName()))) {
                    Assert.assertEquals("Цена товара " + dataManager.getDataManager().getProductList().get(i).getName() + " не совпадает с его ценой в корзине!",
                            String.valueOf(dataManager.getDataManager().getProductList().get(i).getPrice()),
                            String.valueOf(Utils.convertPrice(productPrice.getText())));
                }
            }
        }
        checkPurchasesSum();
    }

    public CartPage deleteProductByName(String productName) {
        for (WebElement productMenuItem : purchasesList) {
            WebElement title = productMenuItem.findElement(By.xpath(".//a[contains(@class, 'product-name')]"));
            if (convertNameProduct(title.getText()).contains(convertNameProduct(productName))) {
                for (int i = 0; i < dataManager.getProductList().size(); i++) {
                    if (convertNameProduct(dataManager.getProductList().get(i).getName())
                            .contains(convertNameProduct(title.getText()))) {

                        bucket.push(dataManager.getProductList().get(i));
                        dataManager.getProductList().remove(i);
                    }
                }
                waitUtilElementToBeClickable(productMenuItem.findElement(By.xpath(".//button[text() = 'Удалить']"))).click();
                checkDeleteProductFromCart();
                checkPurchasesSum();
                return this;
            }
        }
        Assert.fail("Продукт с именем '" + productName + "' не был найден в корзине!");
        return this;
    }

    public CartPage clickOnGetBackDeletedProductButton() {
        waitUtilElementToBeVisible(getBackDeletedProductButton);
        waitUtilElementToBeClickable(getBackDeletedProductButton).click();
        checkAddProductToCart();
        dataManager.getProductList().add(bucket.pop());
        checkPurchasesSum();
        return this;
    }
}



