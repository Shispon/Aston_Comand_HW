package service.binary_search;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface BinarySearch<T> {
    Optional<T> binarySearch(List<T> sortedList, T key, Comparator<T> comparator);
}
