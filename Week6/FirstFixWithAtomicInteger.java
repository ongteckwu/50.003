package Week6;

import java.util.concurrent.atomic.AtomicInteger;

public class FirstFixWithAtomicInteger {
	public static AtomicInteger count = new AtomicInteger(0);
	
	public static void main(String args[]){   	
		int numberofThreads = 100000;
		A[] threads = new A[numberofThreads];
	
		for (int i = 0; i < numberofThreads; i++) {
			threads[i] = new A();
			threads[i].start();
		}
		
		try {
			for (int i = 0; i < numberofThreads; i++) {
				threads[i].join();
			}
		} catch (InterruptedException e) {
			System.out.println("some thread is not finished");
		}
		
		System.out.print("The result is ... ");
		System.out.print("wait for it ... ");
		System.out.println(count.get());
	}
}

class A extends Thread {
	public void run () {
		FirstFixWithAtomicInteger.count.incrementAndGet();
	}	
}

