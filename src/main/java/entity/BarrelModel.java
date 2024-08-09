package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BarrelModel {
    private double volume;           // Объем
    private String storedMaterial;   // Хранимый материал
    private String material;         // Материал изготовления

}
