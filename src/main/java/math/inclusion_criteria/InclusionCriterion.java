package math.inclusion_criteria;

import math.preprocessors.Nothing;
import math.preprocessors.Preprocessor;

import java.util.function.Predicate;

/**
 * Created by Kevin on 6/26/2017 for Spirals.
 * <p>
 * Represents a rule that determines whether or not an integer should be
 * included in a sequence.
 */
public interface InclusionCriterion extends Predicate<Integer> {
    /**
     * Returns a preprocessor used to speed up evaluation of the predicate.
     * Useful for sequences where computing many values at once is much more
     * efficient than computing the same values individually, e.g., primes
     * with a sieve of eratosthenes or collatz sequences with dynamic
     * programming.
     *
     * @return A preprocessor which can be run before computing predicate values
     */
    default Preprocessor getPreprocessor() {
        return new Nothing();
    }
}
