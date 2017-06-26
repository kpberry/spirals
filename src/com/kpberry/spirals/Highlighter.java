package com.kpberry.spirals;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Kevin on 6/26/2017 for Spirals.
 */
@FunctionalInterface
public interface Highlighter extends Function<Integer, Double> {
    default Consumer<Integer> getPreprocessor() {
        return i -> {};
    }
}
