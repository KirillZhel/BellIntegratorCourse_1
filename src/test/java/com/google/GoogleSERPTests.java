package com.google;

import core.BaseTest;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.GoogleSERPPage;
import pages.GoogleMainPage;

/**
 * Класс содержит тестовые методы для проверки страницы результатов поиска Google.
 * @author Кирилл Желтышев
 */
public class GoogleSERPTests extends BaseTest {
    /**
     * Тестовый метод проверяет наличие определённого URL на странице результатов поиска по запросу.
     * (Задание 1.1)
     * @author Кирилл Желтышев
     * @param query запрос
     * @param expectedURL ожидаемый URL
     */
    @Feature("Проверка выдачи поисковика")
    @DisplayName("Проверка выдачи поисковика google")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @CsvSource("Гладиолус,wikipedia.org")
    public void checkSERPTest(String query, String expectedURL) {
        GoogleMainPage googleMainPage = new GoogleMainPage(driver);
        googleMainPage.open();
        googleMainPage.search(query);
        GoogleSERPPage googleSERPPage = new GoogleSERPPage(driver);
        Assertions.assertTrue(googleSERPPage.getResults().stream().anyMatch(x -> x.getText().contains(expectedURL)),
                "На странице результатов поиска по запросу \"" + query + "\" не найдена ссылка на " + expectedURL);
    }
}
