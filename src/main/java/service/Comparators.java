package service;

import entity.AnimalModel;
import entity.BarrelModel;
import entity.PersonModel;

import java.util.Comparator;

public class Comparators {

    public static Comparator<AnimalModel> animalComparator() {
        return new Comparator<AnimalModel>() {
            @Override
            public int compare(AnimalModel o1, AnimalModel o2) {
                int result = o1.getSpecies().compareTo(o2.getSpecies());
                if (result != 0) return result;

                result = o1.getEyeColor().compareTo(o2.getEyeColor());
                if (result != 0) return result;

                return Boolean.compare(o1.isHasFur(), o2.isHasFur());
            }
        };
    }

    public static Comparator<BarrelModel> barrelComparator() {
        return new Comparator<BarrelModel>() {
            @Override
            public int compare(BarrelModel o1, BarrelModel o2) {
                int result = o1.getStoredMaterial().compareTo(o2.getStoredMaterial());
                if (result != 0) return result;

                result = o1.getMaterial().compareTo(o2.getMaterial());
                if (result != 0) return result;

                return Double.compare(o1.getVolume(), o2.getVolume());
            }
        };
    }

    public static Comparator<PersonModel> personComparator() {
        return new Comparator<PersonModel>() {
            @Override
            public int compare(PersonModel o1, PersonModel o2) {
                int result = o1.getLastName().compareTo(o2.getLastName());
                if (result != 0) return result;

                result = o1.getGender().compareTo(o2.getGender());
                if (result != 0) return result;

                return Integer.compare(o1.getAge(), o2.getAge());
            }
        };
    }
}

