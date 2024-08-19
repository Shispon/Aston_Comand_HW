package display;

import java.util.Scanner;

public class ConsoleMenu {
    private static Scanner scanner = new Scanner(System.in);
    private static int currentDataObjectId;

    public static void main(String[] args) {
        ConsoleMenu test = new ConsoleMenu();
        test.start();
    }

    public void start() {

        Menu objectMenu = new Menu.Builder("Выберите тип данных")
                .addItem("Люди", () -> currentDataObjectId = 1)
                .addItem("Животные", () -> currentDataObjectId = 2)
                .addItem("Бочки", () -> currentDataObjectId = 3)
                .addItem("Выход", () -> System.exit(0))
                .buildMenu();

        while (true) {
            objectMenu.display();
            int choice = getUserChoice(objectMenu.size());

            if (choice >= 0 && choice < objectMenu.size()) {
                objectMenu.getAction(choice).run();
                System.out.println("Выбран объект с id: " + currentDataObjectId);

                // Переход к главному меню после выбора объекта
                break; // Прерываем цикл после выбора
            } else {
                System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }


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
                .addItem("Сортировка данных", () -> MenuOperationPool.manualInputSorting(currentDataObjectId))
                .addItem("Поиск элемента", () -> MenuOperationPool.manualInputSearch(currentDataObjectId))
                .addItem("Сортировка по четным числовым характеристикам", () -> MenuOperationPool.manualInputSpecialSort(currentDataObjectId))
                .buildMenu();

        navigateMenu(option1Menu);
    }

    private static void option2Menu() {
        Menu option2Menu = new Menu.Builder("Меню проебразований")
                .addItem("Сортировка данных", () -> MenuOperationPool.randomInputSorting(currentDataObjectId))
                .addItem("Поиск элемента", () -> MenuOperationPool.randomInputSearch(currentDataObjectId))
                .addItem("Сортировка по четным числовым характеристикам", () -> MenuOperationPool.randomInputSpecialSort(currentDataObjectId))
                .buildMenu();

        navigateMenu(option2Menu);
    }

    private static void option3Menu() {
        Menu option2Menu = new Menu.Builder("Меню проебразований")
                .addItem("Сортировка данных", () -> MenuOperationPool.fileInputSorting(currentDataObjectId))
                .addItem("Поиск элемента", () -> MenuOperationPool.fileInputSearch(currentDataObjectId))
                .addItem("Сортировка по четным числовым характеристикам", () -> MenuOperationPool.fileInputSpecialSort(currentDataObjectId))
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
