package managers;

import pages.*;

public class PageManager {

    private static BasePage basePage;

    /**
     * Менеджер страничек
     */
    private static PageManager pageManager;

    /**
     * Стартовая страничка
     */
    private HomePage homePage;

    /**
     * Страничка PlayStation
     */
    private CategoriesProductPage categoriesProductPage;


    /**
     * Страничка с Играми
     */
    private ProductListPage productListPage;

    /**
     * Страничка с Играми
     */
    private CartPage cartPage;

    /**
     * Страничка с Играми
     */
    private ProductInfoPage productInfoPage;

    /**
     * Ленивая инициализация PageManager
     *
     * @return PageManager
     */
    public static PageManager getPageManager() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    /**
     * Ленивая инициализация {@link HomePage}
     *
     * @return StartPage
     */
    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    public ProductInfoPage getProductInfoPage() {
        if (productInfoPage == null) {
            productInfoPage = new ProductInfoPage();
        }
        return productInfoPage;
    }

    /**
     * Ленивая инициализация {@link CategoriesProductPage}
     *
     * @return TvAndMultyMediaPage
     */
    public CategoriesProductPage getCategoriesProductPage() {
        if (categoriesProductPage == null) {
            categoriesProductPage = new CategoriesProductPage();
        }
        return categoriesProductPage;

    }

    public ProductListPage getProductListPage() {
        if (productListPage == null) {
            productListPage = new ProductListPage();
        }
        return productListPage;

    }

    public CartPage getCartPage() {
        if (cartPage == null) {
            cartPage = new CartPage();
        }
        return cartPage;

    }

}
