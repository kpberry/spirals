package spirals.highlighters;

import math.preprocessors.Nothing;
import math.preprocessors.Preprocessor;

import java.util.function.Function;

/**
 * Created by Kevin on 6/26/2017 for Spirals.
 * Class for representing functions with an optional preprocessing step.
 */
@FunctionalInterface
public interface PreprocessedFn extends Function<Integer, Double> {
    /**
     * Returns the preprocessor to be run before values of this function are
     * computed
     *
     * @return the preprocessor for this function
     */
    default Preprocessor getPreprocessor() {
        return new Nothing();
    }
}
