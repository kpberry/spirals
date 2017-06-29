package com.kpberry.spirals.highlight_modes;

import com.kpberry.math.Preprocessor;
import com.kpberry.spirals.base.ColorScheme;
import com.kpberry.spirals.base.ColorSchemeFactory;
import com.kpberry.spirals.base.Highlighter;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * Created by Kevin on 6/26/2017 for Spirals for Spirals.
 *
 */
public class LERP implements ColorSchemeFactory {
    @Override
    public String toString() {
        return "LERP";
    }

    @Override
    public int getNumRequiredColors() {
        return 2;
    }

    @Override
    public ColorScheme getColorScheme(Highlighter h, List<Color> cs, int cutoff) {
        Color[] colors = sanitizeColors(cs);
        Color high = colors[1];
        Color low = colors[0];

        return new ColorScheme(colors, this, h, cutoff) {
            @Override
            public boolean isLow(int value) {
                return h.apply(value) == 0;
            }

            @Override
            public Color getColor(int value) {
                return low.interpolate(high, Math.min(1.0 * h.apply(value) / cutoff, 1));
            }

            @Override
            public Preprocessor getPreprocessor() {
                return h.getPreprocessor();
            }

            public String toString() {
                return String.format("LERP by %s from %s to %s",
                        h, colors[0], colors[1]
                );
            }

        };
    }
}
