package display;

import java.util.Scanner;

public class ConsoleMenu {
    private static Scanner scanner = new Scanner(System.in);

    public void start() {
        Menu mainMenu = new Menu.Builder("Главное меню")
                .addItem("Ввод данных вручную", () -> option1Menu())
                .addItem("Ввод случайных данных", () -> option2Menu())
                .addItem("Ввод данных из файла", () -> option3Menu())
                .addItem("Выход", () -> System.exit(0))
                .buildMenu();

        while (true) {
            mainMenu.display();
            int choice = getUserChoice(mainMenu.size());

            if (choice >= 0 && choice < mainMenu.size()) {
                mainMenu.getAction(choice).run();
            } else {
                System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void option1Menu() {
        Menu option1Menu = new Menu.Builder("Меню проебразований")
                .addItem("Сортировка данных", () -> System.out.println("Вы выбрали Подопцию 1.1"))
                .addItem("Поиск элемента", () -> System.out.println("Вы выбрали Подопцию 1.2"))
                .buildMenu();

        navigateMenu(option1Menu);
    }

    private static void option2Menu() {
        Menu option2Menu = new Menu.Builder("Меню проебразований")
                .addItem("Сортировка данных", () -> System.out.println("Вы выбрали Подопцию 2.1"))
                .addItem("Поиск элемента", () -> System.out.println("Вы выбрали Подопцию 2.2"))
                .buildMenu();

        navigateMenu(option2Menu);
    }

    private static void option3Menu() {
        Menu option2Menu = new Menu.Builder("Меню проебразований")
                .addItem("Сортировка данных", () -> System.out.println("Вы выбрали Подопцию 3.1"))
                .addItem("Поиск элемента", () -> System.out.println("Вы выбрали Подопцию 3.2"))
                .buildMenu();

        navigateMenu(option2Menu);
    }

    private static void navigateMenu(Menu menu) {
        while (true) {
            menu.display();
            int choice = getUserChoice(menu.size() + 1); // +1 для возврата

            if (choice == menu.size()) {
                return; // Возврат в предыдущее меню
            }

            Runnable action = menu.getAction(choice);
            if (action != null) {
                action.run();
            } else {
                System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static int getUserChoice(int range) {
        System.out.print("Выберите опцию (1-" + range + "): ");
        return scanner.nextInt() - 1; // -1 для преобразования к индексу
    }
}
