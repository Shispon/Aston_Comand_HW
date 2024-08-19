package repository;

import entity.PersonModel;
import service.BinarySearchService;
import service.SortingService;
import service.comparators.PersonComparator;
import service.serialization.ConsoleSerialization;
import service.serialization.FileSerialization;
import service.serialization.RandomSerialization;
import service.serialization.ValidationException;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonRepository implements Repository<PersonModel> {

    private final List<PersonModel> personList = new ArrayList<>();
    private final ConsoleSerialization<PersonModel> consoleSerialization;
    private final FileSerialization<PersonModel> fileSerialization;
    private final RandomSerialization<PersonModel> randomSerialization;
    private final PersonComparator personComparator;

    public PersonRepository(ConsoleSerialization<PersonModel> consoleSerialization,
                            FileSerialization<PersonModel> fileSerialization,
                            RandomSerialization<PersonModel> randomSerialization,
                            PersonComparator personComparator) {
        this.consoleSerialization = consoleSerialization;
        this.fileSerialization = fileSerialization;
        this.randomSerialization = randomSerialization;
        this.personComparator = personComparator;
    }

    @Override
    public void sortedList() {
        SortingService.insertionSort(personList, personComparator.getComparator());
    }
    public void specialSortedList() {
        SortingService.specialSort(personList, personComparator.getComparator(),PersonModel::getAge);
    }

    @Override
    public PersonModel search(PersonModel person) {
        return BinarySearchService.search(personList, person, personComparator.getComparator());
    }

    @Override
    public void addByConsole() throws ValidationException {
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

            personList.add(person);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new ValidationException(e.getMessage());
        }
    }

    @Override
    public void getFromFile() {
        List<PersonModel> personsFromFile = fileSerialization.GetObjectsFromFile("persons.json", PersonModel.class);
        personList.clear();
        personList.addAll(personsFromFile);
    }

    @Override
    public void addByFile() {
        fileSerialization.WriteObjectsToFile("persons.json", personList);
    }

    @Override
    public void addByRandom(int count) {
        try {
            List<PersonModel> randomPersons = randomSerialization.GetRandomObjects(count, PersonModel.class);
            personList.addAll(randomPersons);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Error generating random persons: " + e.getMessage(), e);
        }
    }

    @Override
    public void getAll() {
        System.out.println(personList);
    }
}
