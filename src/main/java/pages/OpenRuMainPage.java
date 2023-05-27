package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Класс главной страницы сайта банка "Открытие".
 * @author Кирилл Желтышев
 */
public class OpenRuMainPage {
    /**
     * Web-драйвер.
     * @author Кирилл Желтышев
     */
    protected WebDriver driver;
    /**
     * Класс явного ожидания.
     * @author Кирилл Желтышев
     */
    protected WebDriverWait wait;
    /**
     * Локатор ссылки.
     * @author Кирилл Желтышев
     */
    protected String exchangeLinkLocator = "Все курсы";

    /**
     * Конструктор класса.
     * @param webDriver
     * @author Кирилл Желтышев
     */
    public OpenRuMainPage(WebDriver webDriver) {
        driver = webDriver;
        wait = new WebDriverWait(driver, 120);
    }

    /**
     * Метод перехода по ссылке на страницу с курсами валют.
     * @author Кирилл Желтышев
     */
    public void clickExchangeLink() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(exchangeLinkLocator)));
        WebElement exchangeLink = driver.findElement(By.linkText(exchangeLinkLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", exchangeLink);
    }
}
