package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OpenExchangePage{
    private WebDriver driver;
    private WebDriverWait wait;
    private WebElement table;
    private List<WebElement> thead;

    public OpenExchangePage(WebDriver wd) {
        driver = wd;
        wait = new WebDriverWait(driver, 120);
        String xpathTable = "//section[not(@style='display: none;')]//table";
        String xpathThead = "//thead//tr//th";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathTable)));
        table = driver.findElement(By.xpath(xpathTable));
        thead = table.findElements(By.xpath(xpathThead));
    }

    public String getCellValue(String currency, String column) {
        String xpath = "//span[contains(text(),'" + currency + "')]/ancestor::tr//td";
        List<WebElement> elements = table.findElements(By.xpath(xpath));
        List<String> elementsSting = new ArrayList<>();
        elements.stream().map(WebElement::getText).forEach(elementsSting::add);
        return elementsSting.get(getColumnIndex(column));
    }

    public int getColumnIndex(String column) {
        List<String> theadStrList = new ArrayList<>();
        thead.stream().map(WebElement::getText).forEach(theadStrList::add);
        int index = theadStrList.indexOf(column);
        return index;
    }

    public double getSellPrice(String currency) throws ParseException {
        return parse(getCellValue(currency, "Банк продаёт"));
    }

    public double getBuyPrice(String currency) throws ParseException {
        return parse(getCellValue(currency, "Банк покупает"));
    }

    private double parse(String str) throws ParseException {
        NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
        Number number = format.parse(str);
        double d = number.doubleValue();
        return  d;
    }
}
