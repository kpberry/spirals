package com.kpberry.math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Kevin on 5/29/2017 for Spirals.
 *
 */
public class Goldbach {
    private static int[] goldbachIndices = new int[0];
    private static String outFile = "out/goldbach_indices.csv";

    /**
     * Returns the number of ways a number n can be written as the sum of two
     * primes
     * @param n the number to check
     * @return the number of ways n can be written as the sum of two primes
     */
    public static int goldbachIndex(int n) {
        updateGoldbachIndices(n + 1);
        return goldbachIndices[n];
    }

    public static void updateGoldbachIndices(int n) {
        if (n >= goldbachIndices.length) {
            goldbachIndices = new int[n];

            Primes.updatePrimeFactorCounts(n);
            List<Integer> primes = Primes.getPrimes();


            for (int i = 0; i < n; i++) {
                int index = 0;
                int cur = 0;
                while (cur < i && index < primes.size()) {
                    cur = primes.get(index++);
                    if (Primes.isPrime(i - cur)) {
                        goldbachIndices[i]++;
                    }
                }
            }
        }
    }

    public static void saveGoldbachIndices() {
        saveGoldbachIndices(Paths.get(outFile));
    }

    public static void saveGoldbachIndices(Path outFile) {
        try (BufferedWriter bw = Files.newBufferedWriter(outFile)) {
            PrintWriter out = new PrintWriter(bw);
            out.println(goldbachIndices.length);
            for (int goldbachIndex : goldbachIndices) {
                out.println(goldbachIndex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readGoldbachIndices() throws IOException {
        readGoldbachIndices(Paths.get(outFile));
    }

    public static void readGoldbachIndices(Path inFile) throws IOException {
        try (BufferedReader br = Files.newBufferedReader(inFile)) {
            Scanner sc = new Scanner(br);
            if (sc.hasNextLine()) {
                goldbachIndices = new int[sc.nextInt()];
                int i = 0;
                while (sc.hasNextInt()) {
                    goldbachIndices[i++] = sc.nextInt();
                }
            }
        }
    }
}
