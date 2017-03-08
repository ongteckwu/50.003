package Week5;

public class MatrixThread {
	public static void main (String[] args) {
		int[][] A = {new int[]{2, 3, 2}, new int[]{1, 3, 1}};
        int[][] B = {new int[]{1,1,1}, new int[]{1,1,1}, new int[] {1,1,1}};
        int[][] out1;
        int[][] out2;
        int[][] out = new int[A.length][B[0].length];
		
		Thread2 thread2 = new Thread2(A, B);
		Thread1 thread1 = new Thread1(A, B);
		thread1.start();
		thread2.start();
		
		try {
			thread1.join();
			thread2.join();
			out1 = thread1.getOut();
            out2 = thread2.getOut();
            for (int i = 0; i < A.length/2; i++) {
                out[i] = out1[i];
            }
            int addIndex = A.length/2;
            for (int i = 0; i < (A.length - A.length/2); i++) {
                out[i+addIndex] = out2[i];
            }

			PrintMatrix(out);
		}
		catch (InterruptedException e) {
			System.out.println("A thread didn't finish!");
		}
	}
	
    public static void PrintMatrix (int[][] toprint) {
    	//assume that toprint is a square matrix
    	//assume that toprint is not []
    	for (int i = 0; i < toprint.length; i++) {
        	for (int j = 0; j < toprint[0].length; j++) {
        		System.out.print(toprint[i][j] + "\t");
        	}
    		System.out.println();
    	}
    }
}

class Thread1 extends Thread {
	private int[][] A;
	private int[][] B;
    private int[][] out;

    public Thread1(int[][] a, int[][] b) {
        A = a;
        B = b;
        out = new int[A.length/2][B[0].length];
    }

    public void run () {
		for (int i = 0; i < A.length/2; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < B.length; k++) {
                    out[i][j] += A[i][k] * B[k][j];
                }
            }
        }
	}
	
	public int[][] getOut() {
		return out;
	}
}

class Thread2 extends Thread {
    private int[][] A;
    private int[][] B;
    private int[][] out;

    public Thread2(int[][] a, int[][] b) {
        A = a;
        B = b;
        out = new int[A.length - A.length/2][B[0].length];
    }

    public void run () {
        int addIndex = A.length/2;
        for (int i = 0; i < (A.length - A.length/2); i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < B.length; k++) {
                    out[i][j] += A[i+addIndex][k] * B[k][j];
                }
            }
        }
    }

    public int[][] getOut() {
        return out;
    }
}