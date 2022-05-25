package ninth;

import java.util.Objects;

public class Human {
    private String surname;
    private String name;
    private String patronymic;
    private int age;
    private Gender gender;

    public Human(String surname, String name, String patronymic, int age, Gender gender) {
        setSurname(surname);
        setName(name);
        setPatronymic(patronymic);
        setAge(age);
        setGender(gender);
    }

    public void setSurname(String surname) {
        if (surname == null) {
            throw new IllegalArgumentException("The surname string is null");
        }
        this.surname = surname;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("The name string is null");
        }
        this.name = name;
    }

    public void setPatronymic(String patronymic) {
        if (patronymic == null) {
            throw new IllegalArgumentException("The patronymic string is null");
        }
        this.patronymic = patronymic;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age can't be less than zero");
        }
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human)) return false;
        Human human = (Human) o;
        return getAge() == human.getAge() &&
                Objects.equals(getSurname(), human.getSurname())
                && Objects.equals(getName(), human.getName())
                && Objects.equals(getPatronymic(), human.getPatronymic())
                && getGender() == human.getGender();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSurname(), getName(), getPatronymic(), getAge(), getGender());
    }
}
