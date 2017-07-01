package com.kpberry.spirals.highlighters;

import com.kpberry.math.preprocessors.InitializeGoldbachCounts;
import com.kpberry.math.preprocessors.Preprocessor;

import static com.kpberry.math.numeric.Goldbach.goldbachIndex;

/**
 * Created by Kevin on 6/26/2017 for Spirals for Spirals.
 *
 */
public class GoldbachCount implements Highlighter {
    @Override
    public Double apply(Integer integer) {
        return (double) goldbachIndex(integer);
    }

    @Override
    public Preprocessor getPreprocessor() {
        return new InitializeGoldbachCounts();
    }

    @Override
    public String toString() {
        return "Goldbach Count";
    }
}
