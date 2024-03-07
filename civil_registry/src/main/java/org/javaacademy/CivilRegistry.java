package org.javaacademy;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import javaacademy.human.Human;
import org.javaacademy.model.Citizen;
import org.javaacademy.model.RecordTypeRegister;
import static org.javaacademy.model.FamilyStatus.*;
import static org.javaacademy.model.TypeRegister.*;

public class CivilRegistry {
    private final String name;
    private final TreeMap<LocalDate, List<RecordTypeRegister>> records;

    public CivilRegistry(String name) {
        this.name = name;
        this.records = new TreeMap<>(LocalDate::compareTo);
    }

    public static Citizen createCitizen(Human human) {
        return new Citizen(human);
    }

    public void registrationBirth(Human child, Citizen father, Citizen mother, LocalDate dateRegister) {
        Citizen children = createCitizen(child);
        RecordTypeRegister record = new RecordTypeRegister(
                dateRegister,
                BIRTH,
                List.of(children, father, mother));
        addRecord(record);
    }

    public void registrationWeddings(Citizen husband, Citizen wife, LocalDate dateRegister) {
        husband.checkSameGender(wife);
        husband.setPartner(wife);
        husband.setFamilyStatus(MARRIED);
        wife.setPartner(husband);
        wife.setFamilyStatus(MARRIED);
        addRecord(new RecordTypeRegister(
                dateRegister,
                WEDDINGS,
                List.of(husband, wife)));
    }

    public void registrationDivorce(Citizen husband, Citizen wife, LocalDate dateRegister) {
        husband.setFamilyStatus(DIVORCED);
        husband.setPartner(null);
        wife.setFamilyStatus(DIVORCED);
        wife.setPartner(null);
        addRecord(new RecordTypeRegister(
                dateRegister,
                DIVORCE,
                List.of(husband, wife)));
    }

    private void addRecord(RecordTypeRegister record) {
        LocalDate key = record.getDate();
        if (records.containsKey(key)) {
            records.get(key).add(record);
        } else {
            records.put(key, new ArrayList<>(List.of(record)));
        }
    }

    public String getStatistics(LocalDate date) {
        if (!records.containsKey(date)) {
            return String.format("Статистика по ЗАГС: %s \nДата %s: нет ни одной записи за эту дату!", name, date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
        List<RecordTypeRegister> recordsForDate = records.get(date);
        long countWeddings = recordsForDate.stream()
                .filter(record -> record.getTypeRegister().equals(WEDDINGS))
                .count();
        long countDivorces = recordsForDate.stream()
                .filter(record -> record.getTypeRegister().equals(DIVORCE))
                .count();
        long countBirth = recordsForDate.stream()
                .filter(record -> record.getTypeRegister().equals(BIRTH))
                .count();
        return String.format("Статистика по ЗАГС: %s \nДата %s: количество свадеб - %s, количество разводов - %s, количество рождений - %s",
                name,
                date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                countWeddings,
                countDivorces,
                countBirth);
    }
}
