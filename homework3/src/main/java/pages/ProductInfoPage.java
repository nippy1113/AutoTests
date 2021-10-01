package pages;

import data.Product;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Utils;
import utils.Warantee;

import java.util.List;

public class ProductInfoPage extends BasePage {

    @FindBy(xpath = "//label[contains(@class, 'ui-radio__item product-warranty__item')]/span[@class = 'ui-radio__content']")
    private List<WebElement> waranteeList;

    @FindBy(xpath = "//label[contains(@class, 'ui-radio__item product-warranty__item')]/span[@class = 'product-warranty__price']")
    private List<WebElement> waranteePriceList;

    @FindBy(xpath = "//div[@class = 'product-buy product-buy_one-line']/button[contains(@class, 'buy-btn') and text() = 'Купить']")
    private WebElement buyButton;

    @FindBy(xpath = "//h1")
    private WebElement title;

    @FindBy(xpath = "//a[@id ='header-logo']")
    private WebElement logo;

    @FindBy(xpath = "//div[@class ='product-card-top__buy']//././div[@class = 'product-buy__price']")
    private WebElement productPrice;

    @FindBy(xpath = "//div[contains(text(), 'Гарантия') and contains(@class, 'sales')]")
    private WebElement waranteeProduct;

    private Product product = new Product(title.getText(), Utils.convertPrice(productPrice.getText()));

    public ProductInfoPage checkOpenPage(String productName) {
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                productName,
                title.getText());
        return this;
    }

    public ProductInfoPage chooseWarantee(Warantee warantee) {
        int waranteePrice = 0;
        switch (warantee) {
            case THIRTY_SIX_MONTH:
                waranteeProduct.click();
                waranteeList.get(2).click();
                waranteePrice = Utils.convertPrice(waranteePriceList.get(2).getText());

                product.setWarrantyPrice(waranteePrice);

                break;


            case TWENTY_FOUR_MONTH:
                waranteeProduct.click();
                waranteeList.get(1).click();
                waranteePrice = Utils.convertPrice(waranteePriceList.get(1).getText());

                product.setWarrantyPrice(waranteePrice);

                break;


            case TWELVE_MONTH:
                waranteeProduct.click();
                waranteeList.get(0).click();
                waranteePrice = Utils.convertPrice(waranteePriceList.get(0).getText());

                product.setWarrantyPrice(waranteePrice);

                break;


        }

        return this;
    }

    public ProductInfoPage addProductToCart() {
        waitUtilElementToBeClickable(buyButton).click();
        checkAddProductToCart();
        dataManager.getProductList().add(product);
        return this;
    }

    public HomePage toTheHomePage() {
        waitUtilElementToBeClickable(logo).click();

        return pageManager.getHomePage();
    }




}
