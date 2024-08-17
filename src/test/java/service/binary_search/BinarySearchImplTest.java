package service.binary_search;

import entity.AnimalModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.comparator.AnimalComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinarySearchImplTest {

    private BinarySearch<AnimalModel> binarySearch;
    private Comparator<AnimalModel> comparator;
    private List<AnimalModel> sortedList;

    @Before
    public void before() {
        binarySearch = new BinarySearchImpl<>();
        comparator = new AnimalComparator();
        List<AnimalModel> list = new ArrayList<>();
        list.add(new AnimalModel("dog", "brown", true));
        list.add(new AnimalModel("cat", "green", true));
        list.add(new AnimalModel("cat", "gray", false));
        list.add(new AnimalModel("spider", "black", false));
        list.add(new AnimalModel("bird", "blue", false));
        sortedList = null; // TODO: здесь вызвать метод сортировки
    }

    @Test
    public void binarySearchTest() {
        AnimalModel expected = sortedList.get(2);
        AnimalModel actual = binarySearch.binarySearch(sortedList, expected, comparator).get();
        Assert.assertEquals(expected, actual);
    }
}
