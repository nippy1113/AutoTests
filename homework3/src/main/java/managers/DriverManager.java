package managers;

import org.apache.commons.exec.OS;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static utils.PropertieConstants.*;


public class DriverManager {
    private WebDriver driver;

    /**
     * Переменна для хранения объекта DriverManager
     */
    private static DriverManager INSTANCE = null;


    /**
     * Менеджер properties
     *
     *
     */
    private final PropertieManager props = PropertieManager.getPropertieManager();

    private DriverManager() {
    }

    /**
     * Метод ленивой инициализации веб драйвера
     *
     * @return WebDriver - возвращает веб драйвер
     */
    public static DriverManager getDriverManager() {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }

    /**
     * Метод ленивой инициализации веб драйвера
     *
     * @return WebDriver - возвращает веб драйвер
     */
    public WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    /**
     * Метод для закрытия сессии драйвера и браузера
     *
     * @see WebDriver#quit()
     */
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    /**
     * Метод инициализирующий веб драйвер
     */
    private void initDriver() {
        if (OS.isFamilyWindows()) {
            initDriverWindowsOsFamily();
        } else if (OS.isFamilyMac()) {
            initDriverMacOsFamily();
        } else if (OS.isFamilyUnix()) {
            initDriverUnixOsFamily();
        }
    }

    /**
     * Метод инициализирующий веб драйвер под ОС семейства Windows
     */
    private void initDriverWindowsOsFamily() {
        initDriverAnyOsFamily(PATH_GECKO_DRIVER_WINDOWS, PATH_CHROME_DRIVER_WINDOWS);
    }


    /**
     * Метод инициализирующий веб драйвер под ОС семейства Mac
     */
    private void initDriverMacOsFamily() {
        initDriverAnyOsFamily(PATH_GECKO_DRIVER_MAC, PATH_CHROME_DRIVER_MAC);
    }

    /**
     * Метод инициализирующий веб драйвер под ОС семейства Unix
     */
    private void initDriverUnixOsFamily() {
        initDriverAnyOsFamily(PATH_GECKO_DRIVER_UNIX, PATH_CHROME_DRIVER_UNIX);
    }


    /**
     * Метод инициализирующий веб драйвер под любую ОС
     *
     *
     * @param chrome - переменная chrome из файла application.properties в классе {@link utils.PropertieConstants}
     */
    private void initDriverAnyOsFamily(String gecko, String chrome) {
        switch (props.getProperty(TYPE_BROWSER)) {
            case "firefox":
                System.setProperty("webdriver.gecko.driver", props.getProperty(gecko));
                driver = new FirefoxDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", props.getProperty(chrome));
                driver = new ChromeDriver();
                break;
            default:
                Assert.fail("Типа браузера '" + props.getProperty(TYPE_BROWSER) + "' не существует во фреймворке");
        }
    }
}
