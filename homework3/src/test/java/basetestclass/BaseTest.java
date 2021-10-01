package basetestclass;

import managers.DriverManager;
import managers.InitManager;
import managers.PageManager;
import managers.PropertieManager;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import static utils.PropertieConstants.*;

public class BaseTest {
    /**
     * Менеджер страничек
     * @see PageManager#getPageManager()
     */
    protected PageManager pageManager = PageManager.getPageManager();

    /**
     * Менеджер WebDriver
     *
     * @see DriverManager#getDriverManager()
     */
    private final DriverManager driverManager = DriverManager.getDriverManager();

    @BeforeClass
    public static void beforeAll() {
        InitManager.initFramework();
    }

    @Before
    public void beforeEach() {
        driverManager.getDriver().get(PropertieManager.getPropertieManager().getProperty(BASE_URL));
    }

    @AfterClass
    public static void afterAll() {
        InitManager.quitFramework();
    }
}
