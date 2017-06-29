package com.kpberry.math.inclusion_criteria;

import com.kpberry.math.InclusionCriterion;
import com.kpberry.math.Preprocessor;
import com.kpberry.math.preprocessors.Nothing;

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
    public Preprocessor getPreprocessor() {
        return new Nothing();
    }
}
