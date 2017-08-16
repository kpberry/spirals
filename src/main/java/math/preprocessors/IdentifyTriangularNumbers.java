package math.preprocessors;

import javafx.beans.binding.DoubleBinding;
import math.numeric.Triangular;

import static math.numeric.Triangular.updateTriangleNumbers;

/**
 * Created by Kevin on 6/11/2017 for Spirals.
 * <p>
 * Initializes triangular numbers as a preprocessing step.
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
    public DoubleBinding progressProperty() {
        return Triangular.progressProperty().add(0);
    }
}
