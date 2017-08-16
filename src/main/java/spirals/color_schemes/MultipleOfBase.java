package spirals.color_schemes;

import javafx.scene.paint.Color;
import spirals.highlighters.PreprocessedFn;

import java.util.List;

/**
 * Created by Kevin on 7/1/2017 for Spirals.
 * Color scheme which multiplies a base color by an intensity value.
 */
public class MultipleOfBase extends ColorScheme {
    private final Color base;

    /**
     * Creates a color scheme which multiplies a base color by intensity.
     *
     * @param colors list which should contain one color to use
     * @param fn     the function which will determine the color's intensity
     */
    public MultipleOfBase(List<Color> colors, PreprocessedFn fn) {
        super(new spirals.highlight_modes.MultipleOfBase(), colors, fn, -1);
        base = getColor(0);
    }

    @Override
    public boolean isLow(int value) {
        return apply(value) <= 0;
    }

    @Override
    public Color computeColor(int value) {
        double multiple = apply(value);
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
        return String.format(
                "Multiple of %s by %s", base, getIntensityFunction()
        );
    }
}
