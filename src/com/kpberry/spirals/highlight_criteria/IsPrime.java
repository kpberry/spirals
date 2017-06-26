package com.kpberry.spirals.highlight_criteria;

import com.kpberry.spirals.Highlighter;
import com.kpberry.spirals.preprocessors.IdentifyPrimeNumbers;

import java.util.function.Consumer;

import static com.kpberry.math.Primes.isPrime;

/**
 * Created by Kevin on 6/26/2017 for Spirals.
 */
public class IsPrime implements Highlighter {

    @Override
    public Double apply(Integer integer) {
        return isPrime(integer) ? 1.0 : 0.0;
    }

    @Override
    public Consumer<Integer> getPreprocessor() {
        return new IdentifyPrimeNumbers();
    }

    @Override
    public String toString() {
        return "Is Prime";
    }
}
