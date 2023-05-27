package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Класс страницы результатов поиска Yandex.
 * @author Кирилл Желтышев
 */
public class YandexSERPFactory {
    /**
     * Web-драйвер.
     * @author Кирилл Желтышев
     */
    protected WebDriver driver;

    /**
     * Web-элемент, содержащий все элементы результата поиска.
     * @author Кирилл Желтышев
     */
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'serp-item_card')]")
    protected WebElement serpItemCard;

    /**
     * Конструктор класса.
     * @author Кирилл Желтышев
     * @param webDriver web-драйвер
     */
    public YandexSERPFactory(WebDriver webDriver) {
        driver = webDriver;
        PageFactory.initElements(this.driver, this);
    }

    /**
     * Метод проверки наличия элемента с заголовком на странице результата поиска.
     * @author Кирилл Желтышев
     * @param title заголовок искомого элемента
     * @return true, если заголовок найден и false, если не найден
     */
    public Boolean checkSERPItemWithTitle(String title) {
        List<WebElement> itemsTitles = serpItemCard.findElements(titleLocator(title));
        return itemsTitles.size() > 0;
    }

    /**
     * Метод нажимает на элемент с заголовком на странице результата поиска.
     * @author Кирилл Желтышев
     * @param title заголовок искомого элемента
     */
    public void clickItemWithTitle(String title) {
        WebElement itemTitle = serpItemCard.findElement(titleLocator(title));
        itemTitle.click();
    }

    /**
     * Метод создания локатора по заголовку карточки искомого элемента.
     * @author Кирилл Желтышев
     * @param title заголовок
     * @return локатор
     */
    private By titleLocator(String title) {
        return By.xpath("//h2[normalize-space()='"+ title +"']") ;
    }
}
