package math;

/**
 * Created by Kevin on 5/21/2017 for Spirals for Spirals.
 *
 */
public class Primes {
    private static int[] factorCounts = new int[0];

    public static boolean isPrime(int n) {
        if (n >= factorCounts.length) {
            updateFactorCounts(n + 1);
        }
        return factorCounts[n] == 2;
    }

    public static int factorCount(int n) {
        if (n >= factorCounts.length) {
            updateFactorCounts(n + 1);
        }
        return factorCounts[n];
    }

    public static void updateFactorCounts(int upper) {
        if (upper < factorCounts.length) {
            return;
        }

        factorCounts = new int[upper + 1];
        for (int i = 1; i < upper + 1; i++) {
            for (int j = i; j < upper + 1; j += i) {
                factorCounts[j]++;
            }
        }
        factorCounts[0] = 0;
        factorCounts[1] = 1;
    }
}
