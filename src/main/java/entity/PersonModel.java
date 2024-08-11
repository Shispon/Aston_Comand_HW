package entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonModel extends ExternalizableObject {
    private String gender;   // Пол
    private int age;         // Возраст
    private String lastName; // Фамилия
}
