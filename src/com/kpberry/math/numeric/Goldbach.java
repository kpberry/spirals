package com.kpberry.math.numeric;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

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
    private static final DoubleProperty progress = new SimpleDoubleProperty(0);

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

    synchronized public static void updateGoldbachIndices(int n) {
        //TODO read goldbach indices from the file
        if (n >= goldbachIndices.length) {
            goldbachIndices = new int[n];

            Primes.updatePrimeFactorCounts(n);
            List<Integer> primes = Primes.getPrimes();

            progress.setValue(0);

            for (int i = 0; i < n; i++) {
                int index = 0;
                int cur = 0;
                while (cur < i && index < primes.size()) {
                    cur = primes.get(index++);
                    if (Primes.isPrime(i - cur)) {
                        goldbachIndices[i]++;
                    }
                }

                if ((i & 0x100) == 0) {
                    progress.setValue(i / n);
                }
            }
        }
    }

    public static void saveGoldbachIndices() {
        saveGoldbachIndices(Paths.get(outFile));
    }

    public static void saveGoldbachIndices(Path outFile) {
        //TODO make it so that values are only written if more values have been computed
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

    public static double getProgress() {
        return progress.get();
    }

    public static DoubleProperty progressProperty() {
        return progress;
    }
}
