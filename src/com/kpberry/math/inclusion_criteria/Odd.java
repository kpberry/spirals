package com.kpberry.math.inclusion_criteria;

import com.kpberry.math.preprocessors.Nothing;
import com.kpberry.math.preprocessors.Preprocessor;

/**
 * Created by Kevin on 6/11/2017 for Spirals for Spirals.
 *
 */
public class Odd implements InclusionCriterion {
    @Override
    public boolean test(Integer integer) {
        return (integer & 0x1) == 1;
    }

    @Override
    public String toString() {
        return "Odd";
    }

    @Override
    public Preprocessor getPreprocessor() {
        return new Nothing();
    }
}
