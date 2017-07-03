package com.kpberry.math.preprocessors;

import com.kpberry.math.numeric.Primes;
import javafx.beans.property.DoubleProperty;

import static com.kpberry.math.numeric.Primes.updatePrimeFactorCounts;

/**
 * Created by Kevin on 6/11/2017 for Spirals for Spirals for Spirals.
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
