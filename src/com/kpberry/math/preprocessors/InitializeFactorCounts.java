package com.kpberry.math.preprocessors;

import com.kpberry.math.Preprocessor;
import com.kpberry.math.numeric.Primes;
import javafx.beans.property.DoubleProperty;

import static com.kpberry.math.numeric.Primes.updateFactorCounts;

/**
 * Created by Kevin on 6/11/2017 for Spirals for Spirals.
 *
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
    public DoubleProperty progressProperty() {
        return Primes.progressProperty();
    }
}
