package com.kpberry.spirals.highlight_modes;

import com.kpberry.spirals.ColorScheme;
import com.kpberry.spirals.HighlightMode;
import com.kpberry.spirals.Highlighter;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Optional;

/**
 * Created by Kevin on 6/26/2017 for Spirals for Spirals.
 *
 */
public class MultipleOfBase implements HighlightMode {
    @Override
    public String toString() {
        return "Multiple of Base";
    }

    @Override
    public int getNumRequiredColors() {
        return 1;
    }

    @Override
    public ColorScheme getColorScheme(Highlighter h, List<Color> cs, int max) {
        Color[] colors = sanitizeColors(cs);
        Color base = colors[0];
        return v -> {
            double multiple = h.apply(v);
            double red = multiple * base.getRed();
            red = red > 1 ? 1 : red;
            double green = multiple * base.getGreen();
            green = green > 1 ? 1 : green;
            double blue = multiple * base.getBlue();
            blue = blue > 1 ? 1 : blue;
            return Optional.of(new Color(red, green, blue, 1));
        };
    }
}
