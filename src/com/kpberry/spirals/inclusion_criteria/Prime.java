package com.kpberry.spirals.inclusion_criteria;

import java.util.function.Predicate;

import static com.kpberry.math.Primes.isPrime;

/**
 * Created by Kevin on 6/11/2017 for Spirals for Spirals.
 *
 */
public class Prime implements Predicate<Integer> {
    @Override
    public boolean test(Integer value) {
        return isPrime(value);
    }

    @Override
    public String toString() {
        return "Primes";
    }
}
