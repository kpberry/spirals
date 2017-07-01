package com.kpberry.math.preprocessors;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Created by Kevin on 6/27/2017 for Spirals for Spirals.
 *
 */
public class Nothing extends Preprocessor {
    @Override
    public void accept(Integer integer) {
    }

    @Override
    public DoubleProperty progressProperty() {
        return new SimpleDoubleProperty(1);
    }
}
