package Week4;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class FindMaxTest {
    int[] list;
    int[] anotherList;
    @Before
    public void initialize() {
        list = new int[]{10, 6, 3, 11, 4};
        anotherList = new int[]{};
    }

    // Pass
    @Test
    public void checkMax() {
        assertEquals(FindMax.max(list), 11);
    }

    // Failure
    @Test
    public void fakeCheckMax() {
        assertEquals(FindMax.max(list), 10);
    }

    // Error
    @Test
    public void checkErrorIfNoInt() {
        FindMax.max(anotherList);
    }
}
