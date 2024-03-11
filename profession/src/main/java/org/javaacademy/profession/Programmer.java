package org.javaacademy.profession;

import java.math.BigDecimal;
import javaacademy.human.Human;
import lombok.NonNull;
import org.javaacademy.exception.RateException;
import org.javaacademy.task.Task;

public class Programmer extends Employee {
    private static final BigDecimal MIN_RATE = new BigDecimal(1500);
    private static final BigDecimal MAX_RATE = new BigDecimal(2000);

    public Programmer(@NonNull Human human) {
        super(human);
    }

    public void takeTask(Task task) {
        task.setDone(true);
        System.out.println(task.getDescription() + " - " + " сделана");
    }

    @Override
    public void setHourlyRate(BigDecimal hourlyRate) {
        if (hourlyRate.compareTo(MIN_RATE) < 0 || hourlyRate.compareTo(MAX_RATE) > 0) {
            throw new RateException("Ставка должна быть от 1500 до 2000");
        }
        super.setHourlyRate(hourlyRate);
    }

    @Override
    public String toString() {
        return "Programmer{" +
                "surname=" + getSurname() +
                ", hourlyRate=" + hourlyRate +
                ", moneyEarned=" + moneyEarned +
                '}';
    }
}
