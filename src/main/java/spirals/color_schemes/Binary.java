package spirals.color_schemes;

import javafx.scene.paint.Color;
import spirals.highlighters.Highlighter;

import java.util.List;

/**
 * Created by Kevin on 7/1/2017 for Spirals.
 *
 */
public class Binary extends ColorScheme {
    private final Color off;
    private final Color on;

    public Binary(List<Color> colors, Highlighter h, int cutoff) {
        super(new spirals.highlight_modes.Binary(), colors, h, cutoff);
        off = getColor(0);
        on = getColor(1);
    }

    @Override
    public boolean isLow(int value) {
        return applyHighlighter(value) <= 0;
    }

    @Override
    public Color computeColor(int value) {
        return (applyHighlighter(value) > 0) ? off : on;
    }

    public String toString() {
        return String.format(
                "Binary by %s: (%s, %s)", this.getHighlighter(),
                this.getColor(0), this.getColor(1)
        );
    }
}
