package Week5;

import org.ejml.data.DenseMatrix64F;
import org.ejml.simple.SimpleMatrix;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


public class MatrixThreadArray {
    public static void main (String[] args) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("matrix_threads_runtime.txt");

            BufferedWriter bw = new BufferedWriter(fw);
            int n = 10;
            char[] chars = new char[n];
            Arrays.fill(chars, ' ');
            String blanks = new String(chars);
            System.out.print(blanks);
            bw.write(blanks);
            for (int numberOfThreads = 1; numberOfThreads <= 10; numberOfThreads++) {
                System.out.printf("%-9d", numberOfThreads);
                bw.write(String.format("%-9d", numberOfThreads));
            }
            System.out.println();
            bw.write("\n");

            for (int i = 1; i <= 1000; i++) {
                System.out.printf("%-10s", i + "*" + i);
                bw.write(String.format("%-10s", i + "*" + i));
                for (int numberOfThreads = 1; numberOfThreads <= 10; numberOfThreads++) {
                    if (numberOfThreads > i)
                        continue;
                    // get width
                    int matrixWidth = getWidth(i, numberOfThreads);
                    int remainderWidth = i % matrixWidth;
                    // generate threads
                    long startTime = System.currentTimeMillis();
                    MatrixMulThread[] threadArray = new MatrixMulThread[numberOfThreads];
                    for (int j = 0; j < numberOfThreads; j++) {
                        int width = (j != numberOfThreads - 1) ? remainderWidth : matrixWidth;
                        DenseMatrix64F A = new DenseMatrix64F(width, i);
                        DenseMatrix64F B = new DenseMatrix64F(i, i);

                        threadArray[j] = new MatrixMulThread(A, B);
                        threadArray[j].start();
                    }

                    try {
                        for (int j = 0; j < numberOfThreads; j++) {
                            threadArray[j].join();
                        }
                        long endTime = System.currentTimeMillis();
                        double totalTime = (endTime - startTime) / 1000.0;
                        bw.write(String.format("%.5fs ", totalTime));
                        System.out.printf("%.5fs ", totalTime);
                    } catch (InterruptedException e) {
                        System.out.println("A thread didn't finish!");
                    }
                }
                System.out.println();
                bw.write("\n");

            }

            if (bw != null)
                bw.close();

            if (fw != null)
                fw.close();
        } catch (IOException e) {
            System.out.println("File error. Exiting...");
            System.exit(0);
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

    private static int getWidth(int num, int dem) {
        // width of split matrices
        // e.g. 19/7 = 3*6 + 1*1
        //      18/7 = 2*6 + 6*1
        int d = (int) Math.ceil(num / (double) dem);
        if (d*dem >= (num-1)) return d;
        return d-1;
    }
}

class MatrixMulThread extends Thread {
    private SimpleMatrix A;
    private SimpleMatrix B;
    private SimpleMatrix out;

    public MatrixMulThread(DenseMatrix64F A, DenseMatrix64F B) {
        this.A = new SimpleMatrix(A);
        this.B = new SimpleMatrix(B);
    }

    public void run () {
        out = A.mult(B);
    }

    public DenseMatrix64F getOut() {
        return out.getMatrix();
    }
}
