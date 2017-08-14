package spirals.color_schemes;

import javafx.scene.paint.Color;
import spirals.highlight_modes.HighlightMode;
import spirals.highlighters.Highlighter;

import java.util.List;

/**
 * Created by Kevin on 6/26/2017 for Spirals.
 */
public class ColorSchemeFactory {
    private static final HighlightMode BINARY
            = new spirals.highlight_modes.Binary();
    private static final HighlightMode LERP
            = new spirals.highlight_modes.LERP();
    private static final HighlightMode MULTIPLE_OF_BASE
            = new spirals.highlight_modes.MultipleOfBase();

    public ColorScheme getColorScheme(HighlightMode hm, Highlighter h,
                                      List<Color> cs, int cutoff) {
        ColorScheme result;
        if (BINARY.equals(hm)) {
            result = new Binary(cs, h, cutoff);
        } else if (LERP.equals(hm)) {
            result = new LERP(cs, h, cutoff);
        } else if (MULTIPLE_OF_BASE.equals(hm)) {
            result = new MultipleOfBase(cs, h, cutoff);
        } else {
            throw new IllegalArgumentException("Unknown highlight mode: " + hm);
        }

        return result;
    }
}
