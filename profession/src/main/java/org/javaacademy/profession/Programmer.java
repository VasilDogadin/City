package org.javaacademy.profession;

import java.math.BigDecimal;
import javaacademy.human.Human;
import lombok.NonNull;
import org.javaacademy.exception.RateException;
import org.javaacademy.task.Task;

public class Programmer extends Employee {
    private static final BigDecimal minRate = new BigDecimal(1500);
    private static final BigDecimal maxRate = new BigDecimal(2000);

    public Programmer(@NonNull Human human) {
        super(human);
    }

    public Programmer(@NonNull Human human,BigDecimal hourlyRate) {
        super(human);
        setHourlyRate(hourlyRate);
    }

    public void takeTask(Task task) {
        task.setDone(true);
    }

    @Override
    public void setHourlyRate(BigDecimal hourlyRate) {
        if (hourlyRate.compareTo(minRate) < 0 || hourlyRate.compareTo(maxRate) > 0) {
            throw new RateException("Ставка должна быть от 1500 до 2000");
        }
        super.setHourlyRate(hourlyRate);
    }
}
