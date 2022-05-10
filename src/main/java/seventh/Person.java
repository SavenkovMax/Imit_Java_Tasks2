package seventh;

import java.util.Objects;

public class Person implements java.io.Serializable {
    private String birthDate;
    private String surname;
    private String name;
    private String patronymic;

    public Person() {
        this.surname = "";
        this.name = "";
        this.patronymic = "";
        this.birthDate = "01.01.1970";
    }

    public Person(String surname, String name, String patronymic, String birthDate) {
        setSurname(surname);
        setName(name);
        setPatronymic(patronymic);
        setBirthDate(birthDate);
    }

    public Person(Person person) {
        if (person == null) {
            throw new IllegalArgumentException("The person is null");
        }
        this.surname = person.surname;
        this.name = person.name;
        this.patronymic = person.patronymic;
        this.birthDate = person.birthDate;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setSurname(String surname) {
        if (surname == null) {
            throw new IllegalArgumentException("The surname is null");
        }
        this.surname = surname;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("The name is null");
        }
        this.name = name;
    }

    public void setPatronymic(String patronymic) {
        if (patronymic == null) {
            throw new IllegalArgumentException("The patronymic is null");
        }
        this.patronymic = patronymic;
    }

    public void setBirthDate(String birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("The birth date is null");
        }
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getSurname(), person.getSurname())
                && Objects.equals(getName(), person.getName())
                && Objects.equals(getPatronymic(), person.getPatronymic())
                && Objects.equals(birthDate, person.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSurname(), getName(), getPatronymic(), getBirthDate());
    }

    @Override
    public String toString() {
        return "Person{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
