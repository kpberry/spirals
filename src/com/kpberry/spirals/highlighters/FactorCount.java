package com.kpberry.spirals.highlighters;

import com.kpberry.math.Preprocessor;
import com.kpberry.math.preprocessors.InitializeFactorCounts;
import com.kpberry.spirals.base.Highlighter;

import static com.kpberry.math.numeric.Primes.factorCount;

/**
 * Created by Kevin on 6/26/2017 for Spirals for Spirals.
 *
 */
public class FactorCount implements Highlighter {
    @Override
    public Double apply(Integer integer) {
        return (double) factorCount(integer);
    }

    @Override
    public Preprocessor getPreprocessor() {
        return new InitializeFactorCounts();
    }

    @Override
    public String toString() {
        return "Factor Count";
    }
}
