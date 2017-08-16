package spirals.highlighters;

import math.preprocessors.Nothing;
import math.preprocessors.Preprocessor;

import java.util.function.Function;

/**
 * Created by Kevin on 6/26/2017 for Spirals.
 *
 */
@FunctionalInterface
public interface PreprocessedFn extends Function<Integer, Double> {
    default Preprocessor getPreprocessor() {
        return new Nothing();
    }
}
