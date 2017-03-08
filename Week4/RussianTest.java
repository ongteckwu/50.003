package Week4;

import junit.framework.JUnit4TestAdapter;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Suite;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Created by ongteckwu on 19/2/17.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses ({ RussianBlackBoxTest.class, RussianWhiteBoxTest.class, RussianFaultBasedTesting.class })  // Add test classes here.

public class RussianTest {
    // Execution begins at main().  In this test class, we will execute
    // a text test runner that will tell you if any of your tests fail.
    public static void main(String[] args) {
        JUnit4TestAdapter suite = new JUnit4TestAdapter(RussianTest.class);
        junit.textui.TestRunner.run(suite);

    }
}

