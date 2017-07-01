package com.kpberry.spirals.highlighters;

import com.kpberry.math.preprocessors.InitializeCollatzSequences;
import com.kpberry.math.preprocessors.Preprocessor;

import static com.kpberry.math.numeric.Collatz.collatzLength;

/**
 * Created by Kevin on 6/28/2017 for Spirals for Spirals.
 *
 */
public class CollatzLength implements Highlighter {
    @Override
    public Preprocessor getPreprocessor() {
        return new InitializeCollatzSequences();
    }

    @Override
    public Double apply(Integer integer) {
        return (double) collatzLength(integer);
    }

    @Override
    public String toString() {
        return "Collatz Length";
    }
}
