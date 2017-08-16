package spirals;

import javafx.beans.binding.DoubleBinding;
import javafx.scene.canvas.GraphicsContext;
import math.preprocessors.Preprocessor;
import spirals.color_schemes.ColorScheme;
import spirals.drawers.Drawer;

import java.util.function.Predicate;

/**
 * Created by Kevin on 5/21/2017 for Spirals.
 * <p>
 * Class that stores all information necessary to draw a spiral.
 */
public class Spiral {
    private final Drawer drawer;
    private final Preprocessor preprocessor;
    private final ColorScheme colorScheme;
    private final Predicate<Integer> inclusionCriterion;
    private DoubleBinding progress;

    /**
     * Creates a new spiral from a drawer, sequence preprocessor, color scheme,
     * and a value inclusion criterion
     *
     * @param drawer             draws this spiral onto a canvas
     * @param preprocessor       runs computations before the spiral is drawn
     * @param colorScheme        determines colors to use for sequence values
     * @param inclusionCriterion determines which values should be included in
     *                           the spiral
     */
    public Spiral(Drawer drawer, Preprocessor preprocessor,
                  ColorScheme colorScheme,
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

    /**
     * Draws this spiral with the given length and element size onto the
     * specified context
     *
     * @param gc           the context onto which the spiral will be drawn
     * @param spiralLength the length of the spiral to draw
     * @param elemSize     the size of each spiral element
     */
    public void draw(GraphicsContext gc, int spiralLength, double elemSize) {
        this.progress = this.preprocessor.progressProperty();
        this.preprocessor.accept(spiralLength);
        this.drawer.draw(
                gc, spiralLength, elemSize, colorScheme, inclusionCriterion
        );
    }

    /**
     * Returns the progress for drawing this spiral
     *
     * @return the progress for drawing this spiral
     */
    public DoubleBinding progressProperty() {
        return progress;
    }

    /**
     * Returns the preprocessor to run before drawing this spiral
     *
     * @return the preprocessor to run before drawing this spiral
     */
    public Preprocessor getPreprocessor() {
        return preprocessor;
    }

    /**
     * Returns the color scheme used to determine sequence color values
     *
     * @return the color scheme used to determine sequence color values
     */
    public ColorScheme getColorScheme() {
        return colorScheme;
    }

    /**
     * Returns the intensity value for an integer using this spiral's
     * intensity function
     *
     * @param n the value to test
     * @return the intensity of that value
     */
    public double getIntensity(int n) {
        return this.colorScheme.apply(n);
    }
}
