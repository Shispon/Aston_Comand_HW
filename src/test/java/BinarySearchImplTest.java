import entity.AnimalModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.BinarySearchService;
import service.comparators.AnimalComparator;
import service.sorting.InsertionSort;
import service.sorting.SortingService;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinarySearchImplTest {

    private Comparator<AnimalModel> comparator;
    private List<AnimalModel> sortedList;

    @Before
    public void setUp() {
        AnimalComparator animalComparator = new AnimalComparator();
        comparator = animalComparator.getComparator();
        List<AnimalModel> list = new ArrayList<>();
        list.add(new AnimalModel("dog", "brown", true));
        list.add(new AnimalModel("cat", "green", true));
        list.add(new AnimalModel("cat", "gray", false));
        list.add(new AnimalModel("spider", "black", false));
        list.add(new AnimalModel("bird", "blue", false));

        SortingService<AnimalModel> sortingService = new SortingService<>(new InsertionSort<>());
        sortingService.sort(list, comparator);

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
