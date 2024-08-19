package display;

import java.util.Scanner;

public class Menu {
    public static void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите сущность для работы:");
            System.out.println("1. Person");
            System.out.println("2. Animal");
            System.out.println("3. Barrel");
            System.out.println("0. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    MenuOperationPool.handlePersonActions();
                    break;
                case 2:
                    MenuOperationPool.handleAnimalActions();
                    break;
                case 3:
                    MenuOperationPool.handleBarrelActions();
                    break;
                case 0:
                    System.out.println("Выход...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }
}
