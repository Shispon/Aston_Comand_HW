package entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import service.serialization.ExternalizableObject;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonModel extends ExternalizableObject {
    private String lastName; // Фамилия
    private String gender;   // Пол
    private int age;         // Возраст
}
