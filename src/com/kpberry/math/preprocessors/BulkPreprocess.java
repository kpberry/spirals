package com.kpberry.math.preprocessors;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ObservableValue;

import java.util.Collection;

/**
 * Created by Kevin on 6/28/2017 for Spirals for Spirals.
 *
 */
public class BulkPreprocess extends Preprocessor {
    private final Collection<Preprocessor> preprocessors;
    public BulkPreprocess(Collection<Preprocessor> preprocessors) {
        this.preprocessors = preprocessors;
    }

    @Override
    public void accept(Integer integer) {
        preprocessors.parallelStream().forEach(p -> p.accept(integer));
    }

    public ObservableValue progressBinding() {
        DoubleBinding result = new DoubleBinding() {
            @Override
            protected double computeValue() {
                return 0;
            }
        };

        for (Preprocessor p : preprocessors) {
            result = result.add(p.progressProperty());
        }

        //result = result.divide(preprocessors.size());

        return result;
    }
}
