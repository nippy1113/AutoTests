package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath = "//div[@class = 'menu-desktop__root-info']")
    private List<WebElement> listLeftMenu;

    @FindBy(xpath = "//a[@class = 'ui-link menu-desktop__second-level']")
    private List<WebElement> listSubMenu;

    /**
     * Функция наведения мыши на любой пункт меню
     *
     * @param nameBaseMenu - наименование меню
     * @return getTvAndMultyMediaPage() - т.е. переходим на страницу Тв и Мультимедиа
     */
    public HomePage selectBaseMenu(String nameBaseMenu) {
        for (WebElement menuItem : listLeftMenu) {
            if (menuItem.getText().contains(nameBaseMenu)) {
                waitUtilElementToBeClickable(menuItem).click();
                return this;
            }
        }
        Assert.fail("Меню '" + nameBaseMenu + "' не было найдено на стартовой странице!");
        return this;
    }

    public CategoriesProductPage selectSubMenu(String nameSubMenu) {
        for (WebElement subMenuItem : listSubMenu) {
            if (subMenuItem.getText().contains(nameSubMenu)) {
                waitUtilElementToBeClickable(subMenuItem).click();
                return pageManager.getCategoriesProductPage().checkOpenPage(nameSubMenu);
            }
        }
        Assert.fail("Подменю '" + nameSubMenu + "' не было найдено на стартовой странице!");
        return pageManager.getCategoriesProductPage();
    }

}
