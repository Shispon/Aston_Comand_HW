package repository;

import entity.PersonModel;
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

public class PersonRepository {
    private final List<PersonModel> personList = new ArrayList<>();
    private final ConsoleSerialization<PersonModel> consoleSerialization;
    private final FileSerialization<PersonModel> fileSerialization;
    private final RandomSerialization<PersonModel> randomSerialization;

    // Конструктор для инициализации зависимостей
    public PersonRepository(ConsoleSerialization<PersonModel> consoleSerialization,
                            FileSerialization<PersonModel> fileSerialization,
                            RandomSerialization<PersonModel> randomSerialization) {
        this.consoleSerialization = consoleSerialization;
        this.fileSerialization = fileSerialization;
        this.randomSerialization = randomSerialization;
    }

    // Метод для сортировки списка PersonModel
    public void sortedPersonList() {
        SortingService.insertionSort(personList, Comparators.personComparator());
    }

    // Метод для поиска PersonModel в списке
    public boolean searchPerson(PersonModel person) {
        return BinarySearchService.search(personList, person, Comparators.personComparator()) != null;
    }

    // Метод для добавления PersonModel через консольный ввод
    public void addPersonByConsole() throws ValidationException {
        Scanner scanner = new Scanner(System.in);
        PersonModel person = new PersonModel();

        try {
            System.out.print("Enter last name: ");
            String lastName = scanner.nextLine();
            consoleSerialization.SetObjectsProperty(person, "lastName", lastName);

            System.out.print("Enter gender: ");
            String gender = scanner.nextLine();
            consoleSerialization.SetObjectsProperty(person, "gender", gender);

            System.out.print("Enter age: ");
            String age = scanner.nextLine();
            consoleSerialization.SetObjectsProperty(person, "age", age);

            // Добавляем PersonModel в список
            personList.add(person);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new ValidationException(e.getMessage());
        }
    }

    // Метод для получения PersonModel из файла
    public void getPersonsFromFile() {
        List<PersonModel> personsFromFile = fileSerialization.GetObjectsFromFile("persons.json", PersonModel.class);
        personList.clear();
        personList.addAll(personsFromFile);
    }

    // Метод для добавления PersonModel в файл
    public void addPersonsByFile() {
        fileSerialization.WriteObjectsToFile("persons.json", personList);
    }

    // Метод для добавления случайных PersonModel
    public void addPersonsByRandom(int count) {
        try {
            List<PersonModel> randomPersons = randomSerialization.GetRandomObjects(count, PersonModel.class);
            personList.addAll(randomPersons);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Error generating random persons: " + e.getMessage(), e);
        }
    }

    public void getAllPerson () {
        System.out.println(personList);
    }
}
