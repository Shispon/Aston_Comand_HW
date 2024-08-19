package service.comparators;

import java.util.Comparator;

public interface Comparators<T> {
    Comparator<T> getComparator();
}
