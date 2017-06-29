package com.kpberry.math.inclusion_criteria;

import com.kpberry.math.InclusionCriterion;
import com.kpberry.math.Preprocessor;
import com.kpberry.math.preprocessors.Nothing;

/**
 * Created by Kevin on 6/28/2017 for Spirals for Spirals.
 *
 */
public class Even implements InclusionCriterion {
    @Override
    public boolean test(Integer integer) {
        return (integer & 0x1) == 0;
    }

    @Override
    public String toString() {
        return "Even";
    }

    @Override
    public Preprocessor getPreprocessor() {
        return new Nothing();
    }
}
