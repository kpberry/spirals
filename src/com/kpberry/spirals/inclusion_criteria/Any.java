package com.kpberry.spirals.inclusion_criteria;

import com.kpberry.spirals.InclusionCriterion;

import java.util.function.Consumer;

/**
 * Created by Kevin on 6/11/2017 for Spirals for Spirals.
 *
 */
public class Any implements InclusionCriterion {
    @Override
    public boolean test(Integer integer) {
        return true;
    }

    @Override
    public String toString() {
        return "Any";
    }

    @Override
    public Consumer<Integer> getPreprocessor() {
        return i -> {};
    }
}
