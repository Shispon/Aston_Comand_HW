package repository;

import entity.PersonModel;
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

public class PersonRepository implements Repository<PersonModel> {

    private final List<PersonModel> personList = new ArrayList<>();
    private final ConsoleSerialization<PersonModel> consoleSerialization;
    private final FileSerialization<PersonModel> fileSerialization;
    private final RandomSerialization<PersonModel> randomSerialization;
    private SortingLogic<PersonModel> sortingLogic;  // Поле для хранения текущей сортировки
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

    /**
     * Устанавливает алгоритм сортировки.
     *
     * @param sortingLogic новый алгоритм сортировки
     */
    public void setSortingLogic(SortingLogic<PersonModel> sortingLogic) {
        this.sortingLogic = sortingLogic;
    }

    /**
     * Сортирует список людей с использованием стандартной сортировки.
     */
    public void sortedPersonList() {
        setSortingLogic(new InsertionSort<>());
        sortingLogic.sort(personList, Comparators.personComparator());
    }

    /**
     * Специально сортирует список людей, где сортируются только те люди,
     * возраст которых является четным, а затем возвращаются в их исходные позиции.
     */
    public void specialSortedPersonList() {
        NumericExtractor<PersonModel> extractor = PersonModel::getAge;
        setSortingLogic(new SpecialSort<>(extractor));
        sortingLogic.sort(personList, Comparators.personComparator());
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
