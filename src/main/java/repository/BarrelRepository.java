package repository;

import entity.BarrelModel;
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
 * Репозиторий для работы с коллекцией баррелей.
 * Предоставляет функциональность для сортировки, поиска, добавления и загрузки баррелей.
 */
public class BarrelRepository {

    private final List<BarrelModel> barrelList = new ArrayList<>();
    private final ConsoleSerialization<BarrelModel> consoleSerialization;
    private final FileSerialization<BarrelModel> fileSerialization;
    private final RandomSerialization<BarrelModel> randomSerialization;

    /**
     * Конструктор класса {@code BarrelRepository}.
     *
     * @param consoleSerialization объект для сериализации в консоль
     * @param fileSerialization объект для сериализации в файл
     * @param randomSerialization объект для случайной сериализации
     */
    public BarrelRepository(ConsoleSerialization<BarrelModel> consoleSerialization,
                            FileSerialization<BarrelModel> fileSerialization,
                            RandomSerialization<BarrelModel> randomSerialization) {
        this.consoleSerialization = consoleSerialization;
        this.fileSerialization = fileSerialization;
        this.randomSerialization = randomSerialization;
    }

    /**
     * Сортирует список баррелей с использованием стандартной сортировки.
     */
    public void sortedBarrelList() {
        SortingService.insertionSort(barrelList, Comparators.barrelComparator());
    }

    /**
     * Специально сортирует список баррелей, где сортируются только те баррели,
     * объем которых является четным, а затем возвращаются в их исходные позиции.
     */
    public void specialSortedBarrelList() {
        SortingService.specialSort(barrelList, Comparators.barrelComparator(), barrel -> (int) barrel.getVolume());
    }

    /**
     * Ищет баррель в списке по указанным критериям.
     *
     * @param barrel баррель для поиска
     * @return {@code true}, если баррель найден, иначе {@code false}
     */
    public BarrelModel searchBarrel(BarrelModel barrel) {
        return BinarySearchService.search(barrelList, barrel, Comparators.barrelComparator());
    }

    /**
     * Добавляет баррель в список на основе ввода с консоли.
     *
     * @throws ValidationException если возникла ошибка при валидации данных
     */
    public void addBarrelByConsole() throws ValidationException {
        Scanner scanner = new Scanner(System.in);
        BarrelModel barrel = new BarrelModel();

        try {
            System.out.print("Enter stored material: ");
            String storedMaterial = scanner.nextLine();
            consoleSerialization.SetObjectsProperty(barrel, "storedMaterial", storedMaterial);

            System.out.print("Enter material: ");
            String material = scanner.nextLine();
            consoleSerialization.SetObjectsProperty(barrel, "material", material);

            System.out.print("Enter volume: ");
            String volume = scanner.nextLine();
            consoleSerialization.SetObjectsProperty(barrel, "volume", volume);

            barrelList.add(barrel);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new ValidationException(e.getMessage());
        }
    }

    /**
     * Загружает баррели из файла и обновляет список баррелей.
     */
    public void getBarrelsFromFile() {
        List<BarrelModel> barrelsFromFile = fileSerialization.GetObjectsFromFile("barrels.json", BarrelModel.class);
        barrelList.clear();
        barrelList.addAll(barrelsFromFile);
    }

    /**
     * Записывает список баррелей в файл.
     */
    public void addBarrelsByFile() {
        fileSerialization.WriteObjectsToFile("barrels.json", barrelList);
    }

    /**
     * Добавляет случайно сгенерированные баррели в список.
     *
     * @param count количество баррелей для генерации
     * @throws RuntimeException если возникает ошибка при генерации случайных баррелей
     */
    public void addBarrelsByRandom(int count) {
        try {
            List<BarrelModel> randomBarrels = randomSerialization.GetRandomObjects(count, BarrelModel.class);
            barrelList.addAll(randomBarrels);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Error generating random barrels: " + e.getMessage(), e);
        }
    }

    /**
     * Выводит все баррели из списка на консоль.
     */
    public void getAllBarrels() {
        System.out.println(barrelList);
    }
}
