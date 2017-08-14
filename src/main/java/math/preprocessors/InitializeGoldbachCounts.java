package math.preprocessors;

import javafx.beans.property.DoubleProperty;
import math.numeric.Goldbach;

import static math.numeric.Primes.updateFactorCounts;

/**
 * Created by Kevin on 6/11/2017 for Spirals.
 *
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
    public DoubleProperty progressProperty() {
        return Goldbach.progressProperty();
    }
}
