package org.javaacademy.profession;

import java.math.BigDecimal;
import javaacademy.human.Human;
import lombok.NonNull;

public class Manager extends Employee {
    private static final BigDecimal MANAGER_RATE = new BigDecimal(10_000);

    public Manager(@NonNull Human human) {
        super(human);
        setHourlyRate(MANAGER_RATE);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "surname=" + getSurname() +
                ", hourlyRate=" + hourlyRate +
                ", moneyEarned=" + moneyEarned +
                '}';
    }
}
