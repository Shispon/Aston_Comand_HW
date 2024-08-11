package service.comparator;

import entity.BarrelModel;

import java.util.Comparator;

public class BarrelComparator implements Comparator<BarrelModel> {

    @Override
    public int compare(BarrelModel o1, BarrelModel o2) {
        int result = o1.getStoredMaterial().compareTo(o2.getStoredMaterial());
        if (result != 0) return result;

        result = o1.getMaterial().compareTo(o2.getMaterial());
        if (result != 0) return result;
        return Double.compare(o1.getVolume(), o2.getVolume());
//        return o1.getVolume() > o2.getVolume() ? 1 : (o1.getVolume() == o2.getVolume()) ? 1 : -1;
    }
}
