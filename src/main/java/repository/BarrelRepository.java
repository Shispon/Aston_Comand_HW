package repository;

import entity.BarrelModel;
import service.*;
import service.serialization.ConsoleSerialization;
import service.serialization.FileSerialization;
import service.serialization.RandomSerialization;
import service.serialization.ValidationException;
import service.sorting.InsertionSort;
import service.sorting.NumericExtractor;
import service.sorting.SortingLogic;
import service.sorting.SpecialSort;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Репозиторий для работы с коллекцией баррелей.
 * Предоставляет функциональность для сортировки, поиска, добавления и загрузки баррелей.
 */
public class BarrelRepository implements Repository<BarrelModel> {

    private final List<BarrelModel> barrelList = new ArrayList<>();
    private final ConsoleSerialization<BarrelModel> consoleSerialization;
    private final FileSerialization<BarrelModel> fileSerialization;
    private final RandomSerialization<BarrelModel> randomSerialization;
    private SortingLogic<BarrelModel> sortingLogic;  // Поле для хранения текущей сортировки
    private final BarrelComparator barrelComparator;

    /**
     * Конструктор класса {@code BarrelRepository}.
     *
     * @param consoleSerialization объект для сериализации в консоль
     * @param fileSerialization объект для сериализации в файл
     * @param randomSerialization объект для случайной сериализации
     */
    public BarrelRepository(ConsoleSerialization<BarrelModel> consoleSerialization,
                            FileSerialization<BarrelModel> fileSerialization,
                            RandomSerialization<BarrelModel> randomSerialization,
                            BarrelComparator barrelComparator) {
        this.consoleSerialization = consoleSerialization;
        this.fileSerialization = fileSerialization;
        this.randomSerialization = randomSerialization;
        this.barrelComparator = barrelComparator;
    }

    /**
     * Устанавливает алгоритм сортировки.
     *
     * @param sortingLogic новый алгоритм сортировки
     */
    public void setSortingLogic(SortingLogic<BarrelModel> sortingLogic) {
        this.sortingLogic = sortingLogic;
    }


    /**
     * Сортирует список баррелей с использованием стандартной сортировки.
     */
    public void sortedBarrelList() {
        setSortingLogic(new InsertionSort<>());
        sortingLogic.sort(barrelList, Comparators.barrelComparator());
    }
    /**
     * Специально сортирует список баррелей, где сортируются только те баррели,
     * объем которых является четным, а затем возвращаются в их исходные позиции.
     */
    public void specialSortedBarrelList() {
        NumericExtractor<BarrelModel> extractor = barrel -> (int) barrel.getVolume();
        setSortingLogic(new SpecialSort<>(extractor));
        sortingLogic.sort(barrelList, Comparators.barrelComparator());
    }

    /**
     * Ищет баррель в списке по указанным критериям.
     *
     * @param barrel баррель для поиска
     * @return {@code BarrelModel}, если баррель найден, иначе {@code null}
     */
    @Override
    public BarrelModel search(BarrelModel barrel) {
        return BinarySearchService.search(barrelList, barrel, barrelComparator.getComparator());
    }

    /**
     * Добавляет баррель в список на основе ввода с консоли.
     *
     * @throws ValidationException если возникла ошибка при валидации данных
     */
    @Override
    public void addByConsole() throws ValidationException {
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
    @Override
    public void getFromFile() {
        List<BarrelModel> barrelsFromFile = fileSerialization.GetObjectsFromFile("barrels.json", BarrelModel.class);
        barrelList.clear();
        barrelList.addAll(barrelsFromFile);
    }

    /**
     * Записывает список баррелей в файл.
     */
    @Override
    public void addByFile() {
        fileSerialization.WriteObjectsToFile("barrels.json", barrelList);
    }

    /**
     * Добавляет случайно сгенерированные баррели в список.
     *
     * @param count количество баррелей для генерации
     * @throws RuntimeException если возникает ошибка при генерации случайных баррелей
     */
    @Override
    public void addByRandom(int count) {
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
    @Override
    public void getAll() {
        System.out.println(barrelList);
    }
}
