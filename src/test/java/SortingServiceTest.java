import entity.AnimalModel;
import entity.BarrelModel;
import entity.PersonModel;
import org.junit.Assert;
import org.junit.Test;
import service.Comparators;
import service.SortingService;

import java.util.ArrayList;
import java.util.List;

public class SortingServiceTest {

    private final AnimalModel[] animalModels = {
            new AnimalModel("Cat", "Ginger", true),
            new AnimalModel("Cat", "Brown", true),
            new AnimalModel("Cat", "Brown", false),
            new AnimalModel("Cat", "Brown", true),
            new AnimalModel("Dog", "Grey", true),
            new AnimalModel("Lion", "Yellow", true),
    };

    private final BarrelModel[] barrelModels = {
            new BarrelModel("Oil", "Steel", 3),
            new BarrelModel("Juice", "Steel", 1.2),
            new BarrelModel("Juice", "Plastic", 1.1),
            new BarrelModel("Juice", "Plastic", 1.2),
            new BarrelModel("Juice", "Plastic", 1.0),
    };

    private final PersonModel[] personModels = {
            new PersonModel("Ivanov", "Male", 30),
            new PersonModel("Ivanov", "Male", 5),
            new PersonModel("Gunko", "Male", 35),
            new PersonModel("Gunko", "Female", 21),
            new PersonModel("Ivanova", "Female", 21),
            new PersonModel("Ivanova", "Female", 22),
    };

    @Test
    public void insertionSortForAnimal() {
        List<AnimalModel> animalModelList = new ArrayList<>(List.of(animalModels));

        // Ожидаемый результат после сортировки
        AnimalModel[] animalModelsExpected = {
                new AnimalModel("Cat", "Brown", false),
                new AnimalModel("Cat", "Brown", true),
                new AnimalModel("Cat", "Brown", true),
                new AnimalModel("Cat", "Ginger", true),
                new AnimalModel("Dog", "Grey", true),
                new AnimalModel("Lion", "Yellow", true),
        };

        SortingService.insertionSort(animalModelList, Comparators.animalComparator());

        Assert.assertArrayEquals(animalModelsExpected, animalModelList.toArray(new AnimalModel[0]));
    }

    @Test
    public void insertionSortForPerson() {
        List<PersonModel> personModelList = new ArrayList<>(List.of(personModels));

        // Ожидаемый результат после сортировки
        PersonModel[] personModelsExpected = {
                new PersonModel("Gunko", "Female", 21),
                new PersonModel("Gunko", "Male", 35),
                new PersonModel("Ivanov", "Male", 5),
                new PersonModel("Ivanov", "Male", 30),
                new PersonModel("Ivanova", "Female", 21),
                new PersonModel("Ivanova", "Female", 22),
        };

        SortingService.insertionSort(personModelList, Comparators.personComparator());

        Assert.assertArrayEquals(personModelsExpected, personModelList.toArray(new PersonModel[0]));
    }

    @Test
    public void insertionSortForBarrel() {
        List<BarrelModel> barrelModelList = new ArrayList<>(List.of(barrelModels));

        // Ожидаемый результат после сортировки
        BarrelModel[] barrelModelsExpected = {
                new BarrelModel("Juice", "Plastic", 1.0),
                new BarrelModel("Juice", "Plastic", 1.1),
                new BarrelModel("Juice", "Plastic", 1.2),
                new BarrelModel("Juice", "Steel", 1.2),
                new BarrelModel("Oil", "Steel", 3),
        };

        SortingService.insertionSort(barrelModelList, Comparators.barrelComparator());

        Assert.assertArrayEquals(barrelModelsExpected, barrelModelList.toArray(new BarrelModel[0]));
    }
}
