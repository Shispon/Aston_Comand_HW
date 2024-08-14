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
public class BarrelModel extends ExternalizableObject {
    private String storedMaterial;   // Хранимый материал
    private String material;         // Материал изготовления
    private double volume;           // Объем

}
