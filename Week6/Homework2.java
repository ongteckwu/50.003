package Week6;

/**
 * Created by ongteckwu on 8/3/17.
 */
public class Homework2 extends Thread {
    private StringBuffer sb;
    public Homework2(StringBuffer sb) {
        this.sb = sb;
    }

    public void run() {
        synchronized (sb) {
            String s = sb.toString();
            for(int i = 0; i < 100; i++) {
                System.out.println(s);
            }
            sb.setCharAt(0, (char) ((int) sb.charAt(0) + 1));
        }
    }

    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer("A");
        Homework2 t1 = new Homework2(sb);
        Homework2 t2 = new Homework2(sb);
        Homework2 t3 = new Homework2(sb);
        t1.start();
        t2.start();
        t3.start();

        /*
        StringBuffer is used ahead of String because String is immutable
         and hence the variable holding the String cannot be modified while synchronized
         since synchronization is on the object and modification involves creating a new object
         */
    }
}
