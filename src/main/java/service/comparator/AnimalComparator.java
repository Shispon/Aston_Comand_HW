package service.comparator;

import entity.AnimalModel;

import java.util.Comparator;

public class AnimalComparator implements Comparator<AnimalModel> {

    @Override
    public int compare(AnimalModel o1, AnimalModel o2) {
        int result = o1.getSpecies().compareTo(o2.getSpecies());
        if (result != 0) return result;

        result = o1.getEyeColor().compareTo(o2.getEyeColor());
        if (result != 0) return result;

        return Boolean.compare(o1.isHasFur(), o2.isHasFur());
    }
}
