package Week4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by ongteckwu on 19/2/17.
 */
public class RussianWhiteBoxTest {
    @Test public void testNSmallerThanZero() {
        assertEquals(0, Russian.multiply(100, -20));
        assertEquals(0, Russian.multiply(100, 0));
    }

    @Test public void testNOdd() {
        assertEquals(180, Russian.multiply(60, 3));
        assertEquals(50, Russian.multiply(10, 5));
    }

    @Test public void testBigMN() {
        assertEquals(15000, Russian.multiply(75, 200));
        assertEquals(20000, Russian.multiply(200, 100));
        assertEquals(7221, Russian.multiply(83, 87));
    }
}
