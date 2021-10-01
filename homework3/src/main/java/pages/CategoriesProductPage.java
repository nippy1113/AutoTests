package pages;

import managers.DriverManager;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CategoriesProductPage extends BasePage {

    @FindBy(xpath = "//span[@itemprop='item']")
    private WebElement subtitle;

    @FindBy(xpath = "//div[@class='subcategory__image']//img[@class ='subcategory__image-content loaded']/../../..")
    private List<WebElement> subCategoryMenu;

    public CategoriesProductPage checkOpenPage(String subCategoryName) {
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                subCategoryName, subtitle.getText());
        return this;
    }



    public ProductListPage clickOnSubCategoryMenu(String nameSubCategoryMenu) {
        for (WebElement subCategoryMenuItem : subCategoryMenu) {
            if (subCategoryMenuItem.getText().equals(nameSubCategoryMenu)) {
                waitUtilElementToBeClickable(subCategoryMenuItem).click();

                return pageManager.getProductListPage().checkOpenPage(nameSubCategoryMenu);
            }
        }
        Assert.fail("ПодМеню '" + nameSubCategoryMenu + "' не было найдено на Подстранице PlayStation!");
        return pageManager.getProductListPage().checkOpenPage(nameSubCategoryMenu);
    }

}
