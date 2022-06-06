package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Serializer {
    public static void save(Serializable serializable, String path) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(path)));
        oos.writeObject(serializable);
    }

    public static Serializable load(String path) throws IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(path)));
        return (Serializable)ois.readObject();
    }
}