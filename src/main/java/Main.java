import entity.AnimalModel;
import entity.BarrelModel;
import entity.PersonModel;

public class Main {
    public static void main(String[] args) {
        AnimalModel cat = new AnimalModel.Builder()
                .setSpecies("Cat")
                .setEyeColor("Green")
                .setHasFur(true)
                .build();

        BarrelModel waterBarrel = new BarrelModel.Builder()
                .setVolume(50.0)
                .setStoredMaterial("Water")
                .setMaterial("Wood")
                .build();

        PersonModel person = new PersonModel.Builder()
                .setGender("Male")
                .setAge(30)
                .setLastName("Smith")
                .build();

        System.out.println(cat);
        System.out.println(waterBarrel);
        System.out.println(person);
    }
}
