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
public class Binary implements HighlightMode {
    @Override
    public String toString() {
        return "Binary";
    }

    @Override
    public int getNumRequiredColors() {
        return 2;
    }

    @Override
    public ColorScheme getColorScheme(Highlighter h, List<Color> cs, int max) {
        Color[] colors = sanitizeColors(cs);
        return v -> Optional.of(h.apply(v) > 0 ? colors[0] : colors[1]);
    }
}
