package com.kpberry.math;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 5/21/2017 for Spirals for Spirals.
 *
 */
public class Primes {
    private static int[] factorCounts = new int[0];
    private static int[] primeFactorCounts = new int[0];
    private static List<Integer> primes = new ArrayList<>();

    public static boolean isPrime(int n) {
        if (n >= factorCounts.length) {
            updateFactorCounts(n + 1);
        }
        return n > 0 && factorCounts[n] == 2;
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
        for (int i = 2; i < upper + 1; i++) {
            for (int j = i; j < upper + 1; j += i) {
                factorCounts[j]++;
            }
            factorCounts[i]++;
        }
        factorCounts[0] = 0;
        factorCounts[1] = 1;
    }

    public static void updatePrimeFactorCounts(int upper) {
        if (upper < primeFactorCounts.length) {
            return;
        }

        primeFactorCounts = new int[upper + 1];
        for (int i = 2; i < upper + 1; i++) {
            if (primeFactorCounts[i] == 0) {
                primes.add(i);
                for (int j = i; j < upper + 1; j += i) {
                    primeFactorCounts[j]++;
                }
                primeFactorCounts[i]++;
            }
        }
        primeFactorCounts[0] = 0;
        primeFactorCounts[1] = 1;
    }

    public static List<Integer> getPrimes() {
        return primes;
    }

    public static void main(String[] args) {
        updatePrimeFactorCounts(1000);
        System.out.println(primes);
    }

    public static int primeFactorCount(int n) {
        if (n >= primeFactorCounts.length) {
            updatePrimeFactorCounts(n + 1);
        }
        return primeFactorCounts[n];
    }
}
