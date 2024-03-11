package org.javaacademy.profession;

import java.math.BigDecimal;
import javaacademy.human.Human;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public abstract class Employee extends Human {
    @Getter
    @Setter
    BigDecimal hourlyRate;

    public Employee(@NonNull Human human) {
        super(human.getName(), human.getSurname(), human.getPatronymic(), human.isMale());
    }
}
