package service.comparators;

import entity.BarrelModel;

import java.util.Comparator;

public class BarrelComparator implements Comparators<BarrelModel> {

    @Override
    public Comparator<BarrelModel> getComparator() {
        return (o1, o2) -> {
            int result = o1.getStoredMaterial().compareTo(o2.getStoredMaterial());
            if (result != 0) return result;

            result = o1.getMaterial().compareTo(o2.getMaterial());
            if (result != 0) return result;

            return Double.compare(o1.getVolume(), o2.getVolume());
        };
    }
}
