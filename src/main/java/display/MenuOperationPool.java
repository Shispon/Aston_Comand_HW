package display;

import entity.AnimalModel;
import entity.BarrelModel;
import entity.PersonModel;
import repository.AnimalRepository;
import repository.BarrelRepository;
import repository.PersonRepository;
import service.comparators.AnimalComparator;
import service.comparators.BarrelComparator;
import service.comparators.PersonComparator;
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
                new RandomSerialization<>(),
                new PersonComparator()
        );
        animalRepository = new AnimalRepository(
                new ConsoleSerialization<>(AnimalModel.class),
                new FileSerialization<>(),
                new RandomSerialization<>(),
                new AnimalComparator()
        );
        barrelRepository = new BarrelRepository(
                new ConsoleSerialization<>(BarrelModel.class),
                new FileSerialization<>(),
                new RandomSerialization<>(),
                new BarrelComparator()
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
                    personRepository.getAll();
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
                        personRepository.addByConsole();
                    } catch (ValidationException e) {
                        System.out.println("Ошибка ввода: " + e.getMessage());
                    }
                    break;
                case 2:
                    personRepository.getFromFile();
                    break;
                case 3:
                    System.out.println("Введите количество людей:");
                    int count = scanner.nextInt();
                    personRepository.addByRandom(count);
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
                    personRepository.sortedList();
                    break;
                case 2:
                    personRepository.specialSortedList();
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

        System.out.println(personRepository.search(PersonModel.builder().lastName(lastname).gender(gender).age(age).build()));
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
                    animalRepository.getAll();
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
                        animalRepository.addByConsole();
                    } catch (ValidationException e) {
                        System.out.println("Ошибка ввода: " + e.getMessage());
                    }
                    break;
                case 2:
                    animalRepository.getFromFile();
                    break;
                case 3:
                    System.out.println("Введите количество животных:");
                    int count = scanner.nextInt();
                    animalRepository.addByRandom(count);
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
                    animalRepository.sortedList();
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

        System.out.println(animalRepository.search(AnimalModel.builder().species(species).eyeColor(eyecolor).hasFur(hasFur).build()));
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
                    barrelRepository.getAll();
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
                        barrelRepository.addByConsole();
                    } catch (ValidationException e) {
                        System.out.println("Ошибка ввода: " + e.getMessage());
                    }
                    break;
                case 2:
                    barrelRepository.getFromFile();
                    break;
                case 3:
                    System.out.println("Введите количество бочек:");
                    int count = scanner.nextInt();
                    barrelRepository.addByRandom(count);
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
                    barrelRepository.sortedList();
                    break;
                case 2:
                    barrelRepository.specialSortedList();
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

        System.out.println(barrelRepository.search(BarrelModel.builder().storedMaterial(type).material(material).volume(size).build()));
    }
}
