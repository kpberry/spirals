package com.kpberry.math.inclusion_criteria;

import com.kpberry.math.preprocessors.IdentifyPrimeNumbers;
import com.kpberry.math.preprocessors.Preprocessor;

import static com.kpberry.math.numeric.Primes.isPrime;

/**
 * Created by Kevin on 6/11/2017 for Spirals for Spirals for Spirals.
 *
 */
public class Prime implements InclusionCriterion {
    @Override
    public boolean test(Integer value) {
        return isPrime(value);
    }

    @Override
    public String toString() {
        return "Primes";
    }

    @Override
    public Preprocessor getPreprocessor() {
        return new IdentifyPrimeNumbers();
    }
}
