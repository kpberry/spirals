package spirals.color_schemes;

import javafx.scene.paint.Color;
import spirals.highlighters.Highlighter;

import java.util.List;

/**
 * Created by Kevin on 7/1/2017 for Spirals.
 *
 */
public class MultipleOfBase extends ColorScheme {
    private final Color base;

    public MultipleOfBase(List<Color> colors, Highlighter h, int cutoff) {
        super(new spirals.highlight_modes.MultipleOfBase(), colors, h, cutoff);
        base = getColor(0);
    }

    @Override
    public boolean isLow(int value) {
        return applyHighlighter(value) <= 0;
    }

    @Override
    public Color computeColor(int value) {
        double multiple = applyHighlighter(value);
        double red = multiple * base.getRed();
        red = (red > 1) ? 1 : red;
        double green = multiple * base.getGreen();
        green = (green > 1) ? 1 : green;
        double blue = multiple * base.getBlue();
        blue = (blue > 1) ? 1 : blue;
        return new Color(red, green, blue, 1);
    }

    @Override
    public String toString() {
        return String.format("Multiple of %s by %s", base, getHighlighter());
    }
}
