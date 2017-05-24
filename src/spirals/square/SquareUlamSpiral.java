package spirals.square;

import javafx.scene.paint.Color;

import java.util.Optional;

import static math.Primes.isPrime;
import static math.Primes.updateFactorCounts;

/**
 * Created by Kevin on 5/21/2017 for Spirals for Spirals.
 *
 */
public class SquareUlamSpiral extends SquareSpiral {
    public SquareUlamSpiral(Color primeColor, Color nonPrimeColor) {
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
