package Week4;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Created by ongteckwu on 19/2/17.
 */
public class RussianFaultBasedTesting {

    @Test public void testDoubleThrowException() {
        assertEquals(0, Russian.multiply(-30, -30));
    }
}
