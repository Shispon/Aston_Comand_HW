package service;

import entity.AnimalModel;
import entity.BarrelModel;
import entity.PersonModel;
import service.comparator.AnimalComparator;
import service.comparator.BarrelComparator;
import service.comparator.PersonComparator;

import java.util.Comparator;

public class SortingService {


    // Реализация сортировки вставками
    public static <T> void insertionSort(T[] array, Comparator<? super T> comparator) {
        for (int i = 1; i < array.length; i++) {
            T key = array[i];
            int j = i - 1;
            while (j >= 0 && comparator.compare(array[j], key) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }
    public static void main(String[] args) {
        // Примеры данных для сортировки
        AnimalModel[] animalModels = {
                new AnimalModel("Кот", "Зеленый", true),
                new AnimalModel("Кот", "Бурый", true),
                new AnimalModel("Кот", "Бурый", false),
                new AnimalModel("Кот", "Бурый", true),
                new AnimalModel("Собака", "Карий", true),
                new AnimalModel("Лев", "Желтый", true),

        };

        BarrelModel[] barrelModels = {
                new BarrelModel(1.0,"Juice", "Plastic"),
                new BarrelModel(1.2,"Juice", "Plastic"),
                new BarrelModel(1.1,"Juice", "Plastic"),
                new BarrelModel(1.0,"Juice", "Plastic"),
        };


        PersonModel[] personModels={
                new PersonModel("Male", 30, "Ivanov"),
                new PersonModel("Male", 5, "Ivanov"),
                new PersonModel("Male", 35, "Ivanov"),
                new PersonModel("Male", 21, "Ivanov"),
                new PersonModel("Female", 21, "Ivanova"),
                new PersonModel("Female", 22, "Ivanova"),
        };



        // Сортировка данных
        insertionSort(animalModels, new AnimalComparator());
        insertionSort(barrelModels, new BarrelComparator());
        insertionSort(personModels, new PersonComparator());


        // Вывод отсортированных данных
        System.out.println("Отсортированные животные:");
        for (AnimalModel animalModel : animalModels) {
            System.out.println(animalModel);
        }

        System.out.println("Отсортированные бочки:");

        for (BarrelModel barrelModel : barrelModels) {
            System.out.println(barrelModel);
        }

        System.out.println("Отсортированные люди:");
        for (PersonModel personModel : personModels) {
            System.out.println(personModel);
        }

    }
}
