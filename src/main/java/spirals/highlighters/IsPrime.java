package spirals.highlighters;

import math.preprocessors.IdentifyPrimeNumbers;
import math.preprocessors.Preprocessor;

import static math.numeric.Primes.isPrime;

/**
 * Created by Kevin on 6/26/2017 for Spirals.
 */
public class IsPrime implements PreprocessedFn {

    @Override
    public Double apply(Integer integer) {
        return isPrime(integer) ? 1.0 : 0.0;
    }

    @Override
    public Preprocessor getPreprocessor() {
        return new IdentifyPrimeNumbers();
    }

    @Override
    public String toString() {
        return "Is Prime";
    }
}
