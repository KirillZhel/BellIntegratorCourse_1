package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * Класс главной страницы Yandex.
 * @author Кирилл Желтышев
 */
public class YandexMainFactory {
    /**
     * Web-драйвер.
     * @author Кирилл Желтышев
     */
    protected WebDriver driver;

    /**
     * Web-элемент строки поиска.
     * @author Кирилл Желтышев
     */
    @FindBy(how = How.ID, id = "text")
    protected WebElement searchField;

    /**
     * Конструктор класса.
     * @author Кирилл Желтышев
     * @param webDriver
     */
    public YandexMainFactory(WebDriver webDriver) {
        driver = webDriver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Метод открытия главной страницы.
     * @author Кирилл Желтышев
     */
    public void open() {
        driver.get("https://ya.ru/");
    }

    /**
     * Метод поиска по запросу.
     * @author Кирилл Желтышев
     * @param query запрос
     */
    public void search(String query) {
        this.searchField.sendKeys(query);
        this.searchField.submit();
    }
}
