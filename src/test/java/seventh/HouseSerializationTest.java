package seventh;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

import static org.testng.Assert.*;

public class HouseSerializationTest {
    private House house;
    private File tempFile;

    @BeforeTest
    public void setUpHouse() {
        ArrayList<Flat> flats = new ArrayList<>(3);
        ArrayList<Person> owners1 = new ArrayList<>(3);
        owners1.add(new Person("b", "s", "a", "09.04.2002"));
        owners1.add(new Person("a", "b", "c", "05.06.2001"));
        owners1.add(new Person("i", "y", "b", "02.05.1996"));
        ArrayList<Person> owners2 = new ArrayList<>(2);
        owners2.add(new Person("n", "b", "n", "12.12.1967"));
        owners2.add(new Person("k", "k", "k", "03.05.1970"));
        flats.add(new Flat(20, 65, owners1));
        flats.add(new Flat(21, 39, owners2));
        house = new House("123", "hh",
                new Person("1", "2", "3", "20.09.2002"), flats);
    }

    @BeforeMethod
    public void setUpFile() throws IOException {
        tempFile = Files.createTempFile("HouseSerializationTest", ".txt").toFile();
    }

    @AfterMethod
    public void deleteFile() {
        tempFile.delete();
    }

    @Test
    public void testSerializeHouse() throws IOException, ClassNotFoundException {
        ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(tempFile));
        HouseSerialization.serializeHouse(house, ous);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tempFile));
        assertEquals(house, HouseSerialization.deserializeHouse(ois));
    }

    @Test
    public void testJsonSerializeHouse() throws IOException {
        assertEquals(house, HouseJacksonSerialization.deserializeHouse(HouseJacksonSerialization.serializeHouse(house)));
    }
}