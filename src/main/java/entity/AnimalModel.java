package entity;

public class AnimalModel {
    private final String species;  // Вид
    private final String eyeColor; // Цвет глаз
    private final boolean hasFur;  // Наличие шерсти

    private AnimalModel(Builder builder) {
        this.species = builder.species;
        this.eyeColor = builder.eyeColor;
        this.hasFur = builder.hasFur;
    }

    public String getSpecies() {
        return species;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public boolean hasFur() {
        return hasFur;
    }

    @Override
    public String toString() {
        return "Animal [Species=" + species + ", Eye Color=" + eyeColor + ", Has Fur=" + hasFur + "]";
    }

    public static class Builder {
        private String species;
        private String eyeColor;
        private boolean hasFur;

        public Builder setSpecies(String species) {
            this.species = species;
            return this;
        }

        public Builder setEyeColor(String eyeColor) {
            this.eyeColor = eyeColor;
            return this;
        }

        public Builder setHasFur(boolean hasFur) {
            this.hasFur = hasFur;
            return this;
        }

        public AnimalModel build() {
            return new AnimalModel(this);
        }
    }
}
