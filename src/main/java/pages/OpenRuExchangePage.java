package pages;

import core.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс страницы с курсами валют на сайте банка "Открытие".
 * @author Кирилл Желтышев
 */
public class OpenRuExchangePage {
    /**
     * Web-драйвер.
     * @author Кирилл Желтышев
     */
    protected WebDriver driver;
    /**
     * Объект явного ожидания.
     * @author Кирилл Желтышев
     */
    protected WebDriverWait wait;
    /**
     * Локатор таблицы с курсами валют.
     * @author Кирилл Желтышев
     */
    protected String tableLocator = "//section[not(@style='display: none;')]//table";
    /**
     * Локатор шапки таблицы с курсами валют.
     * @author Кирилл Желтышев
     */
    protected String theadLocator = "//thead//tr//th";

    /**
     * Конструктор класса.
     * @author Кирилл Желтышев
     * @param webDriver web-драйвер
     */
    public OpenRuExchangePage(WebDriver webDriver) {
        driver = webDriver;
        wait = new WebDriverWait(driver, 120);
    }

    /**
     * Метод возвращает значение ячейки таблицы по названию валюты и названию столбца.
     * @author Кирилл Желтышев
     * @param currency название валюта
     * @param column название столбца
     * @return значение ячейки
     */
    public String getCellValue(String currency, String column) {
        String xpath = "//span[contains(text(),'" + currency + "')]/ancestor::tr//td";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableLocator)));
        WebElement table = driver.findElement(By.xpath(tableLocator));
        List<WebElement> row = table.findElements(By.xpath(xpath));
        List<String> cells = new ArrayList<>();
        row.stream().map(WebElement::getText).forEach(cells::add);
        return cells.get(getColumnIndex(table, column));
    }

    /**
     * Метод вовзращает индекс столбца таблицы по названию
     * @author Кирилл Желтышев
     * @param table таблица
     * @param column название столбца таблицы
     * @return индекс столбца
     */
    public int getColumnIndex(WebElement table, String column) {
        List<String> theadStrList = new ArrayList<>();
        List<WebElement> thead = table.findElements(By.xpath(theadLocator));
        thead.stream().map(WebElement::getText).forEach(theadStrList::add);
        return theadStrList.indexOf(column);
    }

    /**
     * Метод возвращает значения стоимости продажи валюты
     * @author Кирилл Желтышев
     * @param currency валюта
     * @return стоимость продажи
     * @throws ParseException исключение, если не получилось спарсить значение
     */
    public double getSellPrice(String currency) throws ParseException {
        return Helper.parse(getCellValue(currency, "Банк продаёт"));
    }

    /**
     * Метод возвращает значения стоимости покупки валюты
     * @author Кирилл Желтышев
     * @param currency валюта
     * @return стоимость продажи
     * @throws ParseException исключение, если не получилось спарсить значение
     */
    public double getBuyPrice(String currency) throws ParseException {
        return Helper.parse(getCellValue(currency, "Банк покупает"));
    }
}
