package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Класс страницы результатов поиска Google.
 * @author Кирилл Желтышев
 */
public class GoogleSERPPage extends GoogleMainPage {
    /**
     * Локатор ссылок элементов результата поиска.
     * @author Кирилл Желтышев
     */
    protected String searchResultsLinks = "//cite[@role='text']";
    /**
     * Локатор элемента, содержащго все элементы результата поиска.
     * @author Кирилл Желтышев
     */
    protected String searchResult = "//div[@role='main']";
    /**
     * Объект явного ожидания.
     * @author Кирилл Желтышев
     */
    protected WebDriverWait wait;

    /**
     * Конструктор класса.
     * @author Кирилл Желтышев
     * @param webDriver web-драйвер
     */
    public GoogleSERPPage(WebDriver webDriver) {
        super(webDriver);
        wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchResult)));
    }

    /**
     * Метод возвращает ссылки элементов результата поиска.
     * @author Кирилл Желтышев
     * @return список web-элементов
     */
    public List<WebElement> getResults() {
        return driver.findElements(By.xpath(searchResultsLinks));
    }

    /**
     * Метод возвраает web-элемент по его заголовку.
     * @author Кирилл Желтышев
     * @param title заголовок искомого элемента
     * @return web-элемент
     */
    public WebElement getResultByTitle(String title) {
        String xpath = "//h3[contains(text(), '" + title + "')]";

        if (driver.findElements(By.xpath(xpath)).size() == 0) {
            return null;
        }
        return driver.findElement(By.xpath(xpath));
    }

    /**
     * Метод проверки наличия элемента с заголовком на странице результата поиска.
     * @author Кирилл Желтышев
     * @param title заголовок искомого элемента
     * @return true, если заголовок найден и false, если не найден
     */
    public Boolean checkSERPItemWithTitle(String title) {
        return getResultByTitle(title) != null;
    }

    /**
     * Метод нажимает на элемент с заголовком на странице результата поиска.
     * @author Кирилл Желтышев
     * @param title заголовок искомого элемента
     */
    public void clickItemWithTitle(String title) {
        WebElement element = getResultByTitle(title);
        element.click();
    }
}
