package Week6;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class FactorizerUser {
	public static void main (String[] args) {
		CachedFactorizer factorizer = new CachedFactorizer ();

		Thread tr1 = new Thread (new MyRunnable(factorizer));
		Thread tr2 = new Thread (new MyRunnable(factorizer));
		tr1.start();
		tr2.start();
		
		try {
			tr1.join();
			tr2.join();
			System.out.println(factorizer.getCacheHitRatio());
		}
		catch (Exception e) {
			
		}
	}
}

class MyRunnable implements Runnable {
	private CachedFactorizer factorizer;
	
	public MyRunnable (CachedFactorizer factorizer) {
		this.factorizer = factorizer; 
	}
	
	public void run () {
		Random random = new Random ();
		factorizer.service(random.nextInt(100));
	}
}

public class CachedFactorizer {
	private int lastNumber;
	private List<Integer> lastFactors;
	private long hits;
	private long cacheHits;
	private Object[] lockBrothers = new Object[2];
	
	public long getHits () {
		return hits;
	}

	public CachedFactorizer() {
		for (int i=0; i < lockBrothers.length; i++)
			lockBrothers[i] = new Object();
	}
	
	public double getCacheHitRatio () {
		return (double) cacheHits/ (double) hits;
	}
	
	public List<Integer> service (int input) {
		List<Integer> factors = null;
		synchronized(lockBrothers[0]) {
			++hits;
			if (input == lastNumber) {
				++cacheHits;
			}
		}

		if (input == lastNumber) {
			factors = new ArrayList<Integer>(lastFactors);
		}

		if (factors == null) {
			factors = factor(input);
			synchronized (lockBrothers[1]) {
				lastNumber = input;
				lastFactors = new ArrayList<Integer>(factors);
			}
		}
		return factors;
	}
	
	public List<Integer> factor(int n) {		
		List<Integer> factors = new ArrayList<Integer>();
		for (int i = 2; i <= n; i++) {
			while (n % i == 0) {
		        factors.add(i);
		        n /= i;
		    }
		}
		
		return factors;
	}
}