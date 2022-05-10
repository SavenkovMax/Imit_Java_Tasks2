package eighth;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public final class ReflectionDemo {
    private ReflectionDemo() {}

    public static int getCountInheritors(List<?> list) {
        int count = 0;
        for (Object obj : list) {
            if (obj instanceof Human) {
                ++count;
            }
        }
        return count;
    }

    public static List<String> getOpenMethods(Object obj) {
        Method[] methods = obj.getClass().getDeclaredMethods();
        ArrayList<String> list = new ArrayList<>();
        for (Method item: methods) {
            if (item.getModifiers() == Modifier.PRIVATE) continue;
            list.add(item.getName());
        }
        return list;
    }

    public static List<String> getListSuperClasses(Object obj) {
        ArrayList<String> list = new ArrayList<>();
        Class<?> objClass = obj.getClass().getSuperclass();
        while (objClass != null) {
            list.add(objClass.getSimpleName());
            objClass = objClass.getSuperclass();
        }
        return list;
    }

    public static int getCountImplementers(List<?> list) {
        int count = 0;
        for (Object obj : list) {
            if (Executable.class.isAssignableFrom(obj.getClass())) {
                ++count;
                ((Executable) obj).execute();
            }
        }
        return count;
    }


    public static List<String> getListGettersSetters(Object obj) {
        ArrayList<String> list = new ArrayList<>();
        Method[] methods = obj.getClass().getDeclaredMethods();
        for (Method item : methods) {
            if (!Modifier.isPrivate(item.getModifiers()) && !Modifier.isStatic(item.getModifiers()) && ((
                    item.getName().startsWith("get") &&
                    !(item.getReturnType().equals(Void.TYPE)) &&
                    item.getParameterCount() == 0)
                    ||
                    item.getName().startsWith("set") &&
                    item.getReturnType().equals(Void.TYPE) &&
                    item.getParameterCount() == 1)
            ) {
                list.add(item.getName());
            }
        }
        return list;
    }
}
