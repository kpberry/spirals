package math.numeric;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by Kevin on 5/21/2017 for Spirals.
 * <p>
 * Efficiently computes triangle numbers.
 */
public class Triangular {
    private static final Collection<Integer> triangleNumbers = new HashSet<>();
    private static final DoubleProperty progress = new SimpleDoubleProperty();
    private static int maxRequested = 0;

    /**
     * Calculates and caches the list of triangle numbers up to n
     *
     * @param n the number up to which triangle numbers will be calculated
     */
    public static synchronized void updateTriangleNumbers(int n) {
        if (n > maxRequested) {
            maxRequested = n;
        } else {
            return;
        }

        progress.setValue(0);

        int i = 0;
        int cur = 0;
        while (cur < n) {
            cur = (i * (i + 1)) / 2;
            triangleNumbers.add(cur);
            i++;

            if ((i & 0xFF) == 0) {
                progress.setValue(Math.sqrt(cur / n));
            }
        }
    }

    /**
     * Returns whether or not a number is triangular
     *
     * @param n the number to test
     * @return whether or not n is triangular
     */
    public static boolean isTriangular(int n) {
        if (n > maxRequested) {
            updateTriangleNumbers(n);
        }
        return triangleNumbers.contains(n);
    }

    /**
     * Returns the progress for updating the current set of values.
     *
     * @return the current progress
     */
    public static DoubleProperty progressProperty() {
        return progress;
    }
}
