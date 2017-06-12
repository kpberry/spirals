package com.kpberry.spirals.preprocessors;

import java.util.function.Consumer;

import static com.kpberry.math.Primes.updateFactorCounts;

/**
 * Created by Kevin on 6/11/2017 for Spirals.
 */
public class InitializeFactorCounts implements Consumer<Integer> {
    @Override
    public void accept(Integer spiralLength) {
        updateFactorCounts(spiralLength);
    }

    @Override
    public String toString() {
        return "Initialize Factor Counts";
    }
}
