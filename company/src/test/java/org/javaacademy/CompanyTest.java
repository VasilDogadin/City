package org.javaacademy;

import javaacademy.human.Human;
import org.apache.commons.collections4.MultiSet;
import org.javaacademy.company.Company;
import org.javaacademy.profession.Manager;
import org.javaacademy.profession.Programmer;
import org.javaacademy.task.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

public class CompanyTest {
    Task task1 = new Task("Выпонить проект метро", false, 8.0);
    Task task2 = new Task("Создать доску", false, 1.0);
    Task task3 = new Task("Покрыть код тестами", false, 4.0);
    List<Task> taskList = new LinkedList<>(List.of(task1,task2,task3));

    @Test
    public void taskRemoveSuccess() {
        Company company = new Company("1700", "Oracle",
                new Manager(new Human("manager","manager","manager", false)),
                new LinkedList<>(new LinkedList<>(List.of(
                        new Programmer(new Human("programmer1", "programmer1","programmer1", true)),
                        new Programmer(new Human("programmer2", "programmer2","programmer2", true))
                ))));
        List<Task> expected = new LinkedList<>();
        company.weeklyWork(taskList);
        Assertions.assertEquals(taskList,expected);
        Assertions.assertNotEquals(taskList,new LinkedList<>(List.of(task1)));
    }

    @Test
    public void weeklyWorkProgrammerDequeSuccess() {
        Company company = new Company("1700", "Oracle",
                new Manager(new Human("manager","manager","manager", false)),
                new LinkedList<>(new LinkedList<>(List.of(
                        new Programmer(new Human("programmer1", "programmer1","programmer1", true)),
                        new Programmer(new Human("programmer2", "programmer2","programmer2", true))
                ))));
        List<Task> taskList = new LinkedList<>(List.of(task1,task2,task3));
        LinkedList<Programmer> programmers = new LinkedList<>(company.getProgrammers());
        company.weeklyWork(taskList);
        Assertions.assertEquals(programmers,company.getProgrammers());
    }

    @Test
    public void salaryPaymentSuccess() {
        Company company = new Company("1700", "Oracle",
                new Manager(new Human("manager","manager","manager", false)),
                new LinkedList<>(new LinkedList<>(List.of(
                        new Programmer(new Human("programmer1", "programmer1","programmer1", true)),
                        new Programmer(new Human("programmer2", "programmer2","programmer2", true))
                ))));
        company.getProgrammers().get(0).setMoneyEarned(new BigDecimal(30000));
        company.getManager().setMoneyEarned(new BigDecimal(10000));
        company.salaryPayment();
        BigDecimal expected = new BigDecimal(40000);
        Assertions.assertEquals(expected, company.getTotalExpenses());
    }

    @Test
    public void companyInfoSuccess() {
        Company company = new Company("1700", "Oracle",
                new Manager(new Human("manager","manager","manager", false)),
                new LinkedList<>(new LinkedList<>(List.of(
                        new Programmer(new Human("programmer1", "programmer1","programmer1", true)),
                        new Programmer(new Human("programmer2", "programmer2","programmer2", true))
                ))));

        company.weeklyWork(taskList);
        company.salaryPayment();
        company.companyInfo();

        Assertions.assertEquals("Oracle", company.getName());
        Assertions.assertFalse(company.getCompletedTasks().isEmpty());

        MultiSet<Programmer> programmers = company.getCompletedTasks().keys();
        Assertions.assertTrue(programmers.containsAll(company.getProgrammers()));

        Collection<Task> tasks = company.getCompletedTasks().values();
        Assertions.assertTrue(tasks.containsAll(taskList));
    }

}
