package repository;

import entity.AnimalModel;
import service.*;
import service.comparators.AnimalComparator;
import service.serialization.ConsoleSerialization;
import service.serialization.FileSerialization;
import service.serialization.RandomSerialization;
import service.serialization.ValidationException;
import service.sorting.InsertionSort;
import service.sorting.SortingLogic;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnimalRepository implements Repository<AnimalModel> {

    private final List<AnimalModel> animalList = new ArrayList<>();
    private final ConsoleSerialization<AnimalModel> consoleSerialization;
    private final FileSerialization<AnimalModel> fileSerialization;
    private final RandomSerialization<AnimalModel> randomSerialization;
    private SortingLogic<AnimalModel> sortingLogic;  // Поле для хранения текущей сортировки
    private final AnimalComparator animalComparator;

    public AnimalRepository(ConsoleSerialization<AnimalModel> consoleSerialization,
                            FileSerialization<AnimalModel> fileSerialization,
                            RandomSerialization<AnimalModel> randomSerialization,
                            AnimalComparator animalComparator) {
        this.consoleSerialization = consoleSerialization;
        this.fileSerialization = fileSerialization;
        this.randomSerialization = randomSerialization;
        this.animalComparator = animalComparator;
    }

    /**
     * Устанавливает алгоритм сортировки.
     *
     * @param sortingLogic новый алгоритм сортировки
     */
    public void setSortingLogic(SortingLogic<AnimalModel> sortingLogic) {
        this.sortingLogic = sortingLogic;
    }

    /**
     * Сортирует список {@link AnimalModel} с использованием стандартной сортировки.
     */
    @Override
    public void sortedList() {
        setSortingLogic(new InsertionSort<>());
        sortingLogic.sort(animalList, animalComparator.getComparator());
    }

    @Override
    public AnimalModel search(AnimalModel animal) {
        return BinarySearchService.search(animalList, animal, animalComparator.getComparator());
    }

    @Override
    public void addByConsole() throws ValidationException {
        Scanner scanner = new Scanner(System.in);
        AnimalModel animal = new AnimalModel();

        try {
            System.out.print("Введите вид: ");
            String species = scanner.nextLine();
            consoleSerialization.SetObjectsProperty(animal, "species", species);

            System.out.print("Введите цвет глаз: ");
            String eyeColor = scanner.nextLine();
            consoleSerialization.SetObjectsProperty(animal, "eyeColor", eyeColor);

            System.out.print("Есть ли шерсть (true/false): ");
            String hasFur = scanner.nextLine();
            consoleSerialization.SetObjectsProperty(animal, "hasFur", hasFur);

            animalList.add(animal);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new ValidationException(e.getMessage());
        }
    }

    @Override
    public void getFromFile() {
        List<AnimalModel> animalsFromFile = fileSerialization.GetObjectsFromFile("animals.json", AnimalModel.class);
        animalList.clear();
        animalList.addAll(animalsFromFile);
    }

    @Override
    public void addByFile() {
        fileSerialization.WriteObjectsToFile("animals.json", animalList);
    }

    @Override
    public void addByRandom(int count) {
        try {
            List<AnimalModel> randomAnimals = randomSerialization.GetRandomObjects(count, AnimalModel.class);
            animalList.addAll(randomAnimals);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Error generating random animals: " + e.getMessage(), e);
        }
    }

    @Override
    public void getAll() {
        System.out.println(animalList);
    }
}
