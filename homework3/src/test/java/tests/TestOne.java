package tests;

import basetestclass.BaseTest;
import org.junit.Test;
import utils.Warantee;

public class TestOne extends BaseTest {

    @Test
    public void startTest() {
        pageManager.getHomePage()
                .selectBaseMenu("ТВ и мультимедиа")
                .selectSubMenu("Nintendo")
                .clickOnSubCategoryMenu("Консоли Nintendo Switch")
                .selectProductByName("Игровая консоль Nintendo Switch 32 GB")
                .chooseWarantee(Warantee.TWENTY_FOUR_MONTH)
                .addProductToCart()
                .toTheHomePage()
                .selectBaseMenu("ТВ и мультимедиа")
                .selectSubMenu("Nintendo")
                .clickOnSubCategoryMenu("Игры")
                .clickBuyButton("Deadly Premonition 2")
                .openCartPage()
                .checkWaranteeOnProduct("Игровая консоль Nintendo Switch 32 GB", Warantee.TWENTY_FOUR_MONTH)
                .deleteProductByName("Deadly Premonition 2")
                .clickPlusButton("Игровая консоль Nintendo Switch 32 GB")
                .clickPlusButton("Игровая консоль Nintendo Switch 32 GB")
                .clickOnGetBackDeletedProductButton();

    }
}
