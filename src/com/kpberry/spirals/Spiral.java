package com.kpberry.spirals;

import javafx.scene.canvas.GraphicsContext;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by Kevin on 5/21/2017 for Spirals for Spirals.
 *
 */
public class Spiral {
    private final Drawer drawer;
    private final Consumer<Integer> preprocessor;
    private final ColorScheme colorScheme;
    private final Predicate<Integer> inclusionCriterion;

    public Spiral(Drawer drawer, Consumer<Integer> preprocessor,
                  ColorScheme colorScheme, Predicate<Integer> inclusionCriterion) {
        this.drawer = drawer;
        this.preprocessor = preprocessor;
        this.colorScheme = colorScheme;
        this.inclusionCriterion = inclusionCriterion;
    }

    public void draw(GraphicsContext gc, int spiralLength, double elemSize) {
        this.preprocessor.accept(spiralLength);
        this.drawer.draw(
                gc, spiralLength, elemSize, colorScheme, inclusionCriterion
        );
    }
}
