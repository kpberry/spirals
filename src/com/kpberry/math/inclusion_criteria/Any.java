package com.kpberry.math.inclusion_criteria;

import com.kpberry.math.preprocessors.Nothing;
import com.kpberry.math.preprocessors.Preprocessor;

/**
 * Created by Kevin on 6/11/2017 for Spirals for Spirals for Spirals.
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
    public Preprocessor getPreprocessor() {
        return new Nothing();
    }
}
