package com.kpberry.spirals.preprocessors;

import com.kpberry.math.Goldbach;

import java.io.IOException;
import java.util.function.Consumer;

import static com.kpberry.math.Primes.updateFactorCounts;

/**
 * Created by Kevin on 6/11/2017 for Spirals.
 */
public class InitializeGoldbachCounts implements Consumer<Integer> {
    @Override
    public void accept(Integer spiralLength) {
        updateFactorCounts(spiralLength);
        try {
            Goldbach.readGoldbachIndices();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Goldbach.updateGoldbachIndices(spiralLength);
    }

    @Override
    public String toString() {
        return "Initialize Goldbach Counts";
    }
}
