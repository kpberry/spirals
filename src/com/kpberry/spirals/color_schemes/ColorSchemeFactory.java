package com.kpberry.spirals.color_schemes;

import com.kpberry.spirals.highlight_modes.HighlightMode;
import com.kpberry.spirals.highlighters.Highlighter;
import javafx.scene.paint.Color;

import java.util.List;

/**
 * Created by Kevin on 6/26/2017 for Spirals for Spirals for Spirals.
 *
 */
public class ColorSchemeFactory {
    public ColorScheme getColorScheme(HighlightMode hm, Highlighter h,
                                      List<Color> cs, int cutoff) {
        if (new com.kpberry.spirals.highlight_modes.Binary().equals(hm)) {
            return new Binary(cs, h, cutoff);
        } else if (new com.kpberry.spirals.highlight_modes.LERP().equals(hm)) {
            return new LERP(cs, h, cutoff);
        } else if (new com.kpberry.spirals.highlight_modes.MultipleOfBase().equals(hm)) {
            return new MultipleOfBase(cs, h, cutoff);
        } else {
            throw new IllegalArgumentException("Unknown highlight mode: " + hm);
        }
    }
}
