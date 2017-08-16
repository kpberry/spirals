package spirals.highlighters;

import math.preprocessors.Nothing;
import math.preprocessors.Preprocessor;

import static math.numeric.NumberNames.getNumberNameLength;

/**
 * Created by Kevin on 6/26/2017 for Spirals.
 * Class for preprocessing and getting the lengths of the english names of
 * numbers
 */
public class NameLengths implements PreprocessedFn {
    @Override
    public Double apply(Integer integer) {
        return (double) getNumberNameLength(integer);
    }

    @Override
    public Preprocessor getPreprocessor() {
        return new Nothing();
    }

    @Override
    public String toString() {
        return "Name Lengths";
    }
}
