package com.kpberry.math.inclusion_criteria;

import com.kpberry.math.preprocessors.InitializeFactorCounts;
import com.kpberry.math.preprocessors.Preprocessor;

import static com.kpberry.math.numeric.Primes.factorCount;

/**
 * Created by Kevin on 6/11/2017 for Spirals for Spirals.
 *
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
