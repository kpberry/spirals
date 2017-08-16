package spirals.color_schemes;

import javafx.scene.paint.Color;
import spirals.highlighters.PreprocessedFn;

import java.util.List;

/**
 * Created by Kevin on 7/1/2017 for Spirals.
 * <p>
 * Color scheme that returns one color for values <= 0 and another color for
 * all other values.
 */
public class Binary extends ColorScheme {
    private final Color off;
    private final Color on;

    /**
     * Creates a binary color scheme which chooses between two colors.
     *
     * @param colors the colors to choose from
     * @param fn     the function which will determine if values are low or high
     */
    Binary(List<Color> colors, PreprocessedFn fn) {
        super(new spirals.highlight_modes.Binary(), colors, fn, -1);
        off = getColor(0);
        on = getColor(1);
    }

    @Override
    public boolean isLow(int value) {
        return apply(value) <= 0;
    }

    @Override
    public Color computeColor(int value) {
        return (apply(value) > 0) ? off : on;
    }

    @Override
    public String toString() {
        return String.format(
                "Binary by %s: (%s, %s)", this.getIntensityFunction(),
                this.getColor(0), this.getColor(1)
        );
    }
}
