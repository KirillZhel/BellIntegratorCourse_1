package org.wikipedia;

import core.BaseTest;
import core.Helper;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.WikipediaTableFactory;
import pages.YandexMainFactory;
import pages.YandexSERPFactory;

import java.util.List;

/**
 * Класс содержит тестовые методы для тестирования таблиц Википедии.
 * @author Кирилл Желтышев
 */
public class TableTests extends BaseTest {
    /**
     * Тестовый метод проверяет положение строк в таблице.
     * (Задание 1.3)
     * @author Кирилл Желтышев
     * @param first Ожидаемая первая строка таблицы
     * @param last Ожидаемая последняя строка таблицы
     */
    @Feature("Проверка таблицы на странице википедии")
    @DisplayName("Проверка положения")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @CsvSource({"Сергей Владимирович,Иван Иванович"})
    public void checkWikipediaTablePage(String first, String last) {
        YandexMainFactory yandexMainFactory = new YandexMainFactory(driver);
        yandexMainFactory.open();
        yandexMainFactory.search("Таблица");

        YandexSERPFactory yandexSERPFactory = new YandexSERPFactory(driver);
        Assertions.assertTrue(yandexSERPFactory.checkSERPItemWithTitle("Таблица — Википедия"));
        yandexSERPFactory.clickItemWithTitle("Таблица — Википедия");
        Helper.switchToLastTab(driver);

        WikipediaTableFactory wikipediaTableFactory = new WikipediaTableFactory(driver);
        List<String> teachersList = wikipediaTableFactory.getTeachersList();
        Assertions.assertTrue(teachersList.size() > 0, "Таблица не содержит данных");

        String firstTeacherName = teachersList.get(0);
        String lastTeacherName = teachersList.get(teachersList.size() - 1);
        Assertions.assertTrue(firstTeacherName.endsWith(first), "Первая строка таблицы \""  + firstTeacherName + "\" не содержит \"" + first + "\"");
        Assertions.assertTrue(lastTeacherName.endsWith(last), "Последняя строка таблицы \""  + lastTeacherName + "\" не содержит \"" + last + "\"");
    }

}
