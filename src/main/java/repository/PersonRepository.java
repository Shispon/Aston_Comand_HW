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

/**
 * Репозиторий для работы с коллекцией людей.
 * Предоставляет функциональность для сортировки, поиска, добавления и загрузки людей.
 */
public class PersonRepository {

    private final List<PersonModel> personList = new ArrayList<>();
    private final ConsoleSerialization<PersonModel> consoleSerialization;
    private final FileSerialization<PersonModel> fileSerialization;
    private final RandomSerialization<PersonModel> randomSerialization;

    /**
     * Конструктор класса {@code PersonRepository}.
     *
     * @param consoleSerialization объект для сериализации в консоль
     * @param fileSerialization объект для сериализации в файл
     * @param randomSerialization объект для случайной сериализации
     */
    public PersonRepository(ConsoleSerialization<PersonModel> consoleSerialization,
                            FileSerialization<PersonModel> fileSerialization,
                            RandomSerialization<PersonModel> randomSerialization) {
        this.consoleSerialization = consoleSerialization;
        this.fileSerialization = fileSerialization;
        this.randomSerialization = randomSerialization;
    }

    /**
     * Сортирует список людей с использованием стандартной сортировки.
     */
    public void sortedPersonList() {
        SortingService.insertionSort(personList, Comparators.personComparator());
    }

    /**
     * Специально сортирует список людей, где сортируются только те люди,
     * возраст которых является четным, а затем возвращаются в их исходные позиции.
     */
    public void specialSortedPersonList() {
        SortingService.specialSort(personList, Comparators.personComparator(), PersonModel::getAge);
    }

    /**
     * Ищет человека в списке по указанным критериям.
     *
     * @param person человек для поиска
     * @return {@code true}, если человек найден, иначе {@code false}
     */
    public PersonModel searchPerson(PersonModel person) {
        return BinarySearchService.search(personList, person, Comparators.personComparator());
    }

    /**
     * Добавляет человека в список на основе ввода с консоли.
     *
     * @throws ValidationException если возникла ошибка при валидации данных
     */
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

    /**
     * Загружает людей из файла и обновляет список людей.
     */
    public void getPersonsFromFile() {
        List<PersonModel> personsFromFile = fileSerialization.GetObjectsFromFile("persons.json", PersonModel.class);
        personList.clear();
        personList.addAll(personsFromFile);
    }

    /**
     * Записывает список людей в файл.
     */
    public void addPersonsByFile() {
        fileSerialization.WriteObjectsToFile("persons.json", personList);
    }

    /**
     * Добавляет случайно сгенерированных людей в список.
     *
     * @param count количество людей для генерации
     * @throws RuntimeException если возникает ошибка при генерации случайных людей
     */
    public void addPersonsByRandom(int count) {
        try {
            List<PersonModel> randomPersons = randomSerialization.GetRandomObjects(count, PersonModel.class);
            personList.addAll(randomPersons);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Error generating random persons: " + e.getMessage(), e);
        }
    }

    /**
     * Выводит все люди из списка на консоль.
     */
    public void getAllPerson() {
        System.out.println(personList);
    }
}
