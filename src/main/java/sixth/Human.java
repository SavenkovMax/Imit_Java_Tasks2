package sixth;

import java.util.Objects;

public class Human implements Comparable<Human> {
    private String surname;
    private String name;
    private String patronymic;
    private int age;

    public Human() {
        this.surname = "";
        this.name = "";
        this.patronymic = "";
        this.age = 0;
    }
    
    public Human(String surname, String name, String patronymic, int age) {
        setSurname(surname);
        setName(name);
        setPatronymic(patronymic);
        setAge(age);
    }

    public Human(Human human) {
        this.surname = human.surname;
        this.name = human.name;
        this.patronymic = human.patronymic;
        this.age = human.age;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if (surname == null) {
            throw new IllegalArgumentException("The surname string is null");
        }
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("The name string is null");
        }
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        if (patronymic == null) {
            throw new IllegalArgumentException("The patronymic string is null");
        }
        this.patronymic = patronymic;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("The age is less than zero");
        }
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human human)) return false;
        return getAge() == human.getAge() &&
                getSurname().equals(human.getSurname()) &&
                getName().equals(human.getName()) &&
                getPatronymic().equals(human.getPatronymic());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSurname(), getName(), getPatronymic(), getAge());
    }

    @Override
    public String toString() {
        return "Human{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Human human) {
        String[] fullName = {surname, name, patronymic};
        String[] fullNameHuman = {human.surname, human.name, human.patronymic};
        for (int i = 0; i < 3; i++) {
            if (fullName[i].compareTo(fullNameHuman[i]) > 0) {
                return 1;
            }
            if (fullName[i].compareTo(fullNameHuman[i]) < 0) {
                return -1;
            }
        }
        return 0;
    }
}
