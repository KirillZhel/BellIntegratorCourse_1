package com.google;

import core.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class Tests extends BaseTest {
    @Test
    public void testTitle() {
        chromeDriver.get("https://bellintegrator.ru/");
        String title = chromeDriver.getTitle();
        System.out.println(title);
        Assertions.assertTrue(title.contains("Bell Integrator"), "заголовок не содержит Bell Integrator");
    }

    ////table//span[contains(text(),'USD')]/ancestor::tr

    @Test
    public void testTest() {
        chromeDriver.get("https://www.open.ru/exchange-person?from=main_page");
        String xpathTrUsd = "//span[contains(text(),'USD')]/ancestor::tr//td";
        String xpathTrEur = "//span[contains(text(),'EUR')]/ancestor::tr//td";
        String xpathTable = "//section[not(@style='display: none;')]//table[@class='card-rates-table cards']";
        WebDriverWait wait = new WebDriverWait(chromeDriver, 120);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathTrUsd)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathTable)));
        WebElement table = chromeDriver.findElement(By.xpath(xpathTable));
        WebElement element = chromeDriver.findElement(By.xpath(xpathTrUsd));
        List<WebElement> thead = table.findElements(By.xpath("//thead//tr//th"));
        System.out.println(thead.size());
        for (WebElement a:
                thead) {
            System.out.println(a.getText());
        }

        List<String> lst1 = new ArrayList<>();
        thead.stream().map(WebElement::getText).forEach(lst1::add);
        int nameIndex = lst1.indexOf("Валюта обмена");
        int sellIndex = lst1.indexOf("Банк продаёт");
        int buyIndex = lst1.indexOf("Банк покупает");

        System.out.println(buyIndex);
        System.out.println(sellIndex);

        List<WebElement> usd = table.findElements(By.xpath(xpathTrUsd));
        List<WebElement> eur = table.findElements(By.xpath(xpathTrEur));
        List<String> lstUsd = new ArrayList<>();
        usd.stream().map(WebElement::getText).forEach(lstUsd::add);
        List<String> lstEur = new ArrayList<>();
        eur.stream().map(WebElement::getText).forEach(lstEur::add);

        System.out.println(lstUsd.get(nameIndex) + " " + lstUsd.get(sellIndex) + " " + lstUsd.get(buyIndex));
        System.out.println(lstEur.get(nameIndex) + " " + lstEur.get(sellIndex) + " " + lstEur.get(buyIndex));

        System.out.println(element.getText());
        String[] str = element.getText().split(" ");
        System.out.println(element);
        for (String a:
        str) {
            System.out.println(a);
        }
        Assertions.assertTrue(true);
    }
}
