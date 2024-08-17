package service.serialization;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.String;
import java.lang.reflect.ParameterizedType;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class FileSerialization<T> {

    /**
     * получить объекты из файла
     * @param path путь к файлу
     * @return возвращает список десериализованных объектов
     */
    public List<T> GetObjectsFromFile(String path){
        Gson gson = new Gson();

        List<T> list = new ArrayList<>();
        try {
            var content = Files.readString(Paths.get(path));
            list = gson.fromJson(content, TypeToken.get(list.getClass()).getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    /**
     * сериализовать и записать объекты в файл
     * @param path путь к файлу
     * @param list список объектов для записи в файл
     */
    public void WriteObjectsToFile(String path, List<T> list){
        Gson gson = new Gson();
        String json = gson.toJson(list);
        try {
            if(!Files.exists(Paths.get(path)))
                Files.createFile(Paths.get(path));
            Files.write(Paths.get(path), json.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
