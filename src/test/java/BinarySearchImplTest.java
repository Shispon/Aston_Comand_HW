import entity.AnimalModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.BinarySearchService;
import service.Comparators;
import service.SortingService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinarySearchImplTest {

    private Comparator<AnimalModel> comparator;
    private List<AnimalModel> sortedList;

    @Before
    public void setUp() {
        comparator = Comparators.animalComparator();
        List<AnimalModel> list = new ArrayList<>();
        list.add(new AnimalModel("dog", "brown", true));
        list.add(new AnimalModel("cat", "green", true));
        list.add(new AnimalModel("cat", "gray", false));
        list.add(new AnimalModel("spider", "black", false));
        list.add(new AnimalModel("bird", "blue", false));
        SortingService.insertionSort(list, comparator);
        sortedList = list;
    }

    @Test
    public void binarySearchTest() {
        AnimalModel expected = new AnimalModel("cat", "gray", false);
        AnimalModel actual = BinarySearchService.search(sortedList, expected, comparator);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void binarySearchNotFoundTest() {
        AnimalModel notFound = new AnimalModel("elephant", "gray", true);
        AnimalModel actual = BinarySearchService.search(sortedList, notFound, comparator);
        Assert.assertNull(actual);
    }
}
