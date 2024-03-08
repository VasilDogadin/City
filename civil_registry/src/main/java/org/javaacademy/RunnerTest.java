package org.javaacademy;

import javaacademy.human.Human;
import org.javaacademy.civilRegistry.CivilRegistry;
import org.javaacademy.civilRegistry.model.Citizen;
import java.time.LocalDate;

public class RunnerTest {

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2024, 3, 20);
        CivilRegistry civilRegistry = new CivilRegistry("TEST_ZAGS");
        Citizen h1 = new Citizen(new Human("1", "1", "1", true));
        Citizen w1 = new Citizen(new Human("2", "2", "2", false));
        Citizen h2 = new Citizen(new Human("3", "3", "3", true));
        Citizen w2 = new Citizen(new Human("4", "4", "4", false));
        Citizen h3 = new Citizen(new Human("5", "5", "5", true));
        Citizen w3 = new Citizen(new Human("6", "6", "6", false));
        Citizen h4 = new Citizen(new Human("7", "7", "7", true));
        Citizen w4 = new Citizen(new Human("8", "8", "8", false));
        Citizen h5 = new Citizen(new Human("9", "9", "9", true));
        Citizen w5 = new Citizen(new Human("10", "10", "10", false));
        Citizen h6 = new Citizen(new Human("11", "11", "11", true));
        Citizen w6 = new Citizen(new Human("12", "12", "12", false));

        civilRegistry.registrationWeddings(h1, w1, date);
        civilRegistry.registrationWeddings(h2, w2, date);
        civilRegistry.registrationDivorce(h3, w3, date);
        civilRegistry.registrationBirth(h4.birthBaby("77", "77", "77", true, w4), h4, w4, date);
        civilRegistry.registrationBirth(h5.birthBaby("99", "99", "99", false, w5), h5, w5, date);
        civilRegistry.registrationBirth(h6.birthBaby("1111", "1111", "1111", true, w6), h6, w6, date);

        System.out.println(civilRegistry.getStatistics(date));
    }
}
