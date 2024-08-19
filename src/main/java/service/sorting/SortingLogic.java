package service.sorting;

import java.util.Comparator;
import java.util.List;

public interface SortingLogic<T> {
    void sort(List<T> list, Comparator<? super T> comparator);
}
