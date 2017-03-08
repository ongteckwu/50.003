package Week6;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FactorThread extends Thread {
    public static class PrimeFactors {
        private BigInteger factor1;
        private BigInteger factor2;

        public BigInteger getFactor2() {
            return factor2;
        }

        public BigInteger getFactor1() {
            return factor1;
        }

        public PrimeFactors(BigInteger factor1, BigInteger factor2) {

            this.factor1 = factor1;
            this.factor2 = factor2;
        }
    }

    private BigInteger semiPrime;
    private BigInteger rangeLower;
    private BigInteger rangeHigher;
    private static PrimeFactors out;
    private static boolean isFound;


    public FactorThread(BigInteger semiPrime, BigInteger rangeLower, BigInteger rangeHigher) {
        this.semiPrime = semiPrime;
        this.rangeHigher = rangeHigher;
        this.rangeLower = rangeLower;
    }

    public void run() {
        for (BigInteger i = rangeLower; !i.equals(rangeHigher); i = i.add(BigInteger.ONE)) {
            if (Thread.interrupted()) {
                System.out.println("Thread interrupted");
                break;
            }
            if (semiPrime.mod(i).equals(BigInteger.ZERO)) {
                BigInteger j = semiPrime.divide(i);
                out = new PrimeFactors(i, j);
                isFound = true;
            }
        }
    }

    public static FactorThread.PrimeFactors primeIt(BigInteger semiPrime, int numberOfThreads) {
        BigInteger limit = sqrt(semiPrime);
        BigInteger gap = limit.subtract(BigInteger.valueOf(2));
        BigInteger width = getWidth(gap, numberOfThreads);
        BigInteger remainingWidth = gap.mod(width);
        BigInteger start = BigInteger.valueOf(2);
        BigInteger next = start.add(width);
        List<FactorThread> threadList = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            FactorThread ft = new FactorThread(semiPrime, start, next);
            ft.start();
            threadList.add(ft);
            if (i == numberOfThreads - 2) {
                start = next;
                next = next.add(remainingWidth);
            } else if (i < numberOfThreads - 2) {
                start = next;
                next = next.add(width);
            }
        }
        while (!isFound);
        for (FactorThread factorThread : threadList) {
            factorThread.interrupt();
        }
        //BigInteger.valueOf(2)

        return null;
    }

    public static BigInteger sqrt(BigInteger x) {
        BigInteger div = BigInteger.ZERO.setBit(x.bitLength()/2);
        BigInteger div2 = div;
        // Loop until we hit the same value twice in a row, or wind
        // up alternating.
        for(;;) {
            BigInteger y = div.add(x.divide(div)).shiftRight(1);
            if (y.equals(div) || y.equals(div2))
                return y;
            div2 = div;
            div = y;
        }
    }

    private static BigInteger getWidth(BigInteger num, int dem) {
        // width of split matrices
        // e.g. 19/7 = 3*6 + 1*1
        //      18/7 = 2*6 + 6*1
        //      21/7 = 3*7
        //      15/6 = 2*5 + 5*1
        BigInteger bdem = BigInteger.valueOf(dem);
        BigInteger d = num.divide(bdem).add(BigInteger.ONE);
        // d*(dem-1) < num
        if (d.multiply(bdem.subtract(BigInteger.ONE)).compareTo(num) == -1) return d;
        return d.subtract(BigInteger.ONE);
    }

    public static void main(String[] args) {
        BigInteger bi = new BigInteger("4294967297");
        int numberOfThreads = 8;
        FactorThread.PrimeFactors primeFactors = FactorThread.primeIt(bi, numberOfThreads);
        System.out.println(out.getFactor1());
        System.out.println(out.getFactor2());
    }
}