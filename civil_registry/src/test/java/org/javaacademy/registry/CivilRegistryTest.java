package org.javaacademy.registry;

import javaacademy.human.Human;
import org.javaacademy.registry.model.Citizen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class CivilRegistryTest {
    private static final String NAME = "Тест";
    private static final LocalDate EXIST_DATE = LocalDate.of(2024, 2, 2);
    private static final LocalDate NOT_EXIST_DATE = LocalDate.of(2024, 3, 3);
    private static final Human CHILD = new Human("child", "child", "child", true);
    private static final Human MAN = new Human("man", "man", "man", true);
    private static final Human WOMAN_1 = new Human("woman_1", "woman_1", "woman_1", false);
    private static final Human WOMAN_2 = new Human("woman_2", "woman_2", "woman_2", false);
    private static final Citizen CITIZEN_1 = new Citizen(MAN);
    private static final Citizen CITIZEN_2 = new Citizen(WOMAN_1);
    private static final Citizen CITIZEN_3 = new Citizen(WOMAN_2);
    private static final CivilRegistry CIVIL_REGISTRY = new CivilRegistry(NAME);

    @Test
    @DisplayName("Проверка регистрации Брака")
    void registrationBirth() {
        CIVIL_REGISTRY.registrationBirth(CHILD, CITIZEN_1, CITIZEN_2, EXIST_DATE);
        CIVIL_REGISTRY.registrationWeddings(CITIZEN_1, CITIZEN_2, EXIST_DATE);
        CIVIL_REGISTRY.registrationDivorce(CITIZEN_1, CITIZEN_2, EXIST_DATE);
        Assertions.assertEquals(CIVIL_REGISTRY.getStatistics(EXIST_DATE),
                String.format("Статистика по ЗАГС: %s \n" +
                                "Дата %s: количество свадеб - 1, количество разводов - 1, количество рождений - 1",
                        NAME,
                        EXIST_DATE.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
    }

    @Test
    @DisplayName("Одинаковый гендер у людей")
    void checkGender() {
        Assertions.assertThrows(RuntimeException.class,
                () -> CIVIL_REGISTRY.registrationWeddings(CITIZEN_2, CITIZEN_3, EXIST_DATE));
    }

    @Test
    @DisplayName("Нет ни одно записи за требуемую дату")
    void noRecordsOnDate() {
        Assertions.assertEquals(CIVIL_REGISTRY.getStatistics(NOT_EXIST_DATE),
                String.format("Статистика по ЗАГС: %s \n" +
                                "Дата %s: нет ни одной записи за эту дату!",
                        NAME,
                        NOT_EXIST_DATE.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
    }
}