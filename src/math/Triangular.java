package math;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kevin on 5/21/2017 for Spirals.
 */
public class Triangular {
    private static final Set<Integer> triangleNumbers = new HashSet<>();
    private static int maxRequested = 0;

    public static void updateTriangleNumbers(int n) {
        if (n > maxRequested) {
            maxRequested = n;
        } else {
            return;
        }
        //TODO could start somewhere around maxRequested for efficiency
        int i = 0, cur = 0;
        while (cur < n) {
            cur = i * (i + 1) / 2;
            triangleNumbers.add(cur);
            i++;
        }
    }

    public static boolean isTriangular(int n) {
        if (n > maxRequested) {
            updateTriangleNumbers(n);
        }
        return triangleNumbers.contains(n);
    }
}
