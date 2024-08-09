package entity;

public class BarrelModel {
    private final double volume;           // Объем
    private final String storedMaterial;   // Хранимый материал
    private final String material;         // Материал изготовления

    private BarrelModel(Builder builder) {
        this.volume = builder.volume;
        this.storedMaterial = builder.storedMaterial;
        this.material = builder.material;
    }

    public double getVolume() {
        return volume;
    }

    public String getStoredMaterial() {
        return storedMaterial;
    }

    public String getMaterial() {
        return material;
    }

    @Override
    public String toString() {
        return "Barrel [Volume=" + volume + " liters, Stored Material=" + storedMaterial + ", Material=" + material + "]";
    }

    public static class Builder {
        private double volume;
        private String storedMaterial;
        private String material;

        public Builder setVolume(double volume) {
            this.volume = volume;
            return this;
        }

        public Builder setStoredMaterial(String storedMaterial) {
            this.storedMaterial = storedMaterial;
            return this;
        }

        public Builder setMaterial(String material) {
            this.material = material;
            return this;
        }

        public BarrelModel build() {
            return new BarrelModel(this);
        }
    }
}
