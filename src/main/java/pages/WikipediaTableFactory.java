package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс страницы википедии.
 * @author Кирилл Желтышев
 */
public class WikipediaTableFactory {
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
     * Web-элемент таблицы 'Преподаватели кафедры программирования'.
     * @author Кирилл Желтышев
     */
    @FindBy(how = How.XPATH, using = "//caption[contains(text(),'Преподаватели кафедры программирования')]/ancestor::table")
    protected WebElement teachersTable;

    /**
     * Локатор строк таблицы без шапки таблицы.
     * @author Кирилл Желтышев
     */
    protected String tableRow = ".//tbody/tr[not(./th)]";

    /**
     * Конструктор класса.
     * @author Кирилл Желтышев
     * @param webDriver Web-драйвер
     */
    public WikipediaTableFactory(WebDriver webDriver) {
        driver = webDriver;
        wait = new WebDriverWait(driver, 120);
        PageFactory.initElements(driver, this);
    }

    /**
     * Метод возвращает список строк таблицы 'Преподаватели кафедры программирования'.
     * @author Кирилл Желтышев
     * @return список строк таблицы
     */
    public List<String> getTeachersList() {
        wait.until(ExpectedConditions.visibilityOf(teachersTable));
        List<WebElement> rows = teachersTable.findElements(By.xpath(tableRow));
        return rows.stream()
                .map(row -> row.getText().trim().replace("\n", ""))
                .collect(Collectors.toList());
    }
}
