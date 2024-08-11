package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnimalModel extends ExternalizableObject {
    private String species;  // Вид
    private String eyeColor; // Цвет глаз
    private boolean hasFur;  // Наличие шерсти
}
