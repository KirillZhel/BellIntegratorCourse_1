package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Класс главной страницы Google.
 * @author Кирилл Желтышев
 */
public class GoogleMainPage {
    /**
     * Web-драйвер.
     * @author Кирилл Желтышев
     */
    protected WebDriver driver;

    /**
     * Локатор для строки поиска.
     * @author Кирилл Желтышев
     */
    protected String searchField = "//textarea[@type='search']";

    /**
     * Конструктор класса.
     * @author Кирилл Желтышев
     * @param webDriver web-драйвер
     */
    public GoogleMainPage(WebDriver webDriver)
    {
        driver = webDriver;
    }

    /**
     * Метод поиска по запросу.
     * @author Кирилл Желтышев
     * @param query запрос
     */
    public void search(String query) {
        WebElement searchField = driver.findElement(By.xpath(this.searchField));
        searchField.sendKeys(query);
        searchField.submit();
    }

    /**
     * Метод открытия главной страницы.
     * @author Кирилл Желтышев
     */
    public void open() {
        driver.get("https://www.google.com/");
    }
}
