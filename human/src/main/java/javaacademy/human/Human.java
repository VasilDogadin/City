package javaacademy.human;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javaacademy.exception.HumanSameGenderException;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
public class Human {
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    //отчество
    private String patronymic;
    private final boolean isMale;
    private Human father;
    private Human mother;
    private List<Human> children = new ArrayList<>();

    public Human(@NonNull String name, @NonNull String surname, @NonNull String patronymic, boolean isMale) {
        this.name = StringUtils.capitalize(name);
        this.surname = StringUtils.capitalize(surname);
        this.patronymic = StringUtils.capitalize(patronymic);
        this.isMale = isMale;
    }

    public Human birthBaby(String name, String surname, String patronymic, boolean isMale, Human parent2) {
        checkSameGender(parent2);
        Human human = new Human(name, surname, patronymic, isMale);
        human.addParent(this, parent2);
        return human;
    }

    public String getFullName() {
        return StringUtils.joinWith(" ", name, surname, patronymic);
    }

    private void addParent(Human parent1, Human parent2) {
        checkSameGender(parent2);
        if (parent1.isMale) {
            this.father = parent1;
            this.mother = parent2;
        } else {
            this.father = parent2;
            this.mother = parent1;
        }
        parent1.children.add(this);
        parent2.children.add(this);
    }

    protected void checkSameGender(Human human) {
        if (this.isMale == human.isMale) {
            throw new HumanSameGenderException("У людей один пол");
        }
    }

    private String printParent(Human parent) {
        if (parent == null) {
            return null;
        }
        return parent.getFullName();
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", isMale=" + isMale +
                ", father=" + printParent(father) +
                ", mother=" + printParent(mother) +
                ", children=" + children.stream()
                .map(Human::getFullName)
                .collect(Collectors.joining(", ")) +
                '}';
    }
}
