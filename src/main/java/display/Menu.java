package display;

import java.util.ArrayList;
import java.util.List;

class Menu {
    private String title;
    private List<MenuItem> items;

    private Menu(Builder builder) {
        this.title = builder.title;
        this.items = builder.items;
    }

    public static class Builder {
        private String title;
        private List<MenuItem> items = new ArrayList<>();

        public Builder(String title) {
            this.title = title;
        }

        public Builder addItem(String name, Runnable action) {
            items.add(new MenuItem(name, action));
            return this;
        }

        public Menu buildMenu() {
            return new Menu(this);
        }
    }

    public void display() {
        System.out.println("==== " + title + " ====");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getName());
        }
        System.out.println((items.size() + 1) + ". Назад");
    }

    public Runnable getAction(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index).getAction();
        }
        return null; // Возврат null если индекс вне диапазона
    }

    public int size() {
        return items.size();
    }

    private static class MenuItem {
        private String name;
        private Runnable action;

        public MenuItem(String name, Runnable action) {
            this.name = name;
            this.action = action;
        }

        public String getName() {
            return name;
        }

        public Runnable getAction() {
            return action;
        }
    }
}
