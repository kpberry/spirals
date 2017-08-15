package math.preprocessors;

import javafx.beans.binding.DoubleBinding;

/**
 * Created by Kevin on 6/27/2017 for Spirals.
 *
 * Does nothing as a preprocessing step.
 */
public class Nothing extends Preprocessor {
    @Override
    public void accept(Integer integer) {
    }

    @Override
    public DoubleBinding progressProperty() {
        return new DoubleBinding() {
            @Override
            protected double computeValue() {
                return 1;
            }
        };
    }
}
