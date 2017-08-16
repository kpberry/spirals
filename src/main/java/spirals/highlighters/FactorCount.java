package spirals.highlighters;

import math.preprocessors.InitializeFactorCounts;
import math.preprocessors.Preprocessor;

import static math.numeric.Primes.factorCount;

/**
 * Created by Kevin on 6/26/2017 for Spirals.
 */
public class FactorCount implements PreprocessedFn {
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
