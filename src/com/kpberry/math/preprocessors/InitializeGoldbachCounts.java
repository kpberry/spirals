package com.kpberry.math.preprocessors;

import com.kpberry.math.Preprocessor;
import com.kpberry.math.numeric.Goldbach;
import javafx.beans.property.DoubleProperty;

import java.io.IOException;

import static com.kpberry.math.numeric.Primes.updateFactorCounts;

/**
 * Created by Kevin on 6/11/2017 for Spirals for Spirals.
 *
 */
public class InitializeGoldbachCounts extends Preprocessor {
    @Override
    public void accept(Integer spiralLength) {
        updateFactorCounts(spiralLength);
        try {
            Goldbach.readGoldbachIndices();
        } catch (IOException e) {
            e.printStackTrace();
        }
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