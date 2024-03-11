package org.javaacademy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javaacademy.human.Human;
import org.javaacademy.company.Company;
import org.javaacademy.profession.Manager;
import org.javaacademy.profession.Programmer;
import org.javaacademy.task.Task;


public class Runner {
    public static void main(String[] args) {
        Programmer programmer1 = new Programmer(
                new Human("Иван", "Иванов", "Иванович", true),
                new BigDecimal(1600));
        Programmer programmer2 = new Programmer(
                new Human("Екатерина", "Петрова", "Петровна", false),
                new BigDecimal(1800));
        List<Programmer> programmers = new LinkedList<>(new ArrayList<>(List.of(programmer1, programmer2)));
        Manager manager = new Manager(
                new Human("Юрий", "Юрьев", "Юрьевич",true)
        );

        Task task1 = new Task("Выпонить проект метро", false, 8.0);
        Task task2 = new Task("Создать доску", false, 1.0);
        Task task3 = new Task("Покрыть код тестами", false, 4.0);
        List<Task> taskList = new ArrayList<>(List.of(task1,task2,task3));

        Company oracle = new Company(args[0], args[1], manager, programmers);

        oracle.weeklyWork(taskList);
        oracle.salaryPayment();
        oracle.companyInfo();
    }
}
