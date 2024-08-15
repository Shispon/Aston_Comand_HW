package display;

import java.util.Scanner;

public class ConsoleMenu {
    private static Scanner scanner = new Scanner(System.in);

    public void start() {
        Menu mainMenu = new Menu.Builder("Главное меню")
                .addItem("Ввод данных вручную", ConsoleMenu::option1Menu)
                .addItem("Ввод случайных данных", ConsoleMenu::option2Menu)
                .addItem("Ввод данных из файла", ConsoleMenu::option3Menu)
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
                .addItem("Сортировка данных", MenuOperationPool::manualInputSorting)
                .addItem("Поиск элемента", MenuOperationPool::manualInputSearch)
                .buildMenu();

        navigateMenu(option1Menu);
    }

    private static void option2Menu() {
        Menu option2Menu = new Menu.Builder("Меню проебразований")
                .addItem("Сортировка данных", MenuOperationPool::randomInputSorting)
                .addItem("Поиск элемента", MenuOperationPool::randomInputSearch)
                .buildMenu();

        navigateMenu(option2Menu);
    }

    private static void option3Menu() {
        Menu option2Menu = new Menu.Builder("Меню проебразований")
                .addItem("Сортировка данных", MenuOperationPool::fileInputSorting)
                .addItem("Поиск элемента", MenuOperationPool::fileInputSearch)
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
