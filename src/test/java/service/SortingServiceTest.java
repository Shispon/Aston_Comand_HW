package service;

import entity.AnimalModel;
import entity.BarrelModel;
import entity.PersonModel;
import org.junit.Assert;
import org.junit.Test;
import service.comparator.AnimalComparator;
import service.comparator.BarrelComparator;
import service.comparator.PersonComparator;

public class SortingServiceTest {

    AnimalModel[] animalModels = {
            new AnimalModel("Cat", "Ginger", true),
            new AnimalModel("Cat", "Brown", true),
            new AnimalModel("Cat", "Brown", false),
            new AnimalModel("Cat", "Brown", true),
            new AnimalModel("Dog", "Grey", true),
            new AnimalModel("Lion", "Yellow", true),

    };

    BarrelModel[] barrelModels = {
            new BarrelModel("Oil", "Steel", 3),
            new BarrelModel("Juice", "Steel", 1.2),
            new BarrelModel("Juice", "Plastic", 1.1),
            new BarrelModel("Juice", "Plastic", 1.2),
            new BarrelModel("Juice", "Plastic", 1.0),

    };


    PersonModel[] personModels = {
            new PersonModel("Ivanov", "Male", 30),
            new PersonModel("Ivanov", "Male", 5),
            new PersonModel("Gunko", "Male", 35),
            new PersonModel("Gunko", "Female", 21),
            new PersonModel("Ivanova", "Female", 21),
            new PersonModel("Ivanova", "Female", 22),
    };


    @Test
    public void insertionSortForAnimal() {
        AnimalModel[] animalModelsExpected = {
                new AnimalModel("Cat", "Brown", false),
                new AnimalModel("Cat", "Brown", true),
                new AnimalModel("Cat", "Brown", true),
                new AnimalModel("Cat", "Ginger", true),
                new AnimalModel("Dog", "Grey", true),
                new AnimalModel("Lion", "Yellow", true),

        };
        SortingService.insertionSort(animalModels, new AnimalComparator());
        Assert.assertArrayEquals(animalModelsExpected, animalModels);

    }

    @Test
    public void insertionSortForPerson() {
        PersonModel[] personModelsExpected = {
                new PersonModel("Gunko", "Female", 21),
                new PersonModel("Gunko", "Male", 35),
                new PersonModel("Ivanov", "Male", 5),
                new PersonModel("Ivanov", "Male", 30),
                new PersonModel("Ivanova", "Female", 21),
                new PersonModel("Ivanova", "Female", 22),
        };
        SortingService.insertionSort(personModels, new PersonComparator());
        Assert.assertArrayEquals(personModelsExpected, personModels);

    }

    @Test
    public void insertionSortForBarrel() {
        BarrelModel[] barrelModelsExpected = {
                new BarrelModel("Juice", "Plastic", 1.0),
                new BarrelModel("Juice", "Plastic", 1.1),
                new BarrelModel("Juice", "Plastic", 1.2),
                new BarrelModel("Juice", "Steel", 1.2),
                new BarrelModel("Oil", "Steel", 3),

        };
        SortingService.insertionSort(barrelModels, new BarrelComparator());
        Assert.assertArrayEquals(barrelModelsExpected, barrelModels);

    }

}