package math.inclusion_criteria;

import math.preprocessors.IdentifyPrimeNumbers;
import math.preprocessors.Preprocessor;

import static math.numeric.Primes.isPrime;

/**
 * Created by Kevin on 6/11/2017 for Spirals.
 *
 * Criterion which filters by whether or not a number is prime.
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
