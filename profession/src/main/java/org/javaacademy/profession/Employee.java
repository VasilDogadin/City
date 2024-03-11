package org.javaacademy.profession;

import java.math.BigDecimal;
import javaacademy.human.Human;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public abstract class Employee extends Human {

    //Почасовая ставка
    BigDecimal hourlyRate;
    //Сумма заработанных денег
    BigDecimal moneyEarned = new BigDecimal(0);

    public Employee(@NonNull Human human) {
        super(human.getName(), human.getSurname(), human.getPatronymic(), human.isMale());
    }

}
