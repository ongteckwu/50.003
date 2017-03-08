package Week4;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by ongteckwu on 14/2/17.
 */
@RunWith(Parameterized.class)
public class Exercise5Test {
    private Exercise5 ex = new Exercise5();
    private int a, b;

    public Exercise5Test(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList (new Object [][] {
                {0, 0},
                {2, 1},
                {3, 1},
                {10, 1},
                {-10, -1},
                {-20, -1},
                {-100, -1}});
    }

    @Test
    public void checkCorrectnessTest() {
        assertEquals(ex.normalize(a), b);
    }

}
