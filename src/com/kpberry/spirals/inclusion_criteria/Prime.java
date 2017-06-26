package com.kpberry.spirals.inclusion_criteria;

import com.kpberry.spirals.InclusionCriterion;
import com.kpberry.spirals.preprocessors.IdentifyPrimeNumbers;

import java.util.function.Consumer;

import static com.kpberry.math.Primes.isPrime;

/**
 * Created by Kevin on 6/11/2017 for Spirals for Spirals.
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
    public Consumer<Integer> getPreprocessor() {
        return new IdentifyPrimeNumbers();
    }
}
