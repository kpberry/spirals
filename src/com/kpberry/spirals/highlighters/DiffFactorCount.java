package com.kpberry.spirals.highlighters;

import com.kpberry.math.preprocessors.InitializeFactorCounts;
import com.kpberry.math.preprocessors.InitializePrimeFactorCounts;
import com.kpberry.math.preprocessors.Preprocessor;

import static com.kpberry.math.numeric.Primes.factorCount;
import static com.kpberry.math.numeric.Primes.primeFactorCount;

/**
 * Created by Kevin on 6/26/2017 for Spirals for Spirals for Spirals.
 */
public class DiffFactorCount implements Highlighter {
    @Override
    public Double apply(Integer integer) {
        return (double) factorCount(integer) * ((double) primeFactorCount(integer) + 1);
    }

    @Override
    public Preprocessor getPreprocessor() {
        return new Preprocessor() {
            @Override
            public void accept(Integer integer) {
                new InitializeFactorCounts().accept(integer);
                new InitializePrimeFactorCounts().accept(integer);
            }
        };
    }

    @Override
    public String toString() {
        return "Diff Factor Count";
    }
}
