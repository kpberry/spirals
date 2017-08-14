package math.preprocessors;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.ProgressIndicator;

import java.util.function.Consumer;

/**
 * Created by Kevin on 6/27/2017 for Spirals.
 *
 */
public abstract class Preprocessor implements Consumer<Integer> {
    @Override
    public boolean equals(Object other) {
        return (other instanceof Preprocessor)
                && other.toString().equals(this.toString());
    }

    public DoubleProperty progressProperty() {
        return new SimpleDoubleProperty(ProgressIndicator.INDETERMINATE_PROGRESS);
    }
}
