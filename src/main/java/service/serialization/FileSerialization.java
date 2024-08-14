package service.serialization;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.String;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileSerialization<T> {

    public List<T> GetObjectsFromFile(String path){
        Gson gson = new Gson();

        List<T> list = new ArrayList<>();
        try (FileInputStream fin = new FileInputStream(path); ObjectInputStream oos = new ObjectInputStream(fin);){
            var content = Files.readString(Paths.get(path));
            list = gson.fromJson(content, TypeToken.get(list.getClass()).getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void WriteObjectsToFile(String path, List<T> list){
        Gson gson = new Gson();
        String json = gson.toJson(list);
        try {
            Files.write(Paths.get(path), json.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
