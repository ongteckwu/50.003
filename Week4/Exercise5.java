package Week4;

/**
 * Created by ongteckwu on 14/2/17.
 */
public class Exercise5 {
    public static int normalize(int a) {
        if (a == 0) return 0;
        if (a == 100000) return 10;
        return a/Math.abs(a);
    }
}
