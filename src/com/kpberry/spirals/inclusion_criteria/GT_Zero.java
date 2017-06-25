package com.kpberry.spirals.inclusion_criteria;

import java.util.function.Predicate;

/**
 * Created by Kevin on 6/11/2017 for Spirals for Spirals.
 *
 */
public class GT_Zero implements Predicate<Integer> {
    @Override
    public boolean test(Integer integer) {
        return integer > 0;
    }

    @Override
    public String toString() {
        return "Greater than 0";
    }
}
