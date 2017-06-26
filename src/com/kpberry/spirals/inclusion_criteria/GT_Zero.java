package com.kpberry.spirals.inclusion_criteria;

import com.kpberry.spirals.InclusionCriterion;

import java.util.function.Consumer;

/**
 * Created by Kevin on 6/11/2017 for Spirals for Spirals.
 *
 */
public class GT_Zero implements InclusionCriterion {
    @Override
    public boolean test(Integer integer) {
        return integer > 0;
    }

    @Override
    public String toString() {
        return "Greater than 0";
    }

    @Override
    public Consumer<Integer> getPreprocessor() {
        return i -> {};
    }
}
