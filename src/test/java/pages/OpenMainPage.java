package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OpenMainPage {
    protected WebDriver driver;
    //private WebDriverWait wait;
    protected WebElement exchangeLink;

    public OpenMainPage(WebDriver wd)
    {
        driver = wd;
        //String xpath = "//a[contains(text(), 'Все курсы')]";
        //exchangeLink = driver.findElement(By.xpath(xpath));
        exchangeLink = driver.findElement(By.linkText("Все курсы"));
        //wait = new WebDriverWait(wd, 120);
    }

    public void followExchangeLink() {
        //wait.until(ExpectedConditions.elementToBeClickable(exchangeLink));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Все курсы")));
        //Actions actions = new Actions(driver);
        //actions.moveToElement(exchangeLink).click().build().perform();

        //exchangeLink.click();

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", exchangeLink);
    }
}
