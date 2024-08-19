import entity.AnimalModel;
import entity.BarrelModel;
import entity.PersonModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.Comparators;
import service.sorting.InsertionSort;
import service.sorting.SortingService;
import service.sorting.SpecialSort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortingServiceTest {
    private List<BarrelModel> barrelList;
    private Comparator<BarrelModel> comparator;

    @Before
    public void setUp() {
        comparator = Comparators.barrelComparator();

        barrelList = new ArrayList<>();
        barrelList.add(new BarrelModel("Water", "Wood", 50.0)); // четный
        barrelList.add(new BarrelModel("Oil", "Steel", 10.0)); // четный
        barrelList.add(new BarrelModel("Juice", "Plastic", 25.0)); // нечетный
        barrelList.add(new BarrelModel("Juice", "Plastic", 35.0)); // нечетный
        barrelList.add(new BarrelModel("Juice", "Plastic", 20.0)); // четный
    }

    private final AnimalModel[] animalModels = {
            new AnimalModel("Cat", "Ginger", true),
            new AnimalModel("Cat", "Brown", true),
            new AnimalModel("Cat", "Brown", false),
            new AnimalModel("Cat", "Brown", true),
            new AnimalModel("Dog", "Grey", true),
            new AnimalModel("Lion", "Yellow", true),
    };

    private final BarrelModel[] barrelModels = {
            new BarrelModel("Oil", "Steel", 3.0),
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
        SortingService<AnimalModel> sortingService = new SortingService<>(new InsertionSort<>());

        // Ожидаемый результат после сортировки: сначала по species, затем по eyeColor, затем по hasFur
        AnimalModel[] animalModelsExpected = {
                new AnimalModel("Cat", "Brown", false),
                new AnimalModel("Cat", "Brown", true),
                new AnimalModel("Cat", "Brown", true),
                new AnimalModel("Cat", "Ginger", true),
                new AnimalModel("Dog", "Grey", true),
                new AnimalModel("Lion", "Yellow", true),
        };

        sortingService.sort(animalModelList, Comparators.animalComparator());

        Assert.assertArrayEquals(animalModelsExpected, animalModelList.toArray(new AnimalModel[0]));
    }

    @Test
    public void insertionSortForPerson() {
        List<PersonModel> personModelList = new ArrayList<>(List.of(personModels));
        SortingService<PersonModel> sortingService = new SortingService<>(new InsertionSort<>());

        // Ожидаемый результат после сортировки: сначала по lastName, затем по gender, затем по age
        PersonModel[] personModelsExpected = {
                new PersonModel("Gunko", "Female", 21),
                new PersonModel("Gunko", "Male", 35),
                new PersonModel("Ivanov", "Male", 5),
                new PersonModel("Ivanov", "Male", 30),
                new PersonModel("Ivanova", "Female", 21),
                new PersonModel("Ivanova", "Female", 22),
        };

        sortingService.sort(personModelList, Comparators.personComparator());

        Assert.assertArrayEquals(personModelsExpected, personModelList.toArray(new PersonModel[0]));
    }

    @Test
    public void insertionSortForBarrel() {
        List<BarrelModel> barrelModelList = new ArrayList<>(List.of(barrelModels));
        SortingService<BarrelModel> sortingService = new SortingService<>(new InsertionSort<>());

        // Ожидаемый результат после сортировки: сначала по объему в порядке возрастания
        BarrelModel[] barrelModelsExpected = {
                new BarrelModel("Juice", "Plastic", 1.0),
                new BarrelModel("Juice", "Plastic", 1.1),
                new BarrelModel("Juice", "Plastic", 1.2),
                new BarrelModel("Juice", "Steel", 1.2),
                new BarrelModel("Oil", "Steel", 3.0),
        };

        sortingService.sort(barrelModelList, Comparators.barrelComparator());

        Assert.assertArrayEquals(barrelModelsExpected, barrelModelList.toArray(new BarrelModel[0]));
    }

    @Test
    public void testSpecialSort() {
        SortingService<BarrelModel> sortingService = new SortingService<>(new SpecialSort<>(BarrelModel::getVolume));

        // Преобразование значения double в int для проверки четности
        sortingService.sort(barrelList, Comparators.barrelComparator());

        List<BarrelModel> expected = new ArrayList<>();
        expected.add(new BarrelModel("Juice", "Plastic", 20.0)); // четный
        expected.add(new BarrelModel("Oil", "Steel", 10.0)); // четный
        expected.add(new BarrelModel("Juice", "Plastic", 25.0)); // нечетный
        expected.add(new BarrelModel("Juice", "Plastic", 35.0)); // нечетный
        expected.add(new BarrelModel("Water", "Wood", 50.0)); // четный

        Assert.assertEquals(expected, barrelList);
    }
}
