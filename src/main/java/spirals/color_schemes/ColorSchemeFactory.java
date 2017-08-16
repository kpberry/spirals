package spirals.color_schemes;

import javafx.scene.paint.Color;
import spirals.highlight_modes.HighlightMode;
import spirals.highlighters.PreprocessedFn;

import java.util.List;

/**
 * Created by Kevin on 6/26/2017 for Spirals.
 * <p>
 * Class to facilitate the creation of color schemes from consistent parameters.
 */
public class ColorSchemeFactory {
    private static final HighlightMode BINARY
            = new spirals.highlight_modes.Binary();
    private static final HighlightMode LERP
            = new spirals.highlight_modes.LERP();
    private static final HighlightMode MULTIPLE_OF_BASE
            = new spirals.highlight_modes.MultipleOfBase();

    /**
     * Returns a new color scheme based on the supplied highlight mode,
     * intensity function, color scheme, and cutoff.
     *
     * @param hm     the expected highlight mode to be used
     * @param fn     the function to use for determining color intensities
     * @param cs     the colors the scheme will choose from
     * @param cutoff the cutoff for the maximum color intensity
     * @return a color scheme with the supplied attributes
     */
    public ColorScheme getColorScheme(HighlightMode hm, PreprocessedFn fn,
                                      List<Color> cs, int cutoff) {
        ColorScheme result;
        if (BINARY.equals(hm)) {
            result = new Binary(cs, fn);
        } else if (LERP.equals(hm)) {
            result = new LERP(cs, fn, cutoff);
        } else if (MULTIPLE_OF_BASE.equals(hm)) {
            result = new MultipleOfBase(cs, fn);
        } else {
            throw new IllegalArgumentException("Unknown highlight mode: " + hm);
        }

        return result;
    }
}
