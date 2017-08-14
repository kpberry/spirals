package math.numeric;

import org.junit.Test;

/**
 * Created by Kevin on 8/12/2017 for Spirals.
 */
public class CollatzTest {
    @Test
    public void collatzLength() throws Exception {
        for (int i = 2; i < 200; i += 2) {
            int l1 = Collatz.collatzLength(i);
            int l2 = Collatz.collatzLength(i / 2);
            assert (l1 == l2 + 1);
        }
        for (int i = 3; i < 201; i += 2) {
            int l1 = Collatz.collatzLength(i);
            int l2 = Collatz.collatzLength(i * 3 + 1);
            assert (l1 == l2 + 1);
        }
        assert (Collatz.collatzLength(1) == 0);
        assert (Collatz.collatzLength(5) == 5);
        assert (Collatz.collatzLength(3) == 7);
    }

    @Test
    public void collatzLengthNonPositive() {
        Collatz.collatzLength(5);

        boolean threwException = false;
        try {
            Collatz.collatzLength(0);
        } catch (ArithmeticException ae) {
            threwException = true;
        }
        assert (threwException);

        threwException = false;
        try {
            Collatz.collatzLength(-1);
        } catch (ArithmeticException ae) {
            threwException = true;
        }
        assert (threwException);
    }
}