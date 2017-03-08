package Week6;
public class YieldExample {
    public static final int rounds = 5000;
    public static final int sleepingTime = 50;

    public static void main(String[] args) throws Exception {
        LeftThread2 left = new LeftThread2();
        RightThread2 right = new RightThread2();
        left.start();
        right.start();

        try {
            left.join();
            right.join();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class LeftThread2 extends Thread {
    public void run() {
        for (int i = 0; i < SleepExample.rounds; i++) {
            System.out.print(1);
            System.out.print(2);
            System.out.print(3);

            Thread.yield();

            System.out.println(5);
        }
    }
}

class RightThread2 extends Thread {
    public void run() {
        Thread.yield();

        for (int i = 0; i <  SleepExample.rounds; i++) {
            System.out.print(4);

            Thread.yield();
        }
    }
}
