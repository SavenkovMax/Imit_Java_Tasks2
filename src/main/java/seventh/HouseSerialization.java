package seventh;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class HouseSerialization {

    private HouseSerialization() {}

    public static void serializeHouse(House house, ObjectOutputStream outputStream) throws IOException {
        outputStream.writeObject(house);
        outputStream.close();
    }

    public static House deserializeHouse(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
        return (House) inputStream.readObject();
    }
}
