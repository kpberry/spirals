package com.kpberry.spirals;

import com.kpberry.math.preprocessors.Preprocessor;
import com.kpberry.spirals.color_schemes.ColorScheme;
import com.kpberry.spirals.drawers.Drawer;
import com.kpberry.spirals.highlighters.Highlighter;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.GraphicsContext;

import java.util.function.Predicate;

/**
 * Created by Kevin on 5/21/2017 for Spirals for Spirals.
 *
 */
public class Spiral {
    private final Drawer drawer;
    private final Preprocessor preprocessor;
    private final ColorScheme colorScheme;
    private final Predicate<Integer> inclusionCriterion;
    private DoubleProperty progress;

    public Spiral(Drawer drawer, Preprocessor preprocessor,
                  ColorScheme colorScheme, Predicate<Integer> inclusionCriterion) {
        this.drawer = drawer;
        this.preprocessor = preprocessor;
        this.colorScheme = colorScheme;
        this.inclusionCriterion = inclusionCriterion;
        this.progress = new SimpleDoubleProperty(0);
    }

    public void draw(GraphicsContext gc, int spiralLength, double elemSize) {
        this.progress = this.preprocessor.progressProperty();
        this.preprocessor.accept(spiralLength);
        this.drawer.draw(
                gc, spiralLength, elemSize, colorScheme, inclusionCriterion
        );
    }

    public double getProgress() {
        return progress.get();
    }

    public DoubleProperty progressProperty() {
        return progress;
    }

    public Drawer getDrawer() {
        return drawer;
    }

    public Preprocessor getPreprocessor() {
        return preprocessor;
    }

    public ColorScheme getColorScheme() {
        return colorScheme;
    }

    public Predicate<Integer> getInclusionCriterion() {
        return inclusionCriterion;
    }

    public Highlighter getHighlighter() {
        return colorScheme.getHighlighter();
    }

    public double applyHighlighter(int n) {
        return this.colorScheme.applyHighlighter(n);
    }
}
