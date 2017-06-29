package com.kpberry.math.numeric;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Kevin on 5/21/2017 for Spirals for Spirals.
 *
 */
public class Triangular {
    private static final Set<Integer> triangleNumbers = new HashSet<>();
    private static int maxRequested = 0;
    private static final DoubleProperty progress = new SimpleDoubleProperty();

    synchronized public static void updateTriangleNumbers(int n) {
        if (n > maxRequested) {
            maxRequested = n;
        } else {
            return;
        }
        //TODO could start somewhere around maxRequested for efficiency

        progress.setValue(0);

        int i = 0, cur = 0;
        while (cur < n) {
            cur = i * (i + 1) / 2;
            triangleNumbers.add(cur);
            i++;

            if ((i & 0x100) == 0) {
                progress.setValue(Math.sqrt(cur / n));
            }
        }
    }

    public static boolean isTriangular(int n) {
        if (n > maxRequested) {
            updateTriangleNumbers(n);
        }
        return triangleNumbers.contains(n);
    }

    public static double getProgress() {
        return progress.get();
    }

    public static DoubleProperty progressProperty() {
        return progress;
    }
}
