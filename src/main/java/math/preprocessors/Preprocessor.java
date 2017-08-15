package math.preprocessors;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.control.ProgressIndicator;

import java.util.function.Consumer;

/**
 * Created by Kevin on 6/27/2017 for Spirals.
 *
 * Class that allows for preprocessing of integer sequences.
 */
public abstract class Preprocessor implements Consumer<Integer> {
    @Override
    public boolean equals(Object other) {
        return (other instanceof Preprocessor)
                && other.toString().equals(this.toString());
    }

    /**
     * Returns the current progress in the preprocessing step.
     *
     * @return the current progress in the preprocessing step.
     */
    public DoubleBinding progressProperty() {
        return new DoubleBinding() {
            @Override
            protected double computeValue() {
                return ProgressIndicator.INDETERMINATE_PROGRESS;
            }
        };
    }
}
