package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleBeforeSearch {
    protected WebDriver driver;
    protected WebElement searchField;
    //protected WebElement searchButton;

    public GoogleBeforeSearch(WebDriver wd)
    {
        driver = wd;
        searchField = driver.findElement(By.xpath("//*[@id='APjFqb']"));
    }

    public void find(String word) {
        searchField.click();
        searchField.sendKeys(word);
        searchField.submit();
        //searchButton.click();
    }
}
