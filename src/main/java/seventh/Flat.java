package seventh;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Flat implements java.io.Serializable {
    private int number;
    private double square;
    private final List<Person> owners;

    public Flat() {
        this.number = 0;
        this.square = 0;
        this.owners = new ArrayList<>();
    }

    public Flat(int number, double square, List<Person> owners) {
        if (owners == null) {
            throw new IllegalArgumentException("The list of owners is null");
        }
        setNumber(number);
        setSquare(square);
        this.owners = new ArrayList<>(owners);
    }

    public int getNumber() {
        return number;
    }

    public double getSquare() {
        return square;
    }

    public List<Person> getOwners() {
        return new ArrayList<>(owners);
    }

    public void setNumber(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("The number of flat cannot be less than 1: " + number);
        }
        this.number = number;
    }

    public void setSquare(double square) {
        if (square < 0) {
            throw new IllegalArgumentException("The square of flat cannot be less than 0: " + square);
        }
        this.square = square;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "number=" + number +
                ", square=" + square +
                ", owners=" + owners +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flat)) return false;
        Flat flat = (Flat) o;
        return getNumber() == flat.getNumber()
                && Double.compare(flat.getSquare(), getSquare()) == 0
                && Objects.equals(getOwners(), flat.getOwners());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getSquare(), getOwners());
    }
}
