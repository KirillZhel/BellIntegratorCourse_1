package ru.open;

import core.BaseTest;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.GoogleSERPPage;
import pages.GoogleMainPage;
import pages.OpenRuExchangePage;
import pages.OpenRuMainPage;

import java.text.ParseException;
/**
 * Класс содержит тестовые методы для тестирования страницы курса валют банка "Открытие".
 * @author Кирилл Желтышев
 */
public class ExchangeTests extends BaseTest {
    /**
     * Тестовый метод проверяет отношение курсов покупки/продажи.
     * (Задание 1.2)
     * @author Кирилл Желтышев
     * @param currency проверяемая валюта
     */
    @Feature("Проверка курса на сайте банка Открытие")
    @DisplayName("Проверка курсов покупки и продажи в банке Открытие")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @CsvSource({"USD", "EUR"})
    public void checkExchangeTest(String currency) {
        GoogleMainPage googleMainPage = new GoogleMainPage(driver);
        googleMainPage.open();
        googleMainPage.search("Открытие");

        GoogleSERPPage googleSERPPage = new GoogleSERPPage(driver);
        String title = "Банк Открытие: кредит наличными — от 4% годовых";
        Assertions.assertTrue(googleSERPPage.checkSERPItemWithTitle(title), title + " - в результатах поиска отсутствует");
        googleSERPPage.clickItemWithTitle(title);

        OpenRuMainPage openRuMainPage = new OpenRuMainPage(driver);
        openRuMainPage.clickExchangeLink();
        OpenRuExchangePage openRuExchangePage = new OpenRuExchangePage(driver);
        double sellPrice = 0;
        double buyPrice = 0;
        try {
            sellPrice = openRuExchangePage.getSellPrice(currency);
            buyPrice = openRuExchangePage.getBuyPrice(currency);
        } catch (ParseException e) {
            Assertions.fail("парсинг" + currency + "не удался");
        }
        Assertions.assertTrue(buyPrice < sellPrice,currency + ": цена покупки больше цены продажи");
    }
}
