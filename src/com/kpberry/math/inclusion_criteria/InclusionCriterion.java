package com.kpberry.math.inclusion_criteria;

import com.kpberry.math.preprocessors.Nothing;
import com.kpberry.math.preprocessors.Preprocessor;

import java.util.function.Predicate;

/**
 * Created by Kevin on 6/26/2017 for Spirals.
 *
 */
public interface InclusionCriterion extends Predicate<Integer> {
    default Preprocessor getPreprocessor() {
        return new Nothing();
    }
}
