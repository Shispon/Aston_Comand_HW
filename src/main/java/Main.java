import display.ConsoleMenu;
import entity.AnimalModel;
import entity.BarrelModel;
import entity.PersonModel;
import service.serialization.FileSerialization;
import service.serialization.RandomSerialization;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Hashtable;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
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

        var animals = new AnimalModel[]{cat, dog, axolotl};

        BarrelModel waterBarrel = BarrelModel.builder()
                .volume(50.0)
                .storedMaterial("Water")
                .material("Wood")
                .build();

        PersonModel person = PersonModel.builder()
                .gender("Male")
                .age(30)
                .lastName("Smith")
                .build();


        FileSerialization<AnimalModel> ser1 = new FileSerialization<>();
        ser1.WriteObjectsToFile(".\\animal.json", Arrays.asList(animals));
        var fileAnimals = ser1.GetObjectsFromFile("animal.json");

        FileSerialization<BarrelModel> ser2 = new FileSerialization<>();
        ser2.WriteObjectsToFile(".\\waterBarrel.json", Arrays.asList(new BarrelModel[]{waterBarrel}));
        var fileWaterBarrels = ser2.GetObjectsFromFile("waterBarrel.json");

        FileSerialization<PersonModel> ser3 = new FileSerialization<>();
        ser3.WriteObjectsToFile(".\\person.json", Arrays.asList(new PersonModel[]{person}));
        var filePersons = ser3.GetObjectsFromFile("person.json");

        var map = new Hashtable<String, String[]>();
        map.put("species", new String[]{"Rat", "Cat", "Bird"});
        map.put("eyeColor", new String[]{"Blue", "Gray", "Black"});
        RandomSerialization<AnimalModel> rand = new RandomSerialization<>(map);

        var rndAnimals = rand.GetRandomObjects(2, cat.getClass());



        //new ConsoleMenu().start();

    }
}
