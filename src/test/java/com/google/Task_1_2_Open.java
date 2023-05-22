package com.google;

import core.BaseTest;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebElement;
import pages.GoogleAfterSearch;
import pages.GoogleBeforeSearch;
import pages.OpenExchangePage;
import pages.OpenMainPage;

import java.text.ParseException;

public class Task_1_2_Open extends BaseTest {
    /*
    * запустить Chrome
    * открыть https://www.google.com/
    * написать в строке поиска «Открытие»
    * нажать «Поиск»
    * проверить, что результатах поиска есть ”Банк Открытие: кредит наличными — от 4% годовых”
    * кликнуть по данному заголовку
    * в блоке «Курс обмена» кликнуть на ссылку «Все курсы»
    * В открывшейся странице проверить в блоке «Курс обмена», что курс продажи больше курса покупки, для USD и для EUR.
    */

    @Feature("Проверка курса на сайте банка Открытие")
    @DisplayName("Проверка курсов покупки и продажи в банке Открытие")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @CsvSource({"USD", "EUR"})
    public void exchangeCheck(String currency) {
        //поиск и переход на главную страницу сайта банка Открытие
        chromeDriver.get("https://www.google.com/");
        GoogleBeforeSearch googleBeforeSearch = new GoogleBeforeSearch(chromeDriver);
        //по идее можно заменить например на googleBeforeSearch.findOpenRu() что бы вообще избежать хардкода в тестах
        googleBeforeSearch.find("Открытие");
        GoogleAfterSearch googleAfterSearch = new GoogleAfterSearch(chromeDriver);
        WebElement element = googleAfterSearch.getResult("Банк Открытие: кредит наличными — от 4% годовых");
        Assertions.assertNotNull(element, "в результатах поиска нет \"Банк Открытие: кредит наличными — от 4% годовых\"");
        element.click();

        //переход на страницу курсов обмена на сайте банка открытие
        OpenMainPage openMainPage = new OpenMainPage(chromeDriver);
        openMainPage.followExchangeLink();
        //Assertions.assertTrue(true);

        // "USD" и "EUR" перенести в параметризацию
        // @ParameterizedTest(name = "{displayName}: {arguments}")
        // @CsvSource({"RPA,Кирилл Филенков","нагрузочное тестирование,Сергей Минаев"})
        OpenExchangePage openExchangePage = new OpenExchangePage(chromeDriver);


        double sellPrice = 0;
        double buyPrice = 0;
        try {
            sellPrice = openExchangePage.getSellPrice(currency);
            buyPrice = openExchangePage.getBuyPrice(currency);
        } catch (ParseException e) {
            Assertions.fail("парсинг" + currency + "не удался");
        }

        Assertions.assertTrue(buyPrice < sellPrice,currency + ": цена покупки больше цены продажи");
    }
}
