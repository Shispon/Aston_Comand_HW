package service.binary_search;

import entity.AnimalModel;
import service.comparator.AnimalComparator;

import java.util.List;

public class AnimalBinarySearch {

    private final AnimalComparator comparator;

    public AnimalBinarySearch() {
        this.comparator = new AnimalComparator();
    }

    public AnimalModel binarySearch(List<AnimalModel> sortedList, AnimalModel key) {
        int mid;
        int min = 0;
        int max = sortedList.size() - 1;
        int resultOfCompare;
        while (min <= max) {
            mid = min + (max - min) / 2;
            AnimalModel m = sortedList.get(mid);
            resultOfCompare = comparator.compare(key, sortedList.get(mid));
            if (resultOfCompare == 0) {
                return key;
            } else if (resultOfCompare == 1) {
                min = mid + 1;
            } else if (resultOfCompare == -1) {
                max = mid - 1;
            }
        }
        return null;
    }
}
