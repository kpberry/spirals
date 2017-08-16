package spirals.highlighters;

import math.preprocessors.InitializePrimeFactorCounts;
import math.preprocessors.Preprocessor;

import static math.numeric.Primes.primeFactorCount;

/**
 * Created by Kevin on 6/26/2017 for Spirals.
 * Class for preprocessing and getting the prime factor counts of numbers
 */
public class PrimeFactorCount implements PreprocessedFn {
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
