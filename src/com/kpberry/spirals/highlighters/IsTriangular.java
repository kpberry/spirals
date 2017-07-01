package com.kpberry.spirals.highlighters;

import com.kpberry.math.preprocessors.IdentifyTriangularNumbers;
import com.kpberry.math.preprocessors.Preprocessor;

import static com.kpberry.math.numeric.Triangular.isTriangular;

/**
 * Created by Kevin on 6/26/2017 for Spirals for Spirals.
 *
 */
public class IsTriangular implements Highlighter {
    @Override
    public Double apply(Integer integer) {
        return isTriangular(integer) ? 1.0 : 0.0;
    }

    @Override
    public Preprocessor getPreprocessor() {
        return new IdentifyTriangularNumbers();
    }

    @Override
    public String toString() {
        return "Is Triangular";
    }
}
