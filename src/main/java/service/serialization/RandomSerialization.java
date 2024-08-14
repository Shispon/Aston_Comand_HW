package service.serialization;

import lombok.SneakyThrows;

import java.lang.reflect.ParameterizedType;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomSerialization<T> {

    @SneakyThrows
    public List<T> GetRandomObjects(int count){
        Random rand = new Random();
        List<T> list = new ArrayList<T>(count);
        var type = ((ParameterizedType) this.getClass()
            .getGenericSuperclass()).getActualTypeArguments()[0].getClass();
        var fields = type.getDeclaredFields();

        for(int i = 0; i < count; i++){
            T obj = (T)type.getConstructor().newInstance();
            try {
                for (var f : fields) {
                    f.setAccessible(true);
                    switch(f.getType().getName()){
                        case "java.lang.String":
                            f.set(obj, java.util.UUID.randomUUID().toString());
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
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            list.add(obj);
        }

        return list;
    }
}
