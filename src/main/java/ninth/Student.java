package ninth;

import java.util.Objects;

public class Student extends Human {
    private String university;
    private String faculty;
    private String speciality;

    public Student(String surname, String name, String patronymic, int age, Gender gender,
                   String university, String faculty, String speciality) {
        super(surname, name, patronymic, age, gender);
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        if (university == null) {
            throw new IllegalArgumentException("The university string is null");
        }
        this.university = university;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        if (faculty == null) {
            throw new IllegalArgumentException("The faculty string is null");
        }
        this.faculty = faculty;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        if (speciality == null) {
            throw new IllegalArgumentException("The speciality string is null");
        }
        this.speciality = speciality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(getUniversity(), student.getUniversity())
                && Objects.equals(getFaculty(), student.getFaculty())
                && Objects.equals(getSpeciality(), student.getSpeciality());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getUniversity(), getFaculty(), getSpeciality());
    }
}
