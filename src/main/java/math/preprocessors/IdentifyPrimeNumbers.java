package math.preprocessors;

import javafx.beans.binding.DoubleBinding;
import math.numeric.Primes;

import static math.numeric.Primes.updateFactorCounts;

/**
 * Created by Kevin on 6/11/2017 for Spirals.
 *
 * Initializes prime numbers as a preprocessing step.
 */
public class IdentifyPrimeNumbers extends Preprocessor {
    @Override
    public void accept(Integer spiralLength) {
        updateFactorCounts(spiralLength);
    }

    @Override
    public String toString() {
        return "Identify Prime Numbers";
    }

    @Override
    public DoubleBinding progressProperty() {
        return Primes.progressProperty().add(0);
    }
}
