package com.kpberry.spirals.base;

import com.kpberry.math.Preprocessor;
import com.kpberry.math.preprocessors.Nothing;
import javafx.scene.paint.Color;

import java.util.Arrays;

/**
 * Created by Kevin on 5/21/2017 for Spirals for Spirals.
 *
 */
public abstract class ColorScheme {
    private final ColorSchemeFactory colorSchemeFactory;
    private final Highlighter highlighter;
    private final int cutoff;
    private final Color[] colors;

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public abstract boolean isLow(int value);

    public abstract Color getColor(int value);

    public ColorScheme(Color[] colors, ColorSchemeFactory hm, Highlighter h, int cutoff) {
        this.colorSchemeFactory = hm;
        this.highlighter = h;
        this.cutoff = cutoff;
        this.colors = colors;
    }

    public Preprocessor getPreprocessor() {
        return new Nothing();
    }

    public ColorSchemeFactory getColorSchemeFactory() {
        return colorSchemeFactory;
    }

    public int getCutoff() {
        return cutoff;
    }

    public Highlighter getHighlighter() {
        return highlighter;
    }

    public Color[] getColors() {
        return colors;
    }

    public int getNumRequiredColors() {
        return colorSchemeFactory.getNumRequiredColors();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ColorScheme)) return false;

        ColorScheme that = (ColorScheme) o;

        if (cutoff != that.cutoff) return false;
        if (colorSchemeFactory != null ? !colorSchemeFactory.equals(that.colorSchemeFactory) : that.colorSchemeFactory != null)
            return false;
        if (highlighter != null ? !highlighter.equals(that.highlighter) : that.highlighter != null)
            return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(colors, that.colors);
    }

    @Override
    public int hashCode() {
        int result = colorSchemeFactory != null ? colorSchemeFactory.hashCode() : 0;
        result = 31 * result + (highlighter != null ? highlighter.hashCode() : 0);
        result = 31 * result + cutoff;
        result = 31 * result + Arrays.hashCode(colors);
        return result;
    }
}
