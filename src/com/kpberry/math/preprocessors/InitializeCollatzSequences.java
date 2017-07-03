package com.kpberry.math.preprocessors;

import com.kpberry.math.numeric.Collatz;
import javafx.beans.property.DoubleProperty;

import static com.kpberry.math.numeric.Collatz.updateCollatzLengths;

/**
 * Created by Kevin on 6/28/2017 for Spirals for Spirals for Spirals.
 *
 */
public class InitializeCollatzSequences extends Preprocessor {
    @Override
    public void accept(Integer spiralLength) {
        updateCollatzLengths(spiralLength);
    }

    @Override
    public String toString() {
        return "Update Collatz Lengths";
    }

    @Override
    public DoubleProperty progressProperty() {
        return Collatz.progressProperty();
    }
}
