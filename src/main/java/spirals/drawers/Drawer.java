package spirals.drawers;

import javafx.scene.canvas.GraphicsContext;
import spirals.color_schemes.ColorScheme;

import java.util.function.Predicate;

/**
 * Created by Kevin on 6/11/2017 for Spirals.
 * Draws a spiral on a supplied context
 */
public interface Drawer {
    /**
     * Draws a spiral of specified length, element size, color size, and
     * included elements on a specified context.
     *
     * @param gc       the context to use for drawing
     * @param length   the length (in elements) of the spiral
     * @param elemSize the size of each spiral element
     * @param cs       the color scheme to use for the spiral
     * @param ic       the inclusion criterion for spiral elements
     */
    void draw(GraphicsContext gc, int length, double elemSize,
              ColorScheme cs, Predicate<Integer> ic);

    /**
     * Returns the integer index in the spiral that this drawer would draw
     * that corresponds to the element in which the supplied mouse coordinate
     * would be.
     *
     * @param x        the x coordinate of the mouse
     * @param y        the y coordinate of the mouse
     * @param gc       the context to use for drawing
     * @param length   the length (in elements) of the spiral
     * @param elemSize the size of each spiral element
     * @param ic       the inclusion criterion for spiral elements
     * @return the index of the element the mouse would be in for a spiral
     * drawn by this drawer
     */
    int mousePositionToN(double x, double y, GraphicsContext gc, int length,
                         double elemSize, Predicate<Integer> ic);
}
