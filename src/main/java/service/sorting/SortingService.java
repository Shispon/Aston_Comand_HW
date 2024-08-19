package service.sorting;

import java.util.Comparator;
import java.util.List;

public class SortingService<T> {

    private SortingLogic<T> sortingLogic;

    public SortingService(SortingLogic<T> sortingLogic) {
        this.sortingLogic = sortingLogic;
    }

    public void sort(List<T> list, Comparator<? super T> comparator) {
        sortingLogic.sort(list, comparator);
    }
}
