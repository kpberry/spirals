package com.kpberry.spirals.highlighters;

import com.kpberry.math.preprocessors.InitializePrimeFactorCounts;
import com.kpberry.math.preprocessors.Preprocessor;

import static com.kpberry.math.numeric.Primes.primeFactorCount;

/**
 * Created by Kevin on 6/26/2017 for Spirals for Spirals for Spirals.
 */
public class PrimeFactorCount implements Highlighter {
    @Override
    public Double apply(Integer integer) {
        return (double) primeFactorCount(integer);
    }

    @Override
    public Preprocessor getPreprocessor() {
        return new InitializePrimeFactorCounts();
    }

    @Override
    public String toString() {
        return "Prime Factor Count";
    }
}
