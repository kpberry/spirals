package spirals.highlighters;

import math.preprocessors.Nothing;
import math.preprocessors.Preprocessor;

import static math.numeric.NumberNames.getNumberNameLength;

/**
 * Created by Kevin on 6/26/2017 for Spirals.
 */
public class NameLengths implements Highlighter {
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
