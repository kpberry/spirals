package math.numeric;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Kevin on 5/21/2017 for Spirals.
 * <p>
 * Computes the number of factors that numbers have, as well as whether or not
 * they are prime.
 */
public class Primes {
    private static final Set<Integer> primes = new HashSet<>();
    private static final DoubleProperty progress = new SimpleDoubleProperty(0);
    private static int[] factorCounts = new int[0];
    private static int[] primeFactorCounts = new int[0];

    /**
     * Returns whether or not a number is prime
     *
     * @param n the number to test
     * @return whether or not n is prime
     */
    public static boolean isPrime(int n) {
        return primes.contains(n);
    }

    /**
     * Returns the number of factors a number has (not including 1)
     * @param n the number to test
     * @return the number of factors n has
     */
    public static int factorCount(int n) {
        if (n >= factorCounts.length) {
            updateFactorCounts(n + 1);
        }
        return factorCounts[n];
    }

    /**
     * Updates the factor counts for all numbers up to and including n,
     * caching them locally.
     *
     * @param upper the number up to which values will be cached
     */
    public static synchronized void updateFactorCounts(int upper) {
        if (upper < factorCounts.length) {
            return;
        }

        factorCounts = new int[upper + 1];
        for (int i = 2; i < (upper + 1); i++) {
            for (int j = i; j < (upper + 1); j += i) {
                factorCounts[j]++;
            }
            factorCounts[i]++;

            if (factorCounts[i] == 2) {
                primes.add(i);
            }
        }
        factorCounts[0] = 0;
        factorCounts[1] = 1;
    }

    /**
     * Updates the prime factor counts for all numbers up to and including n,
     * caching them locally.
     *
     * @param upper the number up to which values will be cached
     */
    public static synchronized void updatePrimeFactorCounts(int upper) {
        if (upper < primeFactorCounts.length) {
            return;
        }

        primeFactorCounts = new int[upper + 1];
        primeFactorCounts[0] = 0;
        primeFactorCounts[1] = 0;
        primeFactorCounts[2] = 0;
        for (int i = 2; i < (upper + 1); i++) {
            if (primeFactorCounts[i] == 0) {
                primes.add(i);
                for (int j = i; j < (upper + 1); j += i) {
                    primeFactorCounts[j]++;
                }
            }
        }
    }

    /**
     * Returns all of the primes already calculated
     *
     * @return all of the primes already calculated
     */
    public static List<Integer> getCalculatedPrimes() {
        List<Integer> result = new ArrayList<>(primes);
        result.sort(Comparator.comparingInt(a -> a));
        return result;
    }

    /**
     * Returns the number of prime factors of a number
     * @param n the number to test
     * @return the number of prime factors of n
     */
    public static int primeFactorCount(int n) {
        if (n >= primeFactorCounts.length) {
            updatePrimeFactorCounts(n + 1);
        }
        return primeFactorCounts[n];
    }

    /**
     * Returns the progress for updating the current set of values.
     * @return the current progress
     */
    public static DoubleProperty progressProperty() {
        return progress;
    }
}
