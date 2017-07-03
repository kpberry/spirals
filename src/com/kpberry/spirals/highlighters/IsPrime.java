package com.kpberry.spirals.highlighters;

import com.kpberry.math.preprocessors.IdentifyPrimeNumbers;
import com.kpberry.math.preprocessors.Preprocessor;

import static com.kpberry.math.numeric.Primes.isPrime;

/**
 * Created by Kevin on 6/26/2017 for Spirals for Spirals for Spirals.
 *
 */
public class IsPrime implements Highlighter {

    @Override
    public Double apply(Integer integer) {
        return isPrime(integer) ? 1.0 : 0.0;
    }

    @Override
    public Preprocessor getPreprocessor() {
        return new IdentifyPrimeNumbers();
    }

    @Override
    public String toString() {
        return "Is Prime";
    }
}
