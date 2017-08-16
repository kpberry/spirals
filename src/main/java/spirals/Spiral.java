package spirals;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.canvas.GraphicsContext;
import math.preprocessors.Preprocessor;
import spirals.color_schemes.ColorScheme;
import spirals.drawers.Drawer;

import java.util.function.Predicate;

/**
 * Created by Kevin on 5/21/2017 for Spirals.
 *
 */
public class Spiral {
    private final Drawer drawer;
    private final Preprocessor preprocessor;
    private final ColorScheme colorScheme;
    private final Predicate<Integer> inclusionCriterion;
    private DoubleBinding progress;

    public Spiral(Drawer drawer, Preprocessor preprocessor, ColorScheme colorScheme,
                  Predicate<Integer> inclusionCriterion) {
        this.drawer = drawer;
        this.preprocessor = preprocessor;
        this.colorScheme = colorScheme;
        this.inclusionCriterion = inclusionCriterion;
        this.progress = new DoubleBinding() {
            @Override
            protected double computeValue() {
                return 0;
            }
        };
    }

    public void draw(GraphicsContext gc, int spiralLength, double elemSize) {
        this.progress = this.preprocessor.progressProperty();
        this.preprocessor.accept(spiralLength);
        this.drawer.draw(
                gc, spiralLength, elemSize, colorScheme, inclusionCriterion
        );
    }

    public DoubleBinding progressProperty() {
        return progress;
    }

    public Preprocessor getPreprocessor() {
        return preprocessor;
    }

    public ColorScheme getColorScheme() {
        return colorScheme;
    }

    public double applyHighlighter(int n) {
        return this.colorScheme.apply(n);
    }
}
