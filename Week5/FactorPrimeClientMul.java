package Week5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by ongteckwu on 20/2/17.
 */
public class FactorPrimeClientMul {
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

    public static void main(String[] args) throws Exception {
        String hostName = "localhost";
        String hostIP = "localhost";
        //String hostName = "fe80::7517:c1af:b2bb:da73%4";
        int portNumber = 4321;

//        Socket echoSocket = new Socket(hostName, portNumber);
        Socket echoSocket = new Socket();
        SocketAddress sockaddr = new InetSocketAddress(hostIP, portNumber);
        echoSocket.connect(sockaddr, 100);
        PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(echoSocket.getInputStream()));

        do {
            BigInteger bi = new BigInteger(in.readLine());

            PrimeFactors primeFactors = FactorPrimeClientMul.primeIt(bi);
            if (primeFactors != null) {
                BigInteger f1 = primeFactors.getFactor1();
                BigInteger f2 = primeFactors.getFactor2();
                System.out.println(f1 + " " + f2);
                out.println(f1 + " " + f2);
                out.flush();
            }
            else {
                out.println("No factors bruh for");
                out.println(bi);
                out.flush();
            }
        } while (true);

//        echoSocket.close();
//        in.close();
//        out.close();
//        PrimeFactors primeFactors = FactorPrimeClient.primeIt(BigInteger.valueOf(4294967297L));

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
}

