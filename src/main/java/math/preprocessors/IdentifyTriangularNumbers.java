package math.preprocessors;

import javafx.beans.property.DoubleProperty;
import math.numeric.Triangular;

import static math.numeric.Triangular.updateTriangleNumbers;

/**
 * Created by Kevin on 6/11/2017 for Spirals.
 *
 */
public class IdentifyTriangularNumbers extends Preprocessor {
    @Override
    public void accept(Integer spiralLength) {
        updateTriangleNumbers(spiralLength);
    }

    @Override
    public String toString() {
        return "Identify Triangular Numbers";
    }

    @Override
    public DoubleProperty progressProperty() {
        return Triangular.progressProperty();
    }
}
