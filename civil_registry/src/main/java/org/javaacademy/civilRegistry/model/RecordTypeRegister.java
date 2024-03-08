package org.javaacademy.civilRegistry.model;

import javaacademy.human.Human;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Setter
@Getter
public class RecordTypeRegister {
    @NonNull
    private LocalDate date;
    @NonNull
    private TypeRegister typeRegister;
    @NonNull
    private List<Citizen> citizens;

    @Override
    public String toString() {
        return "Дата - " + date +
                ", тип гражданского действия - " + typeRegister.getValue() +
                ", граждане - " + citizens.stream()
                .map(Human::getFullName)
                .collect(Collectors.joining(", "));
    }
}
