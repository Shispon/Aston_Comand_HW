import display.ConsoleMenu;
import entity.AnimalModel;
import entity.BarrelModel;
import entity.PersonModel;
import service.serialization.FileSerialization;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        AnimalModel cat = AnimalModel.builder()
                .species("Cat")
                .eyeColor("Green")
                .hasFur(true)
                .build();
        AnimalModel dog = AnimalModel.builder().species("Dog")
                .eyeColor("Brown")
                .hasFur(true)
                .build();
        AnimalModel axolotl = AnimalModel.builder().species("Axolotl")
                .eyeColor("Gray")
                .hasFur(false)
                .build();

        var animals = new AnimalModel[]{ cat, dog, axolotl };

//        BarrelModel waterBarrel = BarrelModel.builder()
//                .volume(50.0)
//                .storedMaterial("Water")
//                .material("Wood")
//                .build();
//
//        PersonModel person = PersonModel.builder()
//                .gender("Male")
//                .age(30)
//                .lastName("Smith")
//                .build();
//
//        System.out.println(cat);
//        System.out.println(waterBarrel);
//        System.out.println(person);
        FileSerialization<AnimalModel> ser = new FileSerialization<>();
        ser.WriteObjectsToFile("asdf.txt", Arrays.asList(animals));
        //new ConsoleMenu().start();

    }
}
