package com.kpberry.spirals.base;

import javafx.scene.paint.Color;

import java.util.List;

/**
 * Created by Kevin on 6/26/2017 for Spirals for Spirals.
 *
 */
public interface ColorSchemeFactory {
    int getNumRequiredColors();
    ColorScheme getColorScheme(Highlighter h, List<Color> cs, int cutoff);

    default Color[] sanitizeColors(List<Color> cs) {
        int numColors = cs.size();
        Color[] result = new Color[getNumRequiredColors()];
        for (int i = 0; i < result.length; i++) {
            if (i < numColors) {
                result[i] = cs.get(i);
            } else {
                result[i] = Color.BLACK;
            }
        }

        return result;
    }
}
