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
    private static final PersonRepository personRepository;
    private static final AnimalRepository animalRepository;
    private static final BarrelRepository barrelRepository;

    static {
        // Инициализация репозиториев с необходимыми сериализаторами
        personRepository = new PersonRepository(
                new ConsoleSerialization<>(PersonModel.class),
                new FileSerialization<>(),
                new RandomSerialization<>()
        );
        animalRepository = new AnimalRepository(
                new ConsoleSerialization<>(AnimalModel.class),
                new FileSerialization<>(),
                new RandomSerialization<>()
        );
        barrelRepository = new BarrelRepository(
                new ConsoleSerialization<>(BarrelModel.class),
                new FileSerialization<>(),
                new RandomSerialization<>()
        );
    }

    public static void handlePersonActions() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите действие для Person:");
            System.out.println("1. Ввод данных");
            System.out.println("2. Отсортировать");
            System.out.println("3. Найти");
            System.out.println("4. Вывести все");
            System.out.println("0. Назад");

            int action = scanner.nextInt();
            scanner.nextLine();  // Потребуется для следующего ввода строки

            switch (action) {
                case 1:
                    handlePersonInput();
                    break;
                case 2:
                    handlePersonSorting();
                    break;
                case 3:
                    handlePersonSearch();
                    break;
                case 4:
                    personRepository.getAllPerson();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private static void handlePersonInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите метод ввода данных:");
            System.out.println("1. Ввод вручную");
            System.out.println("2. Ввод из файла");
            System.out.println("3. Ввод случайно");
            System.out.println("0. Назад");

            int method = scanner.nextInt();
            scanner.nextLine();  // Потребуется для следующего ввода строки

            switch (method) {
                case 1:
                    try {
                        personRepository.addPersonByConsole();
                    } catch (ValidationException e) {
                        System.out.println("Ошибка ввода: " + e.getMessage());
                    }
                    break;
                case 2:
                    personRepository.getPersonsFromFile();
                    break;
                case 3:
                    System.out.println("Введите количество людей:");
                    int count = scanner.nextInt();
                    personRepository.addPersonsByRandom(count);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private static void handlePersonSorting() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите метод сортировки:");
            System.out.println("1. Сортировка методом вставок");
            System.out.println("2. Специальная сортировка");
            System.out.println("0. Назад");

            int method = scanner.nextInt();
            scanner.nextLine();  // Потребуется для следующего ввода строки

            switch (method) {
                case 1:
                    personRepository.sortedPersonList();
                    break;
                case 2:
                    personRepository.specialSortedPersonList();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private static void handlePersonSearch() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите фамилию:");
        String lastname = scanner.nextLine();
        System.out.println("Введите пол:");
        String gender = scanner.nextLine();
        System.out.println("Введите возраст:");
        int age = scanner.nextInt();
        scanner.nextLine();  // Потребуется для следующего ввода строки

        System.out.println(personRepository.searchPerson(PersonModel.builder().lastName(lastname).gender(gender).age(age).build()));
    }

    public static void handleAnimalActions() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите действие для Animal:");
            System.out.println("1. Ввод данных");
            System.out.println("2. Отсортировать");
            System.out.println("3. Найти");
            System.out.println("4. Вывести все");
            System.out.println("0. Назад");

            int action = scanner.nextInt();
            scanner.nextLine();  // Потребуется для следующего ввода строки

            switch (action) {
                case 1:
                    handleAnimalInput();
                    break;
                case 2:
                    handleAnimalSorting();
                    break;
                case 3:
                    handleAnimalSearch();
                    break;
                case 4:
                    animalRepository.getAllAnimals();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private static void handleAnimalInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите метод ввода данных:");
            System.out.println("1. Ввод вручную");
            System.out.println("2. Ввод из файла");
            System.out.println("3. Ввод случайно");
            System.out.println("0. Назад");

            int method = scanner.nextInt();
            scanner.nextLine();  // Потребуется для следующего ввода строки

            switch (method) {
                case 1:
                    try {
                        animalRepository.addAnimalByConsole();
                    } catch (ValidationException e) {
                        System.out.println("Ошибка ввода: " + e.getMessage());
                    }
                    break;
                case 2:
                    animalRepository.getAnimalsFromFile();
                    break;
                case 3:
                    System.out.println("Введите количество животных:");
                    int count = scanner.nextInt();
                    animalRepository.addAnimalsByRandom(count);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private static void handleAnimalSorting() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите метод сортировки:");
            System.out.println("1. Сортировка методом вставок");
            System.out.println("0. Назад");

            int method = scanner.nextInt();
            scanner.nextLine();  // Потребуется для следующего ввода строки

            switch (method) {
                case 1:
                    animalRepository.sortedAnimalList();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private static void handleAnimalSearch() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите вид животного:");
        String species = scanner.nextLine();
        System.out.println("Введите цвет глаз:");
        String eyecolor = scanner.nextLine();
        System.out.println("Введите наличие шерсти:");
        boolean hasFur = scanner.nextBoolean();
        scanner.nextLine();  // Потребуется для следующего ввода строки

        System.out.println(animalRepository.searchAnimal(AnimalModel.builder().species(species).eyeColor(eyecolor).hasFur(hasFur).build()));
    }

    public static void handleBarrelActions() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите действие для Barrel:");
            System.out.println("1. Ввод данных");
            System.out.println("2. Отсортировать");
            System.out.println("3. Найти");
            System.out.println("4. Вывести все");
            System.out.println("0. Назад");

            int action = scanner.nextInt();
            scanner.nextLine();  // Потребуется для следующего ввода строки

            switch (action) {
                case 1:
                    handleBarrelInput();
                    break;
                case 2:
                    handleBarrelSorting();
                    break;
                case 3:
                    handleBarrelSearch();
                    break;
                case 4:
                    barrelRepository.getAllBarrels();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private static void handleBarrelInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите метод ввода данных:");
            System.out.println("1. Ввод вручную");
            System.out.println("2. Ввод из файла");
            System.out.println("3. Ввод случайно");
            System.out.println("0. Назад");

            int method = scanner.nextInt();
            scanner.nextLine();  // Потребуется для следующего ввода строки

            switch (method) {
                case 1:
                    try {
                        barrelRepository.addBarrelByConsole();
                    } catch (ValidationException e) {
                        System.out.println("Ошибка ввода: " + e.getMessage());
                    }
                    break;
                case 2:
                    barrelRepository.getBarrelsFromFile();
                    break;
                case 3:
                    System.out.println("Введите количество бочек:");
                    int count = scanner.nextInt();
                    barrelRepository.addBarrelsByRandom(count);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private static void handleBarrelSorting() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите метод сортировки:");
            System.out.println("1. Сортировка методом вставок");
            System.out.println("2. Специальная сортировка");
            System.out.println("0. Назад");

            int method = scanner.nextInt();
            scanner.nextLine();  // Потребуется для следующего ввода строки

            switch (method) {
                case 1:
                    barrelRepository.sortedBarrelList();
                    break;
                case 2:
                    barrelRepository.specialSortedBarrelList();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }

    private static void handleBarrelSearch() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите тип бочки:");
        String type = scanner.nextLine();
        System.out.println("Введите что хранится в бочке:");
        String material = scanner.nextLine();
        System.out.println("Введите размер:");
        double size = scanner.nextDouble();
        scanner.nextLine();  // Потребуется для следующего ввода строки

        System.out.println(barrelRepository.searchBarrel(BarrelModel.builder().storedMaterial(type).material(material).volume(size).build()));
    }
}
