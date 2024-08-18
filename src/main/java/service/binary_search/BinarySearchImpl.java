package service.binary_search;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class BinarySearchImpl<T> implements BinarySearch<T> {

    /**
     * Выполняет бинарный поиск объекта в отсортированной коллекции типа {@link  List<T>}
     *
     * @param sortedList - {@link List<T>} отсортированная коллекция
     * @param key        - объект с полями поиска
     * @param comparator - {@link Comparator<T>} компаратор сравнение
     * @return           - {@link Optional<T>}
     */
    @Override
    public Optional<T> binarySearch(List<T> sortedList, T key, Comparator<T> comparator) {
        int mid;
        int min = 0;
        int max = sortedList.size() - 1;
        int resultOfCompare;
        while (min <= max) {
            mid = min + (max - min) / 2;
            T object = sortedList.get(mid);
            resultOfCompare = comparator.compare(key, object);
            if (resultOfCompare == 0) {
                return Optional.of(object);
            } else if (resultOfCompare == 1) {
                min = mid + 1;
            } else if (resultOfCompare == -1) {
                max = mid - 1;
            }
        }
        return Optional.empty();
    }
}
