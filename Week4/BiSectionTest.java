package Week4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BiSectionTest {
	private BiSectionExample bi;

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Before 
	public void runBeforeEachTest()
	{
		bi = new BiSectionExample();
	}
	
	@Test
	public void test4MethodCoverage () {
		//System.out.print(bi.root(0.5, 100.3, 0.1));
		assert (bi.root(0.5, 100.3, 0.1) >= 100);
		//question: should we assert the returned value is the exact value we expect?
	}
	
	@Test 
	public void test4LoopCoverage1 () {//loop once
		assert(bi.root(0,100,80) > 50);
	}

	@Test
	public void test4LoopCoverage2 () {//loop once
		assert(bi.root(-100,100,-10) > -10);
	}

	@Test
	public void test4throw () {
		exception.expect(IllegalArgumentException.class);
		bi.root(100, 0, 10);
	}
}
