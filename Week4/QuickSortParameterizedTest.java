package Week4;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import static org.junit.Assert.*;
import java.util.*;

@RunWith(Parameterized.class)

public class QuickSortParameterizedTest {
    public int[] sortArray, testArray;
    QuickSort qs;

    public QuickSortParameterizedTest (int[] sortArray, int[] testArray) {
        this.sortArray = sortArray;
        this.testArray = testArray;
    }

    @Before public void initialize() {
        qs = new QuickSort();
    }

    @Parameters public static Collection<Object[]> parameters() {
        return Arrays.asList (
                new Object [][]{
                        {new int[]{3, 2, 1}, new int[]{1, 2, 3}},
                        {new int[]{7, 8, 2, 1, 3}, new int[]{1, 2, 3, 7, 8}},
                        {new int[]{2, 2, 1, 1, 1}, new int[]{1, 1, 1, 2, 2}}
                });
    }

    @Test public void testArraySort() {
        qs.sort(sortArray);
        assertArrayEquals(sortArray, testArray);
    }

}
