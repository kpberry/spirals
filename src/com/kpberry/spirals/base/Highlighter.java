package com.kpberry.spirals.base;

import com.kpberry.math.Preprocessor;
import com.kpberry.math.preprocessors.Nothing;

import java.util.function.Function;

/**
 * Created by Kevin on 6/26/2017 for Spirals for Spirals.
 *
 */
@FunctionalInterface
public interface Highlighter extends Function<Integer, Double> {
    default Preprocessor getPreprocessor() {
        return new Nothing();
    }
}
