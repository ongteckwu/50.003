package Week4;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by ongteckwu on 19/2/17.
 */
@RunWith(Parameterized.class)

public class FindMaxUsingSortingTest {
    private int[] testArray;
    private int[] sortedArray;
    private int maxInt;

    public FindMaxUsingSortingTest (int[] testArray, int[] sortedArray, int maxInt) {
        this.testArray = testArray;
        this.sortedArray = sortedArray;
        this.maxInt = maxInt;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList (
                new Object [][]{
                        {new int[]{3, 2, 1}, new int[]{1, 2, 3}, 3},
                        {new int[]{7, 8, 2, 1, 3}, new int[]{1, 2, 3, 7, 8}, 8},
                        {new int[]{2, 2, 1, 1, 1}, new int[]{1, 1, 1, 2, 2}, 2}
                });
    }
    @Test
    public void testMaxWithMockSort() {
        Mockery context = new JUnit4Mockery();

        final Sorter sorter = context.mock(Sorter.class);

        context.checking(new Expectations() {{
            oneOf(sorter).sort(testArray);
            will(returnValue(sortedArray));
        }});

        assertEquals(FindMaxUsingSorting.findmax(testArray, sorter), maxInt);

        context.assertIsSatisfied();
    }
}
