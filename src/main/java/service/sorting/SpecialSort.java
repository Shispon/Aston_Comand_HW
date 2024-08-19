package service.sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SpecialSort<T> implements SortingLogic<T> {
    private final NumericExtractor<T> extractor;

    public SpecialSort(NumericExtractor<T> extractor) {
        this.extractor = extractor;
    }

    @Override
    public void sort(List<T> list, Comparator<? super T> comparator) {
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
        new InsertionSort<T>().sort(evenValues, comparator);

        // Вставляем отсортированные четные элементы обратно в их исходные позиции
        for (int i = 0; i < indices.size(); i++) {
            list.set(indices.get(i), evenValues.get(i));
        }
    }
}
