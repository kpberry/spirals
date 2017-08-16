package math.inclusion_criteria;

import math.preprocessors.InitializeFactorCounts;
import math.preprocessors.Preprocessor;

import static math.numeric.Primes.factorCount;

/**
 * Created by Kevin on 6/11/2017 for Spirals.
 * <p>
 * Criterion which filters by whether or not the natural logarithm of a number
 * is less than its factor count.
 */
public class LogN_LT_FC implements InclusionCriterion {
    @Override
    public boolean test(Integer n) {
        return Math.log(n) < factorCount(n);
    }

    @Override
    public String toString() {
        return "ln(n) < FactorCount(n)";
    }

    @Override
    public Preprocessor getPreprocessor() {
        return new InitializeFactorCounts();
    }
}
