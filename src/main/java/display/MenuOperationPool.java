package display;
import entity.AnimalModel;
import entity.BarrelModel;
import entity.PersonModel;
import repository.AnimalRepository;
import repository.BarrelRepository;
import repository.PersonRepository;
import service.serialization.ConsoleSerialization;
import service.serialization.FileSerialization;
import service.serialization.RandomSerialization;
import service.serialization.ValidationException;

import java.util.Scanner;

public class MenuOperationPool {
    private static  PersonRepository personRepository;
    private static AnimalRepository animalRepository;
    private static BarrelRepository barrelRepository;
    public  MenuOperationPool(ConsoleSerialization<PersonModel> personModelConsoleSerialization,
                              FileSerialization<PersonModel> personModelFileSerialization,
                              RandomSerialization<PersonModel> personModelRandomSerialization,
                              ConsoleSerialization<AnimalModel> animalConsoleSerialization,
                              FileSerialization<AnimalModel> animalModelFileSerialization,
                              RandomSerialization<AnimalModel> animalModelRandomSerialization,
                              ConsoleSerialization<BarrelModel> barrelModelConsoleSerialization,
                              FileSerialization<BarrelModel> barrelModelFileSerialization,
                              RandomSerialization<BarrelModel> barrelModelRandomSerialization) {
        personRepository = new PersonRepository(personModelConsoleSerialization, personModelFileSerialization, personModelRandomSerialization);
        animalRepository = new AnimalRepository(animalConsoleSerialization, animalModelFileSerialization, animalModelRandomSerialization);
        barrelRepository = new BarrelRepository(barrelModelConsoleSerialization, barrelModelFileSerialization, barrelModelRandomSerialization);
    }
    public static void manualInputSorting(int currentDataObjectId) {
        System.out.println("Вы выбрали сортировку данных, введенных вручную");

        switch (currentDataObjectId){
            case 1:
                try {
                    personRepository.addPersonByConsole();
                    personRepository.sortedPersonList();
                    personRepository.getAllPerson();
                } catch (ValidationException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 2:
                try {
                    animalRepository.addAnimalByConsole();
                    animalRepository.sortedAnimalList();
                    animalRepository.getAllAnimals();
                } catch (ValidationException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 3:
                try {
                    barrelRepository.addBarrelByConsole();
                    barrelRepository.sortedBarrelList();
                    barrelRepository.getAllBarrels();
                } catch (ValidationException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    public static void manualInputSearch(int currentDataObjectId) {
        System.out.println("Вы выбрали поиск данных, введенных вручную");
        switch (currentDataObjectId){
            case 1:
                try {
                    personRepository.addPersonByConsole();
                    personRepository.sortedPersonList();
                    personRepository.getAllPerson();

                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Введите фамилию");
                    String lastname = scanner.nextLine();
                    System.out.println("Введите пол");
                    String gender = scanner.nextLine();
                    System.out.println("Введите возраст");
                    int age = scanner.nextInt();
                    System.out.println(personRepository.searchPerson(PersonModel.builder().lastName(lastname).gender(gender).age(age).build()));

                } catch (ValidationException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 2:
                try {
                    animalRepository.addAnimalByConsole();
                    animalRepository.sortedAnimalList();
                    animalRepository.getAllAnimals();

                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Введите вид животного");
                    String species = scanner.nextLine();
                    System.out.println("Введите цвет глаз");
                    String eyecolor = scanner.nextLine();
                    System.out.println("Введите наличие шерсти");
                    boolean hasFur = scanner.nextBoolean();
                    System.out.println(animalRepository.searchAnimal(AnimalModel.builder().species(species).eyeColor(eyecolor).hasFur(hasFur).build()));

                } catch (ValidationException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 3:
                try {
                    barrelRepository.addBarrelByConsole();
                    barrelRepository.sortedBarrelList();
                    barrelRepository.getAllBarrels();

                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Введите хранимый материал");
                    String storedMaterial = scanner.nextLine();
                    System.out.println("Введите матерал тарры");
                    String material = scanner.nextLine();
                    System.out.println("Введите объем");
                    double volume = scanner.nextDouble();
                    System.out.println(barrelRepository.searchBarrel(BarrelModel.builder().storedMaterial(storedMaterial).material(material).volume(volume).build()));

                } catch (ValidationException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + currentDataObjectId);
        }
    }

    public static void manualInputSpecialSort(int currentDataObjectId) {
        System.out.println("Вы выбрали специальную сортировку данных, введенных вручную");

        switch (currentDataObjectId){
            case 1:
                try {
                    personRepository.addPersonByConsole();
                    personRepository.specialSortedPersonList();
                    personRepository.getAllPerson();
                } catch (ValidationException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 2:
                try {
                    animalRepository.addAnimalByConsole();
                    System.out.println("Попробуйте позже");
                    animalRepository.getAllAnimals();
                } catch (ValidationException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 3:
                try {
                    barrelRepository.addBarrelByConsole();
                    barrelRepository.specialSortedBarrelList();
                    barrelRepository.getAllBarrels();
                } catch (ValidationException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    public static void randomInputSorting(int currentDataObjectId) {
        System.out.println("Вы выбрали сортировку случайно сгенерированных данных");

        switch (currentDataObjectId){
            case 1:
                Scanner personScanner = new Scanner(System.in);
                System.out.println("Введите количество людей");
                int personCount = personScanner.nextInt();
                personRepository.addPersonsByRandom(personCount);
                personRepository.sortedPersonList();
                personRepository.getAllPerson();
                personScanner.close();
                break;

            case 2:
                Scanner animalScanner = new Scanner(System.in);
                System.out.println("Введите количество животных");
                int animalCount  = animalScanner.nextInt();

                animalRepository.addAnimalsByRandom(animalCount);
                animalRepository.sortedAnimalList();
                animalRepository.getAllAnimals();
                animalScanner.close();
                break;

            case 3:
                Scanner barrelScanner = new Scanner(System.in);
                System.out.println("Введите количество емкостей");
                int barrelCount  = barrelScanner.nextInt();
                barrelRepository.addBarrelsByRandom(barrelCount);
                barrelRepository.sortedBarrelList();
                barrelRepository.getAllBarrels();
                barrelScanner.close();
                break;
        }
    }


    public static void randomInputSearch(int currentDataObjectId) {
        System.out.println("Вы выбрали посик в случайно сгенерированных значениях");

        switch (currentDataObjectId){
            case 1:
                Scanner personScanner = new Scanner(System.in);
                System.out.println("Введите количество людей");
                int personCount = personScanner.nextInt();
                personRepository.addPersonsByRandom(personCount);
                personRepository.sortedPersonList();
                personRepository.getAllPerson();

                System.out.println("Введите фамилию");
                String lastname = personScanner.nextLine();
                System.out.println("Введите пол");
                String gender = personScanner.nextLine();
                System.out.println("Введите возраст");
                int age = personScanner.nextInt();
                System.out.println(personRepository.searchPerson(PersonModel.builder().lastName(lastname).gender(gender).age(age).build()));

                personScanner.close();
                break;

            case 2:
                Scanner animalScanner = new Scanner(System.in);
                System.out.println("Введите количество животных");
                int animalCount  = animalScanner.nextInt();

                animalRepository.addAnimalsByRandom(animalCount);
                animalRepository.sortedAnimalList();
                animalRepository.getAllAnimals();

                System.out.println("Введите вид животного");
                String species = animalScanner.nextLine();
                System.out.println("Введите цвет глаз");
                String eyecolor = animalScanner.nextLine();
                System.out.println("Введите наличие шерсти");
                boolean hasFur = animalScanner.nextBoolean();
                System.out.println(animalRepository.searchAnimal(AnimalModel.builder().species(species).eyeColor(eyecolor).hasFur(hasFur).build()));


                animalScanner.close();
                break;

            case 3:
                Scanner barrelScanner = new Scanner(System.in);
                System.out.println("Введите количество емкостей");
                int barrelCount  = barrelScanner.nextInt();
                barrelRepository.addBarrelsByRandom(barrelCount);
                barrelRepository.sortedBarrelList();
                barrelRepository.getAllBarrels();

                System.out.println("Введите хранимый материал");
                String storedMaterial = barrelScanner.nextLine();
                System.out.println("Введите матерал тарры");
                String material = barrelScanner.nextLine();
                System.out.println("Введите объем");
                double volume = barrelScanner.nextDouble();
                System.out.println(barrelRepository.searchBarrel(BarrelModel.builder().storedMaterial(storedMaterial).material(material).volume(volume).build()));

                barrelScanner.close();
                break;
        }
    }

    public static void randomInputSpecialSort(int currentDataObjectId) {
        System.out.println("Вы выбрали специальную сортировку данных, введенных вручную");

        switch (currentDataObjectId){
            case 1:
                Scanner personScanner = new Scanner(System.in);
                System.out.println("Введите количество людей");
                int personCount = personScanner.nextInt();
                personRepository.addPersonsByRandom(personCount);
                personRepository.specialSortedPersonList();
                personRepository.getAllPerson();

                break;
            case 2:
                Scanner animalScanner = new Scanner(System.in);
                System.out.println("Введите количество животных");
                int animalCount  = animalScanner.nextInt();
                System.out.println("Попробуйте позже");
                animalRepository.getAllAnimals();

                break;
            case 3:
                Scanner barrelScanner = new Scanner(System.in);
                System.out.println("Введите количество емкостей");
                int barrelCount = barrelScanner.nextInt();
                barrelRepository.addBarrelsByRandom(barrelCount);
                barrelRepository.specialSortedBarrelList();
                barrelRepository.getAllBarrels();

                break;
        }
    }

    public static void fileInputSorting(int currentDataObjectId) {
        System.out.println("Вы выбрали сортировку данных из файла");

        switch (currentDataObjectId){
            case 1:
                personRepository.getPersonsFromFile();
                personRepository.sortedPersonList();
                personRepository.getAllPerson();

                break;
            case 2:
                animalRepository.getAllAnimals();
                animalRepository.sortedAnimalList();
                animalRepository.getAllAnimals();

                break;
            case 3:
                barrelRepository.getBarrelsFromFile();
                barrelRepository.sortedBarrelList();
                barrelRepository.getAllBarrels();

                break;
        }
    }

    public static void fileInputSearch(int currentDataObjectId) {
        System.out.println("Вы выбрали поиск значения в файле");

        switch (currentDataObjectId){
            case 1:
                personRepository.getPersonsFromFile();
                personRepository.sortedPersonList();
                personRepository.getAllPerson();

                Scanner personScanner = new Scanner(System.in);
                System.out.println("Введите фамилию");
                String lastname = personScanner.nextLine();
                System.out.println("Введите пол");
                String gender = personScanner.nextLine();
                System.out.println("Введите возраст");
                int age = personScanner.nextInt();
                System.out.println(personRepository.searchPerson(PersonModel.builder().lastName(lastname).gender(gender).age(age).build()));

                personScanner.close();
                break;

            case 2:
                animalRepository.getAnimalsFromFile();
                animalRepository.sortedAnimalList();
                animalRepository.getAllAnimals();

                Scanner animalScanner = new Scanner(System.in);
                System.out.println("Введите вид животного");
                String species = animalScanner.nextLine();
                System.out.println("Введите цвет глаз");
                String eyecolor = animalScanner.nextLine();
                System.out.println("Введите наличие шерсти");
                boolean hasFur = animalScanner.nextBoolean();
                System.out.println(animalRepository.searchAnimal(AnimalModel.builder().species(species).eyeColor(eyecolor).hasFur(hasFur).build()));


                animalScanner.close();
                break;

            case 3:
                Scanner barrelScanner = new Scanner(System.in);

                barrelRepository.getBarrelsFromFile();
                barrelRepository.sortedBarrelList();
                barrelRepository.getAllBarrels();

                System.out.println("Введите хранимый материал");
                String storedMaterial = barrelScanner.nextLine();
                System.out.println("Введите матерал тарры");
                String material = barrelScanner.nextLine();
                System.out.println("Введите объем");
                double volume = barrelScanner.nextDouble();
                System.out.println(barrelRepository.searchBarrel(BarrelModel.builder().storedMaterial(storedMaterial).material(material).volume(volume).build()));

                barrelScanner.close();
                break;
        }
    }

    public static void fileInputSpecialSort(int currentDataObjectId) {
        System.out.println("Вы выбрали сортировку четных значений из файла");

        switch (currentDataObjectId){
            case 1:

                personRepository.getPersonsFromFile();
                personRepository.sortedPersonList();
                personRepository.getAllPerson();

                break;
            case 2:
                animalRepository.getAllAnimals();
                System.out.println("Попробуйте позже");
//                animalRepository.sortedAnimalList();
                animalRepository.getAllAnimals();

                break;
            case 3:
                barrelRepository.getBarrelsFromFile();
                barrelRepository.specialSortedBarrelList();
                barrelRepository.getAllBarrels();

                break;
        }
    }
}
