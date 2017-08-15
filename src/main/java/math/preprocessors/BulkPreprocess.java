package math.preprocessors;

import javafx.beans.binding.DoubleBinding;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Kevin on 6/28/2017 for Spirals.
 * <p>
 * Class that aggregates preprocessors and can execute them in sequence
 */
public class BulkPreprocess extends Preprocessor {
    private final Collection<Preprocessor> preprocessors;

    /**
     * Constructs a preprocessor from a sequence of preprocessors which can be
     * run as a single preprocessing step
     *
     * @param preprocessors the preprocessors to run in sequence
     */
    public BulkPreprocess(Collection<Preprocessor> preprocessors) {
        this.preprocessors = new ArrayList<>(preprocessors);
    }

    @Override
    public void accept(Integer integer) {
        preprocessors.parallelStream().forEach(p -> p.accept(integer));
    }

    @Override
    public DoubleBinding progressProperty() {
        DoubleBinding result = new DoubleBinding() {
            @Override
            protected double computeValue() {
                return 0;
            }
        };

        for (Preprocessor p : preprocessors) {
            result = result.add(p.progressProperty());
        }

        result = result.divide(preprocessors.size());

        return result;
    }
}
