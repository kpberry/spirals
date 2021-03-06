package spirals.highlighters;

import math.preprocessors.InitializeFactorCounts;
import math.preprocessors.InitializePrimeFactorCounts;
import math.preprocessors.Preprocessor;

import static math.numeric.Primes.factorCount;
import static math.numeric.Primes.primeFactorCount;

/**
 * Created by Kevin on 6/26/2017 for Spirals.
 * Class for preprocessing and getting the difference between the factor
 * count and the prime factor counts of numbers
 */
public class DiffFactorCount implements PreprocessedFn {
    @Override
    public Double apply(Integer integer) {
        return (double) factorCount(integer)
                - ((double) primeFactorCount(integer));
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
