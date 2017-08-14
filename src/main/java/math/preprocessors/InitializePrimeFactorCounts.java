package math.preprocessors;

import javafx.beans.property.DoubleProperty;
import math.numeric.Primes;

import static math.numeric.Primes.updatePrimeFactorCounts;

/**
 * Created by Kevin on 6/11/2017 for Spirals.
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
    public DoubleProperty progressProperty() {
        return Primes.progressProperty();
    }
}
