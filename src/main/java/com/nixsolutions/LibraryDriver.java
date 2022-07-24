package com.nixsolutions;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LibraryDriver {

    public static void serializeObject(String fileName, Object obj) {
        Path outputObjectFilePath = Paths.get(fileName);
        try (ObjectOutputStream os = new ObjectOutputStream(Files.newOutputStream(outputObjectFilePath))) {
            os.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object deSerializeObject(String fileName) {
        Path inputObjectFilePath = Paths.get(fileName);
        Object obj = null;
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(inputObjectFilePath))) {
            obj = in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}
