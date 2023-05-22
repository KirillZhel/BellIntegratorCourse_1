package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GoogleAfterSearch extends GoogleBeforeSearch{
    private List<WebElement> results;
    private WebDriverWait wait;
    private WebElement result;

    public GoogleAfterSearch(WebDriver wd) {
        super(wd);
        //wait = new WebDriverWait(wd, 120);
    }

    public List<WebElement> getResults() {
        String xpath = "//*[@ID='search']//div[@class='TbwUpd NJjxre iUh30 apx8Vc ojE3Fb']//span[@class='VuuXrf']";
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        results = driver.findElements(By.xpath(xpath));
        return results;

        // //*[@ID='search']//div[@class='TbwUpd NJjxre iUh30 apx8Vc ojE3Fb']//span[@class='VuuXrf']

        // //*[@ID='search']//div[@class='MjjYud']
        // //*[@id="rso"]/div[4]/div
    }

    public WebElement getResult(String heading) {
        String xpath = "//h3[contains(text(), '" + heading + "')]";
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

        if (driver.findElements(By.xpath(xpath)).size() == 0) {
            return null;
        }
        result = driver.findElement(By.xpath(xpath));
        return result;
    }
}
