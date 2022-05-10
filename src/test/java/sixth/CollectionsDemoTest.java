package sixth;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static sixth.CollectionsDemo.ageMatchingList;
import static org.testng.Assert.*;

public class CollectionsDemoTest {
    private final Human human1 = new Human("Petrov", "Maxim", "Alexeeivich", 22);
    private final Human human2 = new Human("Ivanov", "Ivan", "Ivanovich", 18);
    private final Human human3 = new Human("Sergeev", "Sergey", "Sergeevich", 19);
    private final Human human4 = new Human("Ivanov", "Andrei", "Alexeevich", 20);
    private final Human human5 = new Human("Sergeev", "Petr", "Ivanovich", 17);
    private final Human[] humans = {human1, human2, human3, human4, human5};
    private final ArrayList<Human> listOfHumans = new ArrayList<>(Arrays.asList(humans));

    private final HashSet<Integer> set1 = new HashSet<>(List.of(13, 18, 20));
    private final HashSet<Integer> set2 = new HashSet<>(List.of(15, 17, 23, 30, 50));
    private final HashSet<Integer> set3 = new HashSet<>(List.of(11, 12, 20));
    private final HashSet<Integer> set4 = new HashSet<>(List.of(13, 15, 17, 18, 20, 21));

    private final HashMap<Integer, Human> mapAgeHumans = new HashMap<>(Map.of(human1.getAge(), human1,
            human2.getAge(), human2, human3.getAge(), human3, human4.getAge(), human4, human5.getAge(), human5));

    @DataProvider
    public Object[][] dataCountStrings() {
        String[] arrayOfStrings = {"Comparator", "Java", "JCF", "Collections", "JSON", "Array"};
        ArrayList<String> list = new ArrayList<>(Arrays.asList(arrayOfStrings));
        return new Object[][] {
                {list, 'J', 3},
                {list, 'C', 2},
                {list, 'B', 0},
                {list, 'A', 1},
        };
    }

    @DataProvider
    public Object[][] dataOfNamesakes() {
        Human namesake1 = new Human("Ivanov", "Ivan", "Anatolevich", 17);
        Human namesake2 = new Human("Petrov", "Ivan", "Ivanovich", 24);
        Human namesake3 = new Human("Fedorov", "Tristan", "Alexandrovich", 26);
        ArrayList<Human> listOfNamesakes1 = new ArrayList<>(List.of(human2, human4));
        ArrayList<Human> listOfNamesakes2 = new ArrayList<>(List.of(human1));
        ArrayList<Human> listOfNamesakes3 = new ArrayList<>();
        return new Object[][] {
                {listOfHumans, namesake1, listOfNamesakes1},
                {listOfHumans, namesake2, listOfNamesakes2},
                {listOfHumans, namesake3, listOfNamesakes3}
        };
    }

    @DataProvider
    public Object[][] dataListWithoutPerson() {
        Human[] arrayOfHumans = {human1, human2, human3, human3, human4, human5, human5, human5};
        ArrayList<Human> list = new ArrayList<>(Arrays.asList(arrayOfHumans));
        ArrayList<Human> expected1 = new ArrayList<>(List.of(human1, human2, human3, human5));
        ArrayList<Human> expected2 = new ArrayList<>(List.of(human1, human3, human4, human5));
        ArrayList<Human> expected3 = new ArrayList<>(List.of(human2, human3, human4, human5));
        ArrayList<Human> expected4 = new ArrayList<>(List.of(human1, human2, human3, human3, human4));
        ArrayList<Human> expected5 = new ArrayList<>(List.of(human1, human2, human4, human5, human5, human5));

        return new Object[][] {
                {listOfHumans, human4, expected1},
                {listOfHumans, human2, expected2},
                {listOfHumans, human1, expected3},
                {list, human5, expected4},
                {list, human3, expected5}
        };
    }

    @DataProvider
    public Object[][] dataListIntersection() {
        ArrayList<HashSet<Integer>> list = new ArrayList<>(List.of(set1, set2, set3, set4));
        HashSet<Integer> intersection1 = new HashSet<>(List.of(13, 11, 18, 20));
        HashSet<Integer> intersection2 = new HashSet<>(List.of(13, 15, 11, 20));
        HashSet<Integer> intersection3 = new HashSet<>(List.of(-100, 0, 1, 2));
        ArrayList<HashSet<Integer>> expected1 = new ArrayList<>(List.of(set2));
        ArrayList<HashSet<Integer>> expected2 = new ArrayList<>();

        return new Object[][] {
                {list, intersection1, expected1},
                {list, intersection2, expected2},
                {list, intersection3, list}
        };
    }

    @DataProvider
    public Object[][] dataSetOfMaxAge() {
        Student student1 = new Student("Savenkov", "Maxim", "Aleksandrovich", 23, "imit");
        Student student2 = new Student("Romanovskiy", "Ivan", "Ivanovich", 23, "imit");
        HashSet<Human> expected1 = new HashSet<>(List.of(human1));
        ArrayList<Human> list1 = new ArrayList<>(Arrays.asList(human1, human2, human3, human4, human5, student1, student2));
        ArrayList<Human> list2 = new ArrayList<>();
        HashSet<Human> expected2 = new HashSet<>(List.of(student1, student2));
        HashSet<Human> expected3 = new HashSet<>();

        return new Object[][] {
                {listOfHumans, expected1},
                {list1, expected2},
                {list2, expected3}
        };
    }

    @DataProvider
    public Object[][] dataSetWithIdentifiers() {
        HashSet<Integer> set = new HashSet<>(List.of(1, 2, 10));
        HashSet<Human> result1 = new HashSet<>(List.of(human2, human4));
        HashSet<Human> result2 = new HashSet<>(List.of(human5));
        HashSet<Human> result3 = new HashSet<>();
        HashSet<Human> result4 = new HashSet<>(List.of(human2, human4, human5));
        

        return new Object[][] {
                {mapAgeHumans, set1, result1},
                {mapAgeHumans, set2, result2},
                {mapAgeHumans, set, result3},
                {mapAgeHumans, set4, result4}
        };
    }

    @DataProvider
    public Object[][] dataMajorAge() {
        ArrayList<Human> result = new ArrayList<>(List.of(human1, human2, human3, human4));
        HashMap<Integer, Human> map = new HashMap<>(Map.of(human1.hashCode() + 1, human1, human2.hashCode() + 2, human2,
                human3.hashCode() + 3, human3, human4.hashCode() + 4, human4, human5.hashCode() + 5, human5));

        return new Object[][] {
                {mapAgeHumans, result},
                {map, result}
        };
    }

    @DataProvider
    public Object[][] dataAgeMap() {
        HashMap<Integer, Human> map1 = new HashMap<>(Map.of(1, human1, 2, human2, 4, human3,
                8, human4, 16, human5));
        HashMap<Integer, Integer> mapAge1 = new HashMap<>(Map.of(1, human1.getAge(), 2, human2.getAge(),
                4, human3.getAge(), 8, human4.getAge(), 16, human5.getAge()));
        HashMap<Integer, Human> map2 = new HashMap<>(Map.of(1, human1, 10, human2, 100, human3,
                1000, human4, 10000, human5));
        HashMap<Integer, Integer> mapAge2 = new HashMap<>(Map.of(1, human1.getAge(), 10, human2.getAge(),
                100, human3.getAge(), 1000, human4.getAge(), 10000, human5.getAge()));

        return new Object[][] {
                {map1, mapAge1},
                {map2, mapAge2}
        };
    }

    @DataProvider
    public Object[][] dataAgeMatchingList () {
        HashSet<Human> set = new HashSet<>(Set.of(humans));
        ArrayList<Human> list1 = new ArrayList<>(List.of(human1));
        ArrayList<Human> list2 = new ArrayList<>(List.of(human2));
        ArrayList<Human> list3 = new ArrayList<>(List.of(human3));
        ArrayList<Human> list4 = new ArrayList<>(List.of(human4));
        ArrayList<Human> list5 = new ArrayList<>(List.of(human5));
        HashMap<Integer, ArrayList<Human>> mapAge1 = new HashMap<>(Map.of(human1.getAge(), list1, human2.getAge(), list2,
                human3.getAge(), list3, human4.getAge(), list4, human5.getAge(), list5));

        return new Object[][] {
                {set, mapAge1}
        };
    }

    @DataProvider
    public Object[][] dataListOfHumans() {
        Student student1 = new Student("Артоновенко", "Марк", "Алексееивич", 20, "FMIT");
        Student student2 = new Student("Артоновенко", "Марк", "Антонович", 21, "FMIT");
        Human human1 = new Human("Александров", "Александр", "Данилович", 86);
        Human human2 = new Human("Александров", "Александр", "Александрович", 33);
        Human human3 = new Human("Александров", "Павел", "Сергеевич", 86);
        Human human4 = new Human("AA", "aa", "bb", 25);
        Human human5 = new Human("AA", "aA", "bb", 25);
        Human human6 = new Human("A", "aa", "bb", 25);
        TreeSet<? extends Human> set1 = new TreeSet<>(List.of(student1, student2));
        ArrayList<? extends Human> list1 = new ArrayList<>(List.of(student1, student2));
        TreeSet<? extends Human> set2 = new TreeSet<>(List.of(human1, human2, human3));
        ArrayList<? extends Human> list2 = new ArrayList<>(List.of(human2, human1, human3));
        TreeSet<? extends Human> set3 = new TreeSet<>(List.of(human4, human5, human6));
        ArrayList<? extends Human> list3 = new ArrayList<>(List.of(human6, human5, human4));

        return new Object[][] {
                {set1, list1},
                {set2, list2},
                {set3, list3}
        };
    }

    @Test(dataProvider = "dataCountStrings")
    public void testCountStrings(List<String> list, char symbol, int answer) {
        assertEquals(CollectionsDemo.countStrings(list, symbol), answer);
    }

    @Test(dataProvider = "dataOfNamesakes")
    public void testListOfNamesakes(List<Human> list, Human human, List<Human> expected) {
        assertEquals(CollectionsDemo.listOfNamesakes(list, human), expected);
    }

    @Test(dataProvider = "dataListWithoutPerson")
    public void testListWithoutPerson(List<Human> list, Human human, List<Human> expected) {
        assertEquals(CollectionsDemo.listWithoutPerson(list, human), expected);
    }

    @Test(dataProvider = "dataListIntersection")
    public void testListIntersection(List<Set<Integer>> list, Set<Integer> set, List<Set<Integer>> expected) {
        assertEquals(CollectionsDemo.listWithoutIntersection(list, set), expected);
    }

    @Test(dataProvider = "dataSetOfMaxAge")
    public void testSetOfMaxAge(List<Human> list, Set<Human> expected) {
        assertEquals(CollectionsDemo.setOfMaxAge(list), expected);
    }

    @Test(dataProvider = "dataSetWithIdentifiers")
    public void testSetWithIdentifiers(Map<Integer, Human> map, Set<Integer> set, Set<Human> result) {
        assertEquals(CollectionsDemo.setWithIdentifiers(map, set), result);
    }

    @Test(dataProvider = "dataMajorAge")
    public void testListMajorAge(Map<Integer, Human> map, List<Human> result) {
        assertTrue(CollectionsDemo.listMajorAge(map).containsAll(result));
    }

    @Test(dataProvider = "dataAgeMap")
    public void testAgeMap(Map<Integer, Human> map, Map<Integer, Integer> result) {
        assertEquals(CollectionsDemo.ageMap(map), result);
    }

    @Test(dataProvider = "dataAgeMatchingList")
    public void testAgeMatchingList(Set<Human> set, Map<Integer, List<Human>> result) {
        assertEquals(ageMatchingList(set), result);
    }

    @Test(dataProvider = "dataListOfHumans")
    public void testListOfHumans(TreeSet<? extends Human> set, List<? extends Human> list) {
        assertEquals(CollectionsDemo.listOfHumans(set), list);
    }


    @Test
    public void test10Task() {
            Human human1 = new Human("Petrov", "Maxim", "Alexeeivich", 22);
          Human human2 = new Human("Ivanov", "Ivan", "Ivanovich", 22);
         Human human3 = new Human("Sergeev", "Sergey", "Sergeevich", 19);
         Human human4 = new Human("Ivanov", "Andrei", "Alexeevich", 20);
          Human human5 = new Human("Sergeev", "Petr", "Ivanovich", 19);

          Set<Human> set = new HashSet<>(List.of( human1, human2, human3, human4, human5 ));
          Map<Integer, List<Human>> expected = new HashMap<>();
          expected.put(22, List.of(human2, human1));
          expected.put(19, List.of(human5, human3));
          expected.put(20, List.of(human4));

          assertEquals(ageMatchingList(set), expected);
    }
}