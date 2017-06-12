package com.kpberry.spirals.inclusion_criteria;

import java.util.function.Predicate;

/**
 * Created by Kevin on 6/11/2017 for Spirals.
 */
public class LogN_LT_FC implements Predicate<Integer> {
    @Override
    public boolean test(Integer integer) {
        return false;
    }

    @Override
    public String toString() {
        return "ln(n) < FactorCount(n)";
    }
}
