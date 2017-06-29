package com.kpberry.math.inclusion_criteria;

import com.kpberry.math.InclusionCriterion;
import com.kpberry.math.Preprocessor;
import com.kpberry.math.preprocessors.IdentifyTriangularNumbers;

import static com.kpberry.math.numeric.Triangular.isTriangular;

/**
 * Created by Kevin on 6/11/2017 for Spirals for Spirals.
 *
 */
public class Triangular implements InclusionCriterion {
    @Override
    public boolean test(Integer value) {
        return isTriangular(value);
    }

    @Override
    public String toString() {
        return "Triangle Numbers";
    }

    @Override
    public Preprocessor getPreprocessor() {
        return new IdentifyTriangularNumbers();
    }
}
