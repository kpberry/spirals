package com.kpberry.spirals.inclusion_criteria;

import java.util.function.Predicate;

/**
 * Created by Kevin on 6/11/2017 for Spirals.
 */
public class Any implements Predicate<Integer> {
    @Override
    public boolean test(Integer integer) {
        return true;
    }

    @Override
    public String toString() {
        return "Any";
    }
}
