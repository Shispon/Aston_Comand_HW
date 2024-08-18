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

public class BarrelRepository {
    private final List<BarrelModel> barrelList = new ArrayList<>();
    private final ConsoleSerialization<BarrelModel> consoleSerialization;
    private final FileSerialization<BarrelModel> fileSerialization;
    private final RandomSerialization<BarrelModel> randomSerialization;

    public BarrelRepository(ConsoleSerialization<BarrelModel> consoleSerialization,
                            FileSerialization<BarrelModel> fileSerialization,
                            RandomSerialization<BarrelModel> randomSerialization) {
        this.consoleSerialization = consoleSerialization;
        this.fileSerialization = fileSerialization;
        this.randomSerialization = randomSerialization;
    }

    public void sortedBarrelList() {
        SortingService.insertionSort(barrelList, Comparators.barrelComparator());
    }

    public boolean searchBarrel(BarrelModel barrel) {
        return BinarySearchService.search(barrelList, barrel, Comparators.barrelComparator()) != null;
    }

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

    public void getBarrelsFromFile() {
        List<BarrelModel> barrelsFromFile = fileSerialization.GetObjectsFromFile("barrels.json", BarrelModel.class);
        barrelList.clear();
        barrelList.addAll(barrelsFromFile);
    }

    public void addBarrelsByFile() {
        fileSerialization.WriteObjectsToFile("barrels.json", barrelList);
    }

    public void addBarrelsByRandom(int count) {
        try {
            List<BarrelModel> randomBarrels = randomSerialization.GetRandomObjects(count, BarrelModel.class);
            barrelList.addAll(randomBarrels);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Error generating random barrels: " + e.getMessage(), e);
        }
    }

    public void getAllBarrels () {
        System.out.println(barrelList);
    }
}
