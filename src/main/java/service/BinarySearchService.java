package service;

import java.util.Comparator;
import java.util.List;

public class BinarySearchService {

    public static <T> T search(List<T> list, T key, Comparator<? super T> comparator) {
        // Проверка, отсортирован ли список
        if (!isSorted(list, comparator)) {
            throw new IllegalArgumentException("The list is not sorted according to the comparator.");
        }

        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            T midVal = list.get(mid);
            int cmp = comparator.compare(midVal, key);

            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return midVal; // Возвращаем найденный объект
            }
        }
        return null; // Возвращаем null, если объект не найден
    }

    private static <T> boolean isSorted(List<T> list, Comparator<? super T> comparator) {
        for (int i = 1; i < list.size(); i++) {
            if (comparator.compare(list.get(i - 1), list.get(i)) > 0) {
                return false; // Найдено несоответствие, список не отсортирован
            }
        }
        return true; // Список отсортирован
    }
}
