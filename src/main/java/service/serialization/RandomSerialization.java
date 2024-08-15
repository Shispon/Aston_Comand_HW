package service.serialization;

import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.lang.reflect.Type;

public class RandomSerialization<T> {

    private final Hashtable<String, String[]> propertyVariations;

    public RandomSerialization(Hashtable<String,String[]> propertyVariations) {

        this.propertyVariations = propertyVariations;
    }
    
    /**
     * @param count количество объектов для создания
     * @return список созданных объектов
     */
    public List<T> GetRandomObjects(int count, Class type)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        if(count <= 0)
            throw new IllegalArgumentException("count must be greater than 0");

        Random rand = new Random();
        List<T> list = new ArrayList<T>(count);
        var fields = type.getDeclaredFields();

        for(int i = 0; i < count; i++){
            T obj = (T)type.getConstructor().newInstance();
                for (var f : fields) {
                    f.setAccessible(true);
                    switch(f.getType().getName()){
                        case "java.lang.String":
                            var variations = this.propertyVariations.get(f.getName());
                            f.set(obj, variations[rand.nextInt(variations.length)]);
                            break;
                        case "int":
                        case "Integer":
                            f.set(this, rand.nextInt());
                            break;
                        case "float":
                        case "Float":
                            f.set(this, rand.nextFloat());
                            break;
                        case "double":
                        case "Double":
                            f.set(this, rand.nextDouble());
                            break;
                        case "long":
                        case "Long":
                            f.set(this, rand.nextLong());
                            break;
                        case "boolean":
                        case "Boolean":
                            f.set(obj, rand.nextBoolean());
                            break;
                    }
                }

            list.add(obj);
        }

        return list;
    }
}
