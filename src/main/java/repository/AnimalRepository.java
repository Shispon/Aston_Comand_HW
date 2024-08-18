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

/**
 * Репозиторий для работы с коллекцией животных.
 * Предоставляет функциональность для сортировки, поиска, добавления и загрузки объектов {@link AnimalModel}.
 */
public class AnimalRepository {

    private final List<AnimalModel> animalList = new ArrayList<>();
    private final ConsoleSerialization<AnimalModel> consoleSerialization;
    private final FileSerialization<AnimalModel> fileSerialization;
    private final RandomSerialization<AnimalModel> randomSerialization;

    /**
     * Конструктор класса {@code AnimalRepository}.
     *
     * @param consoleSerialization объект для сериализации в консоль
     * @param fileSerialization объект для сериализации в файл
     * @param randomSerialization объект для случайной сериализации
     */
    public AnimalRepository(ConsoleSerialization<AnimalModel> consoleSerialization,
                            FileSerialization<AnimalModel> fileSerialization,
                            RandomSerialization<AnimalModel> randomSerialization) {
        this.consoleSerialization = consoleSerialization;
        this.fileSerialization = fileSerialization;
        this.randomSerialization = randomSerialization;
    }

    /**
     * Сортирует список {@link AnimalModel} с использованием стандартной сортировки.
     */
    public void sortedAnimalList() {
        SortingService.insertionSort(animalList, Comparators.animalComparator());
    }

    /**
     * Ищет объект {@link AnimalModel} в списке по указанным критериям.
     *
     * @param animal объект {@link AnimalModel} для поиска
     * @return {@code true}, если объект найден, иначе {@code false}
     */
    public AnimalModel searchAnimal(AnimalModel animal) {
        return BinarySearchService.search(animalList, animal, Comparators.animalComparator());
    }

    /**
     * Добавляет объект {@link AnimalModel} в список на основе ввода с консоли.
     *
     * @throws ValidationException если возникла ошибка при валидации данных
     */
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

    /**
     * Загружает объекты {@link AnimalModel} из файла и обновляет список животных.
     */
    public void getAnimalsFromFile() {
        List<AnimalModel> animalsFromFile = fileSerialization.GetObjectsFromFile("animals.json", AnimalModel.class);
        animalList.clear();
        animalList.addAll(animalsFromFile);
    }

    /**
     * Записывает список объектов {@link AnimalModel} в файл.
     */
    public void addAnimalsByFile() {
        fileSerialization.WriteObjectsToFile("animals.json", animalList);
    }

    /**
     * Добавляет случайно сгенерированные объекты {@link AnimalModel} в список.
     *
     * @param count количество объектов для генерации
     * @throws RuntimeException если возникает ошибка при генерации случайных объектов
     */
    public void addAnimalsByRandom(int count) {
        try {
            List<AnimalModel> randomAnimals = randomSerialization.GetRandomObjects(count, AnimalModel.class);
            animalList.addAll(randomAnimals);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Error generating random animals: " + e.getMessage(), e);
        }
    }

    /**
     * Выводит все объекты {@link AnimalModel} из списка на консоль.
     */
    public void getAllAnimals() {
        System.out.println(animalList);
    }
}
