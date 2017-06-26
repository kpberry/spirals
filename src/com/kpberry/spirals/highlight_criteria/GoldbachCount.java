package com.kpberry.spirals.highlight_criteria;

import com.kpberry.spirals.Highlighter;
import com.kpberry.spirals.preprocessors.InitializeGoldbachCounts;

import java.util.function.Consumer;

import static com.kpberry.math.Goldbach.goldbachIndex;

/**
 * Created by Kevin on 6/26/2017 for Spirals.
 */
public class GoldbachCount implements Highlighter {
    @Override
    public Double apply(Integer integer) {
        return (double) goldbachIndex(integer);
    }

    @Override
    public Consumer<Integer> getPreprocessor() {
        return new InitializeGoldbachCounts();
    }

    @Override
    public String toString() {
        return "Goldbach Count";
    }
}
