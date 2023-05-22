package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class WikiPageFactory {
    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "")
    WebElement table;
    public WikiPageFactory(WebDriver wd) {
        driver = wd;
    }
}
