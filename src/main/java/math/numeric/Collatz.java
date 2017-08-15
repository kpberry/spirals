package math.numeric;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kevin on 5/29/2017 for Spirals.
 * <p>
 * Gets the lengths of collatz sequences.
 */
public class Collatz {
    private static final Map<Integer, Integer> collatzLengths = new HashMap<>();
    private static final DoubleProperty progress = new SimpleDoubleProperty(0);

    /**
     * Returns the length of the collatz sequence corresponding to a value n.
     * For instance, collatzLength(5) -> 5, since the collatz sequence from 5
     * to 1, which is 5 -> 16 -> 8 -> 4 -> 2 -> 1, has 5 transitions.
     *
     * @param n the number to check
     * @return the length of the collatz sequence corresponding to n
     */
    public static int collatzLength(int n) {
        if (n <= 0) {
            throw new ArithmeticException(
                    "Cannot find length of Collatz " +
                            "sequence for non-positive number"
            );
        }
        if (n >= collatzLengths.size()) {
            updateCollatzLengths(n);
        }
        return collatzLengths.get(n);
    }

    /**
     * Caches the lengths of the collatz sequences for each number less than
     * or equal to n. Uses dynamic programming for efficiency.
     *
     * @param n the highest number for which collatz sequence lengths will be
     *          cached.
     */
    public static synchronized void updateCollatzLengths(int n) {
        if (n <= 0) {
            throw new ArithmeticException(
                    "Cannot find length of Collatz " +
                            "sequences for non-positive numbers."
            );
        }
        if (n >= collatzLengths.size()) {
            progress.setValue(0);

            for (int i = collatzLengths.size(); i <= n; i++) {
                int k = i;

                int length = 0;
                while (k > 1) {
                    length++;
                    if ((k & 1) == 0) {
                        k /= 2;
                    } else {
                        k = (k * 3) + 1;
                    }
                    if (collatzLengths.containsKey(k)) {
                        length += collatzLengths.get(k);
                        k = 0;
                    }
                }

                collatzLengths.put(i, length);

                if ((i & 0xFF) == 0) {
                    progress.setValue(i / n);
                }
            }
        }
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
