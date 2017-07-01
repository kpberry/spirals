package com.kpberry.spirals.color_schemes;

import com.kpberry.spirals.highlighters.Highlighter;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * Created by Kevin on 7/1/2017 for Spirals for Spirals.
 */
public class LERP extends ColorScheme {
    private final Color low;
    private final Color high;

    public LERP(List<Color> colors, Highlighter h, int cutoff) {
        super(new com.kpberry.spirals.highlight_modes.LERP(), colors, h, cutoff);
        this.low = getColor(0);
        this.high = getColor(1);
    }

    @Override
    public boolean isLow(int value) {
        return applyHighlighter(value) <= 0;
    }

    @Override
    public Color computeColor(int value) {
        return low.interpolate(
                high, Math.min((1.0 * applyHighlighter(value)) / getCutoff(), 1)
        );
    }

    public String toString() {
        return String.format(
                "LERP by %s from %s to %s", getHighlighter(), low, high
        );
    }
}
