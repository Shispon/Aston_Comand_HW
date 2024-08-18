package service.serialization;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

public class ConsoleSerialization<T> {

    private final Class<T> type;

    public ConsoleSerialization(Class<T> type) {
        this.type = type;
    }

    public String[] GetTypePropertyNames() {
        Field[] fields = this.type.getDeclaredFields();
        return Arrays.stream(fields).map(Field::getName).toArray(String[]::new);
    }
    /**
     * @param object конструируемый объект
     * @param propertyName звойство для заполнения
     * @param userInput пользовательский ввод для присваивания свойству
     */

    public void SetObjectsProperty(T object, String propertyName, String userInput) throws ValidationException {
        if (object == null) {
            throw new IllegalArgumentException("object cannot be null");
        }
        if (propertyName == null) {
            throw new IllegalArgumentException("propertyName cannot be null");
        }
        if (userInput == null) {
            throw new IllegalArgumentException("userInput cannot be null");
        }

        Optional<Field> optionalField = Arrays.stream(this.type.getDeclaredFields())
                .filter(f -> propertyName.equals(f.getName()))
                .findFirst();

        if (optionalField.isEmpty()) {
            throw new ValidationException("Property not found: " + propertyName);
        }

        Field field = optionalField.get();
        field.setAccessible(true);

        try {
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
                default:
                    throw new ValidationException("Unsupported field type: " + field.getType().getName());
            }
        } catch (NumberFormatException | IllegalAccessException e) {
            throw new ValidationException("Error setting property: " + e.getMessage(), e);
        }
    }
}
