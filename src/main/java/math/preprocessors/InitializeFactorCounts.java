package math.preprocessors;

import javafx.beans.binding.DoubleBinding;
import math.numeric.Primes;

import static math.numeric.Primes.updateFactorCounts;

/**
 * Created by Kevin on 6/11/2017 for Spirals.
 * <p>
 * Initializes factor counts as a preprocessing step.
 */
public class InitializeFactorCounts extends Preprocessor {
    @Override
    public void accept(Integer spiralLength) {
        updateFactorCounts(spiralLength);
    }

    @Override
    public String toString() {
        return "Initialize Factor Counts";
    }

    @Override
    public DoubleBinding progressProperty() {
        return Primes.progressProperty().add(0);
    }
}
