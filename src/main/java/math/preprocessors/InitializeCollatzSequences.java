package math.preprocessors;

import javafx.beans.property.DoubleProperty;
import math.numeric.Collatz;

import static math.numeric.Collatz.updateCollatzLengths;

/**
 * Created by Kevin on 6/28/2017 for Spirals.
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
