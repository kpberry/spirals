package spirals.highlighters;

import math.preprocessors.IdentifyTriangularNumbers;
import math.preprocessors.Preprocessor;

import static math.numeric.Triangular.isTriangular;

/**
 * Created by Kevin on 6/26/2017 for Spirals.
 *
 */
public class IsTriangular implements Highlighter {
    @Override
    public Double apply(Integer integer) {
        return isTriangular(integer) ? 1.0 : 0.0;
    }

    @Override
    public Preprocessor getPreprocessor() {
        return new IdentifyTriangularNumbers();
    }

    @Override
    public String toString() {
        return "Is Triangular";
    }
}
