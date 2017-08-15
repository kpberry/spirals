package math.numeric;

import org.junit.Test;

import static math.numeric.Goldbach.goldbachIndex;

/**
 * Created by Kevin on 8/14/2017 for Spirals.
 */
public class GoldbachTest {
    @Test
    public void goldbachIndexTest() throws Exception {
        int[] expecteds = {
                0, 1, 1, 1, 2, 1, 2, 2, 2, 2, 3, 3, 3, 2, 3, 2, 4, 4, 2, 3, 4,
                3, 4, 5, 4, 3, 5, 3, 4, 6, 3, 5, 6, 2, 5, 6, 5, 5, 7, 4, 5, 8,
                5, 4, 9, 4, 5, 7, 3, 6, 8, 5, 6, 8, 6, 7, 10, 6, 6, 12, 4, 5,
                10, 3
        };

        for (int i = 0; i < expecteds.length; i++) {
            assert (goldbachIndex(2 * i + 2) == expecteds[i]);
        }
    }

    @Test
    public void goldbachCacheTest() throws Exception {
        for (int i = 1000; i < 10000; i += 1000) {
            long t0 = System.nanoTime();
            goldbachIndex(i);
            long t1 = System.nanoTime();
            goldbachIndex(i);
            long t2 = System.nanoTime();
            assert (t2 - t1 < t1 - t0);
        }
    }
}