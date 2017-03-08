package Week6;

import java.util.Arrays;

/**
 * Created by ongteckwu on 27/2/17.
 */
class MultiThreadSearch extends Thread{

    private int[] array;
    private int searchElement;
    private boolean backwards;
    public static boolean isFound = false;

    public MultiThreadSearch(int[] array, int searchElement) {
        this.array = array;
        this.searchElement = searchElement;
        this.backwards = false;
    }

    public MultiThreadSearch (int[] array, int searchElement, boolean backwards) {
        this.array = array;
        this.searchElement = searchElement;
        this.backwards = backwards;
    }

    public void run(){
        // with backwards
        for (int i = 0; i < array.length; i++) {
            if (Thread.interrupted()) {
                System.out.println("Thread interrupted");
                break;
            }
            int index = (backwards) ? array.length - i - 1 : i;
            int i1 = array[i];
            if (i1 == searchElement) {
                isFound = true;
                break;
            }
        }
    }

    public static void main(String args[]){

        int[] array = new int[100000];
        Arrays.fill(array, 4);
        array[99999] = 5;
        MultiThreadSearch t1=new MultiThreadSearch(array, 5);
        MultiThreadSearch t2=new MultiThreadSearch(array, 5, true);
        t1.start();
        t2.start();
        while (!isFound);
        System.out.println("Is found!");
        t1.interrupt();
        t2.interrupt();
    }
}