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
public class Binary implements ColorSchemeFactory {
    @Override
    public String toString() {
        return "Binary";
    }

    @Override
    public int getNumRequiredColors() {
        return 2;
    }

    @Override
    public ColorScheme getColorScheme(Highlighter h, List<Color> cs, int cutoff) {
        Color[] colors = sanitizeColors(cs);
        return new ColorScheme(colors, this, h, cutoff) {
            @Override
            public boolean isLow(int value) {
                return h.apply(value) <= 0;
            }

            @Override
            public Color getColor(int value) {
                return h.apply(value) > 0 ? colors[0] : colors[1];
            }

            public String toString() {
                return String.format(
                        "Binary by %s: (%s, %s)", h, colors[0], colors[1]
                );
            }

            @Override
            public Preprocessor getPreprocessor() {
                return h.getPreprocessor();
            }
        };
    }
}
