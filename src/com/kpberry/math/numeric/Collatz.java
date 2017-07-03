package com.kpberry.math.numeric;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kevin on 5/29/2017 for Spirals for Spirals.
 *
 */
public class Collatz {
    private static final Map<Integer, Integer> collatzLengths = new HashMap<>();
    private static final String outFile = "out/goldbach_indices.csv";
    private static final DoubleProperty progress = new SimpleDoubleProperty(0);

    /**
     * Returns the number of ways a number n can be written as the sum of two
     * primes
     *
     * @param n the number to check
     * @return the number of ways n can be written as the sum of two primes
     */
    public static int collatzLength(int n) {
        if (n >= collatzLengths.size()) {
            updateCollatzLengths(n);
        }
        return collatzLengths.get(n);
    }

    public static synchronized void updateCollatzLengths(int n) {
        //TODO read goldbach indices from the file
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

                if ((i & 0x100) == 0) {
                    progress.setValue(i / n);
                }
            }
        }
    }

    public static void saveCollatzLengths() {
        saveCollatzLengths(Paths.get(outFile));
    }

    public static void saveCollatzLengths(Path outFile) {
        //TODO make it so that values are only written if more values have been computed
        try (BufferedWriter bw = Files.newBufferedWriter(outFile)) {
            PrintWriter out = new PrintWriter(bw);
            out.println(collatzLengths.size());
            for (int goldbachIndex : collatzLengths.keySet()) {
                out.println(goldbachIndex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public static void readCollatzLengths() throws IOException {
        readCollatzLengths(Paths.get(outFile));
    }

    public static void readCollatzLengths(Path inFile) throws IOException {
        try (BufferedReader br = Files.newBufferedReader(inFile)) {
            Scanner sc = new Scanner(br);
            if (sc.hasNextLine()) {
                collatzLengths = new int[sc.nextInt()];
                int i = 0;
                while (sc.hasNextInt()) {
                    collatzLengths[i++] = sc.nextInt();
                }
            }
        }
    }*/

    public static double getProgress() {
        return progress.get();
    }

    public static DoubleProperty progressProperty() {
        return progress;
    }
}
