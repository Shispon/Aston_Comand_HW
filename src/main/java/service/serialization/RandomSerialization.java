package service.serialization;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomSerialization<T> {

    /**
     * @param count количество объектов для создания
     * @return список созданных объектов
     */

    public List<T> GetRandomObjects(int count, Class<T> type)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        if (count <= 0) {
            throw new IllegalArgumentException("count must be greater than 0");
        }

        Random rand = new Random();
        List<T> list = new ArrayList<>(count);
        var fields = type.getDeclaredFields();

        for (int i = 0; i < count; i++) {
            T obj = type.getConstructor().newInstance();
            for (var f : fields) {
                f.setAccessible(true);
                switch (f.getType().getName()) {
                    case "java.lang.String":
                        f.set(obj, generateRandomString(rand));
                        break;
                    case "int":
                    case "Integer":
                        f.set(obj, rand.nextInt(100)); // Adjust range if needed
                        break;
                    case "float":
                    case "Float":
                        f.set(obj, rand.nextFloat());
                        break;
                    case "double":
                    case "Double":
                        f.set(obj, rand.nextDouble());
                        break;
                    case "long":
                    case "Long":
                        f.set(obj, rand.nextLong());
                        break;
                    case "boolean":
                    case "Boolean":
                        f.set(obj, rand.nextBoolean());
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported field type: " + f.getType().getName());
                }
            }
            list.add(obj);
        }

        return list;
    }

    private String generateRandomString(Random rand) {
        int length = 10; // Adjust length as needed
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char ch = (char) (rand.nextInt(26) + 'a'); // Lowercase letters
            sb.append(ch);
        }
        return sb.toString();
    }
}
