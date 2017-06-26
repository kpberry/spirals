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
public class LERP implements HighlightMode {
    @Override
    public String toString() {
        return "LERP";
    }

    @Override
    public int getNumRequiredColors() {
        return 2;
    }

    @Override
    public ColorScheme getColorScheme(Highlighter h, List<Color> cs, int max) {
        Color[] colors = sanitizeColors(cs);
        Color high = colors[1];
        Color low = colors[0];
        return v -> Optional.of(
                low.interpolate(high, Math.min(h.apply(v) / max, 1))
        );
    }
}
