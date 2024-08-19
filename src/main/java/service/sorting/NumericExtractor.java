package service.sorting;

@FunctionalInterface
public interface NumericExtractor<T> {
    int getNumericValue(T item);
}