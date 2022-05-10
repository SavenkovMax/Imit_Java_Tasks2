package eighth;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class ReflectionDemoTest {
    public class TestClass implements Executable {
        @Override
        public void execute() {

        }
    }
    public class TestClass2 extends TestClass {}

    @Test
    public void testGetCountInheritors() {
        ArrayList<?> list = new ArrayList<>(List.of(new Human(), new Student(), new Human(),
                new TestClass(), new TestClass2()));
        assertEquals(ReflectionDemo.getCountInheritors(list), 3);
    }

    @Test
    public void testGetOpenMethods() {
        ArrayList<String> names = new ArrayList<>(List.of("getSurname", "getAge",
                "getPhoneNumber", "setSurname",
                "setPhoneNumber", "setAge", "getName", "setName"));
        assertTrue(ReflectionDemo.getOpenMethods(new Human()).containsAll(names));
        assertTrue(names.containsAll(ReflectionDemo.getOpenMethods(new Human())));
    }

    @Test
    public void testGetListSuperClasses() {
        Student student = new Student();
        ArrayList<String> superClasses = new ArrayList<>(List.of("Human", "Object"));
        assertEquals(ReflectionDemo.getListSuperClasses(student), superClasses);
    }

    @Test
    public void testGetCountImplementers() {
        ArrayList<?> list = new ArrayList<>(List.of(new TestClass(), new TestClass2(), new Human(),
                new Student(), new Human()));
        assertEquals(ReflectionDemo.getCountImplementers(list), 2);
    }

    @Test
    public void testGetListGettersSetters() {
        ArrayList<String> gettersAndSetters = new ArrayList<>(List.of("getSurname", "getAge",
                "getPhoneNumber", "setSurname",
                "setPhoneNumber", "setAge", "getName", "setName"));
        assertTrue(ReflectionDemo.getListGettersSetters(new Human()).containsAll(gettersAndSetters));
        assertTrue(gettersAndSetters.containsAll(ReflectionDemo.getListGettersSetters(new Human())));
    }
}