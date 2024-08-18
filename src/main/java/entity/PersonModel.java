package entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonModel {
    private String lastName; // Фамилия
    private String gender;   // Пол
    private int age;         // Возраст
}   
