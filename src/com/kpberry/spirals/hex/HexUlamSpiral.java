package com.kpberry.spirals.hex;

import javafx.scene.paint.Color;

import java.util.Optional;

import static com.kpberry.math.Primes.isPrime;
import static com.kpberry.math.Primes.updateFactorCounts;

/**
 * Created by Kevin on 5/21/2017 for Spirals for Spirals.
 *
 */
public class HexUlamSpiral extends HexSpiral {
    public HexUlamSpiral(Color primeColor, Color nonPrimeColor) {
        super(
                n -> Optional.of(isPrime(n) ? primeColor : nonPrimeColor),
                n -> (n > 0)
        );
    }

    @Override
    public void preprocess(int length) {
        updateFactorCounts(length);
    }
}
