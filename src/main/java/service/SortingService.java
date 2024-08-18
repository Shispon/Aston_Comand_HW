package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortingService {

    public static <T> void insertionSort(List<T> list, Comparator<? super T> comparator) {
        for (int i = 1; i < list.size(); i++) {
            T key = list.get(i);
            int j = i - 1;

            while (j >= 0 && comparator.compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    public static <T> void specialSort(List<T> list, Comparator<? super T> comparator, NumericExtractor<T> extractor) {
        List<T> evenValues = new ArrayList<>();
        List<Integer> indices = new ArrayList<>();

        // Разделяем элементы на четные и нечетные
        for (int i = 0; i < list.size(); i++) {
            T element = list.get(i);
            if (extractor.getNumericValue(element) % 2 == 0) {
                evenValues.add(element);
                indices.add(i);
            }
        }

        // Сортируем только четные элементы
        SortingService.insertionSort(evenValues, comparator);

        // Вставляем отсортированные четные элементы обратно в их исходные позиции
        for (int i = 0; i < indices.size(); i++) {
            list.set(indices.get(i), evenValues.get(i));
        }
    }


    // Функциональный интерфейс для извлечения числового значения типа int
    @FunctionalInterface
    public interface NumericExtractor<T> {
        int getNumericValue(T item);
    }
}
