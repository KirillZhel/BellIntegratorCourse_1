package com.google;

import core.BaseTest;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.GoogleAfterSearch;
import pages.GoogleBeforeSearch;

public class Task_1_1_Gladiolus extends BaseTest {

    /*
    * запустить Chrome
    * Открыть https://www.google.com/
    * Ввести «Гладиолус». Нажать поиск
    * Убедиться, что в полученной выборке на первой странице есть ссылка на Википедию
    */
    @Feature("Проверка выдачи поисковика google")
    @Test
    public void SearchGladiolus() {
        chromeDriver.get("https://www.google.com/");
        GoogleBeforeSearch googleBeforeSearch = new GoogleBeforeSearch(chromeDriver);
        googleBeforeSearch.find("Гладиолус");
        GoogleAfterSearch googleAfterSearch = new GoogleAfterSearch(chromeDriver);
        //googleAfterSearch.getResult().forEach(x -> System.out.println(x.getText()));
        String expectedDomain = "wikipedia.org";
        Assertions.assertTrue(googleAfterSearch.getResults().stream().anyMatch(x -> x.getText().contains(expectedDomain)),
                "На странице результатов поиска по запросу \"Гладиолус\" не найдена ссылка на " + expectedDomain);
    }
}
