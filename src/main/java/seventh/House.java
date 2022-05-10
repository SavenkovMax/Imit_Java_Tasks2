package seventh;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class House implements java.io.Serializable {
    private String cadastralNumber;
    private String address;
    private Person headOfHouse;
    private final List<Flat> flats;

    public House() {
        this.cadastralNumber = "";
        this.address = "";
        this.headOfHouse = new Person();
        this.flats = new ArrayList<>();
    }

    public House(String cadastralNumber, String address, Person headOfHouse, List<Flat> flats) {
        if (flats == null) {
            throw new IllegalArgumentException("The list of flats is null");
        }
        setCadastralNumber(cadastralNumber);
        setAddress(address);
        setHeadOfHouse(headOfHouse);
        this.flats = new ArrayList<>(flats);
    }

    public String getCadastralNumber() {
        return cadastralNumber;
    }

    public String getAddress() {
        return address;
    }

    public Person getHeadOfHouse() {
        return new Person(headOfHouse);
    }

    public List<Flat> getFlats() {
        return new ArrayList<>(flats);
    }

    public void setCadastralNumber(String cadastralNumber) {
        if (cadastralNumber == null) {
            throw new IllegalArgumentException("The cadastral numbers string is null");
        }
        this.cadastralNumber = cadastralNumber;
    }

    public void setAddress(String address) {
        if (address == null) {
            throw new IllegalArgumentException("The adress string is null");
        }
        this.address = address;
    }

    public void setHeadOfHouse(Person headOfHouse) {
        if (headOfHouse == null) {
            throw new IllegalArgumentException("The head of house is null");
        }
        this.headOfHouse = new Person(headOfHouse);
    }

    @Override
    public String toString() {
        return "House{" +
                "cadastralNumber='" + cadastralNumber + '\'' +
                ", adress='" + address + '\'' +
                ", headOfHouse=" + headOfHouse +
                ", flats=" + flats +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof House)) return false;
        House house = (House) o;
        return Objects.equals(cadastralNumber, house.cadastralNumber)
                && Objects.equals(address, house.address)
                && Objects.equals(headOfHouse, house.headOfHouse)
                && Objects.equals(flats, house.flats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cadastralNumber, address, headOfHouse, flats);
    }
}
