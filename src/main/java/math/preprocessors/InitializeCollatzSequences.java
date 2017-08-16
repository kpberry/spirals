package math.preprocessors;

import javafx.beans.binding.DoubleBinding;
import math.numeric.Collatz;

import static math.numeric.Collatz.updateCollatzLengths;

/**
 * Created by Kevin on 6/28/2017 for Spirals.
 * <p>
 * Initialize collatz sequences as a preprocessing step.
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
    public DoubleBinding progressProperty() {
        return Collatz.progressProperty().add(0);
    }
}
