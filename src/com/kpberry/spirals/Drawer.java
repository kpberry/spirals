package com.kpberry.spirals;

import javafx.scene.canvas.GraphicsContext;

import java.util.function.Predicate;

/**
 * Created by Kevin on 6/11/2017 for Spirals.
 */
public interface Drawer {
    void draw(GraphicsContext gc, int length, double elemSize,
              ColorScheme cs, Predicate<Integer> ic);
}
