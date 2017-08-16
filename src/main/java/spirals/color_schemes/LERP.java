package spirals.color_schemes;

import javafx.scene.paint.Color;
import spirals.highlighters.PreprocessedFn;

import java.util.List;

/**
 * Created by Kevin on 7/1/2017 for Spirals.
 * <p>
 * Color scheme that linearly interpolates between two colors. An intensity of
 * 0 results in the low color being used completely, while an intensity of
 * cutoff results in the high color being used completely.
 */
public class LERP extends ColorScheme {
    private final Color low;
    private final Color high;

    /**
     * Creates a color scheme which linearly interpolates between two colors.
     *
     * @param colors the colors to choose from
     * @param fn     the function which will determine if values are low or high
     * @param cutoff the cutoff above which all colors have the same value
     */
    public LERP(List<Color> colors, PreprocessedFn fn, int cutoff) {
        super(new spirals.highlight_modes.LERP(), colors, fn, cutoff);
        this.low = getColor(0);
        this.high = getColor(1);
    }

    @Override
    public boolean isLow(int value) {
        return apply(value) <= 0;
    }

    @Override
    public Color computeColor(int value) {
        return low.interpolate(
                high, Math.min((1.0 * apply(value)) / getCutoff(), 1)
        );
    }

    public String toString() {
        return String.format(
                "LERP by %s from %s to %s", getIntensityFunction(), low, high
        );
    }
}
