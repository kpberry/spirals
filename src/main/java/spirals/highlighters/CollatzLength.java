package spirals.highlighters;

import math.preprocessors.InitializeCollatzSequences;
import math.preprocessors.Preprocessor;

import static math.numeric.Collatz.collatzLength;

/**
 * Created by Kevin on 6/28/2017 for Spirals.
 */
public class CollatzLength implements PreprocessedFn {
    @Override
    public Preprocessor getPreprocessor() {
        return new InitializeCollatzSequences();
    }

    @Override
    public Double apply(Integer integer) {
        return (double) collatzLength(integer);
    }

    @Override
    public String toString() {
        return "Collatz Length";
    }
}
