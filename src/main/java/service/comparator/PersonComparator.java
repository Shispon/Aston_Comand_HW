package service.comparator;

import entity.PersonModel;

import java.util.Comparator;

public class PersonComparator implements Comparator<PersonModel> {

    @Override
    public int compare(PersonModel o1, PersonModel o2) {
        int result = o1.getLastName().compareTo(o2.getLastName());
        if (result != 0) return result;

        result = o1.getGender().compareTo(o2.getGender());
        if (result != 0) return result;
        return Integer.compare(o1.getAge(), o2.getAge());
    }
}
