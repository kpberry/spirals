package math.preprocessors;

import javafx.beans.property.DoubleProperty;
import math.numeric.Primes;

import static math.numeric.Primes.updateFactorCounts;

/**
 * Created by Kevin on 6/11/2017 for Spirals.
 *
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
    public DoubleProperty progressProperty() {
        return Primes.progressProperty();
    }
}
