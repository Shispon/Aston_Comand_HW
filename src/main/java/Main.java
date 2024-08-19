import display.ConsoleMenu;
import entity.AnimalModel;
import entity.BarrelModel;
import entity.PersonModel;

public class Main {
    public static void main(String[] args) {
//        AnimalModel cat = AnimalModel.builder()
//                .species("Cat")
//                .eyeColor("Green")
//                .hasFur(true)
//                .build();
//
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
        ConsoleMenu startMenu = new ConsoleMenu();
        startMenu.start();

    }
}
