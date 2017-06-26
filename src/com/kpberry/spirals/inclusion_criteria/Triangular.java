package com.kpberry.spirals.inclusion_criteria;

import com.kpberry.spirals.InclusionCriterion;
import com.kpberry.spirals.preprocessors.IdentifyTriangularNumbers;

import java.util.function.Consumer;

import static com.kpberry.math.Triangular.isTriangular;

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
    public Consumer<Integer> getPreprocessor() {
        return new IdentifyTriangularNumbers();
    }
}
