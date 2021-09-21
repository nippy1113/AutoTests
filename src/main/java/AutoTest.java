import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class AutoTest {
    private WebDriver driver;
    private WebDriverWait wait;
    @Before
    public void before() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(55, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 25, 2500);

        String baseUrl = "https://www.rgs.ru/new/";
        driver.get(baseUrl);
    }

    @Test
    public void exampleScenario() {


        //Выбрать пункт в меню - "Компании"
        String companyInsideMenuXpath = "//a[contains(text(), 'Компаниям')]";
        WebElement toCompanies = driver.findElement(By.xpath(companyInsideMenuXpath));
      //  waitUtilElementToBeClickable(toCompanies);
        toCompanies.click();


        // Нажать на пункт - "Здоровье"
        String healthXpath = "//span[contains(text(), 'Здоровье') and @class = 'padding']";
        WebElement healthButton = driver.findElement(By.xpath(healthXpath));
        waitUtilElementToBeClickable(healthButton);
        healthButton.click();

        // Нажать на пункт - "Добровольное страхование здоровья"
        String healthInsuranceXpath = "//a[contains(text(), 'Добровольное медицинское страхование')]";
        WebElement healthInsuranceButton = driver.findElement(By.xpath(healthInsuranceXpath));
        waitUtilElementToBeClickable(healthInsuranceButton);
        healthInsuranceButton.click();

        // Проверить наличие заголовка - "Добровольное страхование здоровья"
        String titleXpath = "//h1[contains(text(), 'Добровольное медицинское страхование')]";
        waitUtilElementToBeVisible(By.xpath(titleXpath));
        WebElement pageTitle = driver.findElement(By.xpath(titleXpath));
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                "Добровольное медицинское страхование", pageTitle.getText());

        // Нажать на кнопку - "Отправить заявку"
        String applicationButtonXpath = "//button//span[text() = 'Отправить заявку']";
        WebElement sendApplicationButton = driver.findElement(By.xpath(applicationButtonXpath));
        scrollToElementJs(sendApplicationButton);
        waitUtilElementToBeClickable(sendApplicationButton);
        sendApplicationButton.click();

        // Проверить наличие заголовка - "Добровольное страхование здоровья"
        String title2Xpath = "//h2[contains(text(), 'Оперативно перезвоним')]";
        waitUtilElementToBeVisible(By.xpath(title2Xpath));
        WebElement pageTitle2 = driver.findElement(By.xpath(title2Xpath));
        Assert.assertEquals("Заголовок отсутствует/не соответствует требуемому",
                "Оперативно перезвоним\n" +
                        "для оформления полиса", pageTitle2.getText());

        // Заполнить поля данными
        String nameFieldXPath = "//input[@name = 'userName']";
        WebElement nameField = driver.findElement(By.xpath(nameFieldXPath));
        fillInputField(nameField, "Иванов Иван Иваныч");

        String telFieldXPath = "//input[@name = 'userTel']";
        WebElement telField = driver.findElement(By.xpath(telFieldXPath));
        scrollToElementJs(telField);
        waitUtilElementToBeClickable(telField);
        telField.sendKeys("9059529999");

        String emailFieldXPath = "//input[@name = 'userEmail']";
        WebElement emailField = driver.findElement(By.xpath(emailFieldXPath));
        fillInputField(emailField, "qwertyqwerty");

        String addressFieldXPath = "//input[@class = 'vue-dadata__input']";
        WebElement addressField = driver.findElement(By.xpath(addressFieldXPath));
        fillInputField(addressField, "qwererwtwe");


        String agreeFieldXPath = "//input[@type= 'checkbox' and @class = 'checkbox']";
        WebElement agreeField = driver.findElement(By.xpath(agreeFieldXPath));
        scrollWithOffset(agreeField, 0, -70);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", agreeField);


        //Нажать на кнопку "Свяжитесь со мной"
        String submitButtonXpath = "//button[@type = 'submit']";
        WebElement submitButton = driver.findElement(By.xpath(submitButtonXpath));
        scrollToElementJs(submitButton);
        waitUtilElementToBeClickable(submitButton);
        submitButton.click();


        //Проверить сообщение об ощибке у поля введите email
        String errorAlertXPath = "//span[contains(text(), 'Введите корректный адрес электронной почты')]";
        WebElement errorAlert = driver.findElement(By.xpath(errorAlertXPath));
        scrollToElementJs(errorAlert);
        waitUtilElementToBeVisible(errorAlert);
        Assert.assertEquals("Проверка ошибки у alert на странице не была пройдено",
                "Введите корректный адрес электронной почты", errorAlert.getText());


    }

    @After
    public void after(){
        driver.quit();
    }

    /**
     * Скрол до элемента на js коде
     *
     * @param element - веб элемент до которого нужно проскролить
     */
    private void scrollToElementJs(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Явное ожидание того что элемент станет кликабельный
     *
     * @param element - веб элемент до которого нужно проскролить
     */
    private void waitUtilElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Явное ожидание того что элемент станет видемым
     *
     * @param locator - локатор до веб элемент который мы ожидаем найти и который виден на странице
     */
    private void waitUtilElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Явное ожидание того что элемент станет видемым
     *
     * @param element - веб элемент который мы ожидаем что будет  виден на странице
     */
    private void waitUtilElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Заполнение полей определённым значений
     *
     * @param element - веб элемент (поле какое-то) которое планируем заполнить)
     * @param value - значение которы мы заполняем веб элемент (поле какое-то)
     */
    private void fillInputField(WebElement element, String value) {
        scrollToElementJs(element);
        waitUtilElementToBeClickable(element);
        element.sendKeys(value);
        boolean checkFlag = wait.until(ExpectedConditions.attributeContains(element, "value", value));
        Assert.assertTrue("Поле было заполнено некорректно", checkFlag);
    }

    public WebElement scrollWithOffset(WebElement element, int x, int y) {
        String code = "window.scroll(" + (element.getLocation().x + x) + ","
                + (element.getLocation().y + y) + ");";
        ((JavascriptExecutor) driver).executeScript(code, element, x, y);
        return element;
    }
}
