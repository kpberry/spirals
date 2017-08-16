package spirals.highlighters;

import math.preprocessors.InitializeGoldbachCounts;
import math.preprocessors.Preprocessor;

import static math.numeric.Goldbach.goldbachIndex;

/**
 * Created by Kevin on 6/26/2017 for Spirals.
 */
public class GoldbachCount implements PreprocessedFn {
    @Override
    public Double apply(Integer integer) {
        return (double) goldbachIndex(integer);
    }

    @Override
    public Preprocessor getPreprocessor() {
        return new InitializeGoldbachCounts();
    }

    @Override
    public String toString() {
        return "Goldbach Count";
    }
}
