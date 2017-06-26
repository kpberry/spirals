package com.kpberry.spirals.highlight_criteria;

import com.kpberry.spirals.Highlighter;
import com.kpberry.spirals.preprocessors.InitializeFactorCounts;

import java.util.function.Consumer;

import static com.kpberry.math.Primes.factorCount;

/**
 * Created by Kevin on 6/26/2017 for Spirals.
 */
public class FactorCount implements Highlighter {
    @Override
    public Double apply(Integer integer) {
        return (double) factorCount(integer);
    }

    @Override
    public Consumer<Integer> getPreprocessor() {
        return new InitializeFactorCounts();
    }

    @Override
    public String toString() {
        return "Factor Count";
    }
}
