package math.inclusion_criteria;

import math.preprocessors.Nothing;
import math.preprocessors.Preprocessor;

/**
 * Created by Kevin on 6/28/2017 for Spirals.
 * <p>
 * Criterion which filters by whether or not a number is even.
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
