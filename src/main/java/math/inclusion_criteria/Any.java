package math.inclusion_criteria;

import math.preprocessors.Nothing;
import math.preprocessors.Preprocessor;

/**
 * Created by Kevin on 6/11/2017 for Spirals.
 * <p>
 * Do nothing filter.
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
