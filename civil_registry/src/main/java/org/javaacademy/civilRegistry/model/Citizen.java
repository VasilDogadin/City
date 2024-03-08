package org.javaacademy.civilRegistry.model;

import javaacademy.human.Human;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Citizen extends Human {
    private FamilyStatus familyStatus;
    private Human partner;

    public Citizen(Human human) {
        super(human.getName(), human.getSurname(), human.getPatronymic(), human.isMale());
        familyStatus = FamilyStatus.SINGLE;
    }
}
