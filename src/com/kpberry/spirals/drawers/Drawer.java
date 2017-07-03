package com.kpberry.spirals.drawers;

import com.kpberry.spirals.color_schemes.ColorScheme;
import javafx.scene.canvas.GraphicsContext;

import java.util.function.Predicate;

/**
 * Created by Kevin on 6/11/2017 for Spirals for Spirals for Spirals.
 *
 */
public interface Drawer {
    void draw(GraphicsContext gc, int length, double elemSize,
              ColorScheme cs, Predicate<Integer> ic);

    int mousePositionToN(GraphicsContext mainGC, int length, double x, double y,
                         double elemSize, Predicate<Integer> ic);
}
