package Week4;

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
public class RussianBlackBoxTest{
    public int a;
    public int b;
    public int equals;

    public RussianBlackBoxTest (int a, int b, int equals) {
        this.a = a;
        this.b = b;
        this.equals = equals;
    }
    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList (new Object [][] {
                // 0 testing
                {0, 0, 0},
                {0, 10, 0},
                {0, -10, 0},
                // positive number testing
                {5, 5, 25},
                {10, 10, 100},
                {1, 1, 1},
                {100, 100, 10000}});
    }

    @Test
    public void testBB() {
        assertEquals(equals, Russian.multiply(a, b));
    }
}