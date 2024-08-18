package repository;

import entity.AnimalModel;
import service.BinarySearchService;
import service.Comparators;
import service.SortingService;
import service.serialization.ConsoleSerialization;
import service.serialization.FileSerialization;
import service.serialization.RandomSerialization;
import service.serialization.ValidationException;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnimalRepository {
    private final List<AnimalModel> animalList = new ArrayList<>();
    private final ConsoleSerialization<AnimalModel> consoleSerialization;
    private final FileSerialization<AnimalModel> fileSerialization;
    private final RandomSerialization<AnimalModel> randomSerialization;

    // Конструктор для инициализации зависимостей
    public AnimalRepository(ConsoleSerialization<AnimalModel> consoleSerialization,
                            FileSerialization<AnimalModel> fileSerialization,
                            RandomSerialization<AnimalModel> randomSerialization) {
        this.consoleSerialization = consoleSerialization;
        this.fileSerialization = fileSerialization;
        this.randomSerialization = randomSerialization;
    }

    // Метод для сортировки списка AnimalModel
    public void sortedAnimalList() {
        SortingService.insertionSort(animalList, Comparators.animalComparator());
    }

    // Метод для поиска AnimalModel в списке
    public boolean searchAnimal(AnimalModel animal) {
        return BinarySearchService.search(animalList, animal, Comparators.animalComparator()) != null;
    }

    // Метод для добавления AnimalModel через консольный ввод
    public void addAnimalByConsole() throws ValidationException {
        Scanner scanner = new Scanner(System.in);
        AnimalModel animal = new AnimalModel();

        try {
            System.out.print("Enter species: ");
            String species = scanner.nextLine();
            consoleSerialization.SetObjectsProperty(animal, "species", species);

            System.out.print("Enter eye color: ");
            String eyeColor = scanner.nextLine();
            consoleSerialization.SetObjectsProperty(animal, "eyeColor", eyeColor);

            System.out.print("Has fur (true/false): ");
            String hasFur = scanner.nextLine();
            consoleSerialization.SetObjectsProperty(animal, "hasFur", hasFur);

            // Добавляем AnimalModel в список
            animalList.add(animal);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new ValidationException(e.getMessage());
        }
    }

    // Метод для получения AnimalModel из файла
    public void getAnimalsFromFile() {
        List<AnimalModel> animalsFromFile = fileSerialization.GetObjectsFromFile("animals.json", AnimalModel.class);
        animalList.clear();
        animalList.addAll(animalsFromFile);
    }

    // Метод для добавления AnimalModel в файл
    public void addAnimalsByFile() {
        fileSerialization.WriteObjectsToFile("animals.json", animalList);
    }

    // Метод для добавления случайных AnimalModel
    public void addAnimalsByRandom(int count) {
        try {
            List<AnimalModel> randomAnimals = randomSerialization.GetRandomObjects(count, AnimalModel.class);
            animalList.addAll(randomAnimals);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Error generating random animals: " + e.getMessage(), e);
        }
    }

    public void getAllAnimals () {
        System.out.println(animalList);
    }
}
