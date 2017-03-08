package Week6;

import java.util.Scanner;

/**
 * Created by ongteckwu on 8/3/17.
 */
public class SleepCounter extends Thread {

    public void run() {
        int i = 0;
        try {
            while (true) {
                System.out.println(i);
                sleep(1000);
                i++;
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted. exiting...");
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        SleepCounter sc = new SleepCounter();
        sc.start();
        if (in.nextInt() == 0) {
            sc.interrupt();
        }
    }
}
