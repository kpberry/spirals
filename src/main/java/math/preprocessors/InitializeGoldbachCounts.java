package math.preprocessors;

import javafx.beans.binding.DoubleBinding;
import math.numeric.Goldbach;

import static math.numeric.Primes.updateFactorCounts;

/**
 * Created by Kevin on 6/11/2017 for Spirals.
 *
 * Initializes goldbach counts as a preprocessing step.
 */
public class InitializeGoldbachCounts extends Preprocessor {
    @Override
    public void accept(Integer spiralLength) {
        updateFactorCounts(spiralLength);
        Goldbach.updateGoldbachIndices(spiralLength);
    }

    @Override
    public String toString() {
        return "Initialize Goldbach Counts";
    }

    @Override
    public DoubleBinding progressProperty() {
        return Goldbach.progressProperty().add(0);
    }
}
