package service.serialization;

import java.lang.reflect.Field;
import java.util.Arrays;

public class ConsoleSerialization<T> {

    private final Class type;

    public ConsoleSerialization(Class type){
        this.type = type;
    }
    /**
     * @return возвращает имена всех свойств класса
     */
    public String[] GetTypePropertyNames() {
        var fields = this.type.getDeclaredFields();
        return Arrays.stream(fields).map(Field::getName).toArray(String[]::new);
    }

    /**
     * @param object конструируемый объект
     * @param propertyName звойство для заполнения
     * @param userInput пользовательский ввод для присваивания свойству
     */
    public void SetObjectsProperty(T object, String propertyName, String userInput) throws ValidationException, IllegalArgumentException {
        if (object == null)
            throw new IllegalArgumentException("object cannot be null");
        if (propertyName == null)
            throw new IllegalArgumentException("propertyName cannot be null");
        if (userInput == null)
            throw new IllegalArgumentException("userInput cannot be null");

        Field field = Arrays.stream(this.type.getDeclaredFields()).filter(x -> propertyName.equals(x.getName())).findFirst().get();

        // validate & set
        try {
            field.setAccessible(true);
            switch (field.getType().getName()) {
                case "java.lang.String":
                    field.set(object, userInput.trim());
                    break;
                case "int":
                case "Integer":
                    field.set(object, Integer.parseInt(userInput.trim()));
                    break;
                case "float":
                case "Float":
                    field.set(object, Float.parseFloat(userInput.trim()));
                    break;
                case "double":
                case "Double":
                    field.set(object, Double.parseDouble(userInput.trim()));
                    break;
                case "long":
                case "Long":
                    field.set(object, Long.parseLong(userInput.trim()));
                    break;
                case "boolean":
                case "Boolean":
                    field.set(object, Boolean.parseBoolean(userInput.trim()));
                    break;
            }
        } catch (NumberFormatException e) {
            throw new ValidationException(String.format("Невозможно установить значение {0} типа {1}", field.getName(), field.getType().getName()));
        } catch (IllegalAccessException e) {
            throw new ValidationException(e.getMessage());
        }
    }
}
