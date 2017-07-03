package com.kpberry.math.numeric;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Kevin on 5/21/2017 for Spirals for Spirals.
 *
 */
public class Primes {
    private static final Set<Integer> primes = new HashSet<>();
    private static final DoubleProperty progress = new SimpleDoubleProperty(0);
    private static int[] factorCounts = new int[0];
    private static int[] primeFactorCounts = new int[0];

    public static boolean isPrime(int n) {
        return primes.contains(n);
    }

    public static int factorCount(int n) {
        if (n >= factorCounts.length) {
            updateFactorCounts(n + 1);
        }
        return factorCounts[n];
    }

    public static synchronized void updateFactorCounts(int upper) {
        if (upper < factorCounts.length) {
            return;
        }

        progress.setValue(0);

        factorCounts = new int[upper + 1];
        for (int i = 2; i < (upper + 1); i++) {
            for (int j = i; j < (upper + 1); j += i) {
                factorCounts[j]++;
            }
            factorCounts[i]++;

            if (factorCounts[i] == 2) {
                primes.add(i);
            }

            if ((i & 0xFF) == 0) {
                progress.setValue(i / (upper + 1));
            }
        }
        factorCounts[0] = 0;
        factorCounts[1] = 1;
    }

    public static synchronized void updatePrimeFactorCounts(int upper) {
        if (upper < primeFactorCounts.length) {
            return;
        }

        progress.setValue(0);

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

            if ((i & 0xFF) == 0) {
                progress.setValue(i / (upper + 1));
            }
        }
    }

    public static List<Integer> getPrimes() {
        List<Integer> result = new ArrayList<>(primes);
        result.sort(Comparator.comparingInt(a -> a));
        return result;
    }

    public static int primeFactorCount(int n) {
        if (n >= primeFactorCounts.length) {
            updatePrimeFactorCounts(n + 1);
        }
        return primeFactorCounts[n];
    }

    public static double getProgress() {
        return progress.get();
    }

    public static DoubleProperty progressProperty() {
        return progress;
    }
}
