package math.preprocessors;

import javafx.beans.binding.DoubleBinding;
import math.numeric.Primes;

import static math.numeric.Primes.updatePrimeFactorCounts;

/**
 * Created by Kevin on 6/11/2017 for Spirals.
 * <p>
 * Initializes prime factor counts as a preprocessing step.
 */
public class InitializePrimeFactorCounts extends Preprocessor {
    @Override
    public void accept(Integer spiralLength) {
        updatePrimeFactorCounts(spiralLength);
    }

    @Override
    public String toString() {
        return "Initialize Prime Factor Counts";
    }

    @Override
    public DoubleBinding progressProperty() {
        return Primes.progressProperty().add(0);
    }
}
