package math.numeric;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Kevin on 5/29/2017 for Spirals.
 * <p>
 * Computes the number of ways that numbers can be written as the sum of two
 * primes.
 */
public class Goldbach {
    private static final DoubleProperty progress = new SimpleDoubleProperty(0);
    private static int[] goldbachIndices = new int[0];

    /**
     * Returns the number of ways a number n can be written as the sum of two
     * primes
     *
     * @param n the number to check
     * @return the number of ways n can be written as the sum of two primes
     */
    public static int goldbachIndex(int n) {
        updateGoldbachIndices(n + 1);
        return goldbachIndices[n];
    }

    /**
     * Updates the goldbach indices for all numbers up to and including n,
     * caching them locally.
     *
     * @param n the number up to which values will be cached
     */
    @SuppressWarnings("StatementWithEmptyBody")
    public static synchronized void updateGoldbachIndices(int n) {
        if (n > goldbachIndices.length) {
            final int[] localGoldbachIndices = new int[n];

            progress.setValue(0);

            Primes.updateFactorCounts(n);
            List<Integer> primes = Primes.getCalculatedPrimes();

            ExecutorService executor = Executors.newCachedThreadPool();
            int batchSize = 500;
            for (int batch = 0; batch < n; batch += batchSize) {
                final int batchFinal = batch;
                executor.execute(() -> {
                    if (1.0 * batchFinal / batchSize > progress.getValue()) {
                        synchronized (progress) {
                            progress.setValue(1.0 * batchFinal / batchSize);
                        }
                    }
                    int runLength = Math.min(
                            localGoldbachIndices.length - batchFinal, batchSize
                    );
                    for (int i = batchFinal; i < batchFinal + runLength; i++) {
                        //no synchronization necessary because each array
                        //index is only accessed by one thread
                        localGoldbachIndices[i]
                                = computeGoldbachIndex(i, primes);
                    }
                });
            }

            try {
                executor.shutdown();
                while (!executor.awaitTermination(1000, TimeUnit.SECONDS)) ;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            goldbachIndices = localGoldbachIndices;
        }
    }

    private static int
    computeGoldbachIndex(final int i, final List<Integer> primes) {
        int result = 0;
        int primeIndex = 0;
        int cur = 2;
        while ((cur <= (i >> 1)) && (primeIndex < primes.size())) {
            if (Primes.isPrime(i - cur)) {
                result++;
            }
            cur = primes.get(++primeIndex);
        }
        return result;
    }

    /**
     * Returns the progress for updating the current set of values.
     *
     * @return the current progress
     */
    public static DoubleProperty progressProperty() {
        return progress;
    }
}
