package math.inclusion_criteria;

import math.preprocessors.Nothing;
import math.preprocessors.Preprocessor;

/**
 * Created by Kevin on 6/11/2017 for Spirals.
 *
 * Criterion which filters by whether or not a number is greater than 0.
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
