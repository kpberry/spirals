package com.kpberry.spirals.highlight_criteria;

import com.kpberry.spirals.Highlighter;
import com.kpberry.spirals.preprocessors.IdentifyTriangularNumbers;

import java.util.function.Consumer;

import static com.kpberry.math.Triangular.isTriangular;

/**
 * Created by Kevin on 6/26/2017 for Spirals.
 */
public class IsTriangular implements Highlighter {
    @Override
    public Double apply(Integer integer) {
        return isTriangular(integer) ? 1.0 : 0.0;
    }

    @Override
    public Consumer<Integer> getPreprocessor() {
        return new IdentifyTriangularNumbers();
    }

    @Override
    public String toString() {
        return "Is Triangular";
    }
}