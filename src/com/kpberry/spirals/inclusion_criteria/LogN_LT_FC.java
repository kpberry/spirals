package com.kpberry.spirals.inclusion_criteria;

import com.kpberry.spirals.InclusionCriterion;
import com.kpberry.spirals.preprocessors.InitializeFactorCounts;

import java.util.function.Consumer;

import static com.kpberry.math.Primes.factorCount;

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
    public Consumer<Integer> getPreprocessor() {
        return new InitializeFactorCounts();
    }
}
