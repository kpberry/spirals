package com.kpberry.spirals;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by Kevin on 6/26/2017 for Spirals.
 *
 */
public interface InclusionCriterion extends Predicate<Integer> {
    default Consumer<Integer> getPreprocessor() {
        return i -> {};
    }
}
