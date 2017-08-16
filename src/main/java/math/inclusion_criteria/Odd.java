package math.inclusion_criteria;

import math.preprocessors.Nothing;
import math.preprocessors.Preprocessor;

/**
 * Created by Kevin on 6/11/2017 for Spirals.
 * <p>
 * Criterion which filters by whether or not a number is odd.
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
