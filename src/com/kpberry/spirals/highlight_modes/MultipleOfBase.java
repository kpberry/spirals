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
public class MultipleOfBase implements ColorSchemeFactory {
    @Override
    public String toString() {
        return "Multiple of Base";
    }

    @Override
    public int getNumRequiredColors() {
        return 1;
    }

    @Override
    public ColorScheme getColorScheme(Highlighter h, List<Color> cs, int cutoff) {
        Color[] colors = sanitizeColors(cs);
        Color base = colors[0];

        return new ColorScheme(colors, this, h, cutoff) {
            @Override
            public Color getColor(int value) {
                double multiple = h.apply(value);
                double red = multiple * base.getRed();
                red = red > 1 ? 1 : red;
                double green = multiple * base.getGreen();
                green = green > 1 ? 1 : green;
                double blue = multiple * base.getBlue();
                blue = blue > 1 ? 1 : blue;
                return new Color(red, green, blue, 1);
            }

            @Override
            public boolean isLow(int value) {
                return h.apply(value) == 0;
            }

            public String toString() {
                return String.format("Multiple of %s by %s", base, h);
            }

            @Override
            public Preprocessor getPreprocessor() {
                return h.getPreprocessor();
            }
        };
    }
}
