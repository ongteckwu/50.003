package Week5;

import java.math.BigInteger;

/**
 * Created by ongteckwu on 20/2/17.
 */
public class FactorPrime {
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
    public static PrimeFactors primeIt(BigInteger semiPrime) {
        BigInteger limit = sqrt(semiPrime);
        for (BigInteger i = BigInteger.valueOf(2); !i.equals(limit); i = i.add(BigInteger.ONE)) {
            if (semiPrime.mod(i).equals(BigInteger.ZERO)) {
                BigInteger j = semiPrime.divide(i);
                return new PrimeFactors(i, j);
            }
        }
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

    public static void main(String[] args) {
        BigInteger bi = new BigInteger("4294967297");
        PrimeFactors primeFactors = FactorPrime.primeIt(bi);
        System.out.println(primeFactors.getFactor1());
        System.out.println(primeFactors.getFactor2());
    }
}
