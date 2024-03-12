package org.javaacademy.company;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;
import lombok.*;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.javaacademy.profession.Employee;
import org.javaacademy.profession.Manager;
import org.javaacademy.profession.Programmer;
import org.javaacademy.task.Task;

@Getter
@Setter
public class Company {
    private String name;
    private Manager manager;
    private List<Programmer> programmers;
    private final MultiValuedMap<Programmer, Task> completedTasks = new ArrayListValuedHashMap<>();
    private final Map<Employee, Double> timeSheet = new HashMap<>();
    private BigDecimal totalExpenses = new BigDecimal(0);

    public Company(String hourlyRate, String name, Manager manager, List<Programmer> programmers) {
        this.name = name;
        this.manager = manager;
        this.programmers = programmers;
        programmers.forEach(programmer -> programmer.setHourlyRate(new BigDecimal(hourlyRate)));
    }

    public void weeklyWork(List<Task> tasks) {
        ArrayDeque<Programmer> programmerDeque = new ArrayDeque<>(programmers);
        while (!tasks.isEmpty()) {
            Programmer programmer = programmerDeque.removeFirst();
            Task task = tasks.remove(0);
            programmer.takeTask(task);
            programmerDeque.addLast(programmer);
            addSheetRecord(programmer, task.getTimeConsumed());
            addSheetRecord(manager, (task.getTimeConsumed() * 0.1));
            completedTasks.put(programmer, task);
        }
    }

    public void salaryPayment() {
        timeSheet.forEach((key, value) ->
                key.setMoneyEarned((key.getHourlyRate().multiply(BigDecimal.valueOf(value)))));
        totalExpenses = totalExpenses.add(manager.getMoneyEarned())
                .add(programmers.stream()
                .map(Programmer::getMoneyEarned)
                .reduce(totalExpenses,BigDecimal::add));
        timeSheet.clear();
    }

    public void companyInfo() {
        System.out.printf("%s\nЗатраты: %s\n",
                name, totalExpenses.setScale(2, RoundingMode.DOWN));
        printCompletedTask();
    }

    private void printCompletedTask() {
        System.out.println("Список выполненных задач у компании:");
        Map<String, String> map = completedTasks.entries()
                .stream()
                .collect(Collectors.toMap(programmerTaskEntry -> programmerTaskEntry.getKey().getFullName(),
                        programmerTaskEntry -> programmerTaskEntry.getValue().getDescription(),
                        (value1, value2) -> value1 + "; " + value2));
        map.forEach((key, value) -> System.out.println(key + " - " + value));
    }

    private void addSheetRecord(Employee employee, double time) {
        if (timeSheet.containsKey(employee)) {
            timeSheet.merge(employee, time, Double::sum);
        } else {
            timeSheet.put(employee, time);
        }
    }
}
