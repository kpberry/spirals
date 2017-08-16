package spirals.color_schemes;

import javafx.scene.paint.Color;
import math.preprocessors.Preprocessor;
import spirals.highlight_modes.HighlightMode;
import spirals.highlighters.PreprocessedFn;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Kevin on 5/21/2017 for Spirals.
 * <p>
 * Class that combines a highlight mode, a list of colors, a function with
 * a color intensity output from 0 to 1, and a value cutoff to completely
 * determine how a numbers in a sequence should be colored.
 */
public abstract class ColorScheme implements Function<Integer, Double> {
    private final PreprocessedFn fn;
    private final int cutoff;
    private final Color[] colors;
    private final HighlightMode hm;

    /**
     * Constructs a new color scheme.
     *
     * @param hm     the highlight mode which determines the number of colors
     *               to use
     * @param colors the colors to be chosen from when highlighting
     * @param fn     a function from integers
     * @param cutoff the cutoff to use when scaling color values
     */
    public ColorScheme(HighlightMode hm, List<Color> colors,
                       PreprocessedFn fn, int cutoff) {
        this.hm = hm;
        this.fn = fn;
        this.cutoff = cutoff;
        //truncates colors to the correct length and fills in black for any
        //unsupplied colors.
        this.colors = sanitizeColors(colors, hm.getNumRequiredColors());
    }

    /**
     * Returns whether or not this color has a "low value", meaning that the
     * highlight criterion is false.
     *
     * @param value the number to check
     * @return whether or not value is low
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public abstract boolean isLow(int value);

    /**
     * Applies this color scheme's color intensity function to an int value.
     * Should return an intensity result from 0 to 1.
     *
     * @param integer the value for which an intensity will be computed
     * @return the intensity for integer
     */
    @Override
    public Double apply(Integer integer) {
        return fn.apply(integer);
    }

    /**
     * Computes the color that should be used for a particular value
     *
     * @param value the value to check
     * @return the color that should be used for value
     */
    public abstract Color computeColor(int value);

    /**
     * Returns the preprocessor which should be run before computing sequence
     * values.
     *
     * @return this color scheme's preprocessor
     */
    public Preprocessor getPreprocessor() {
        return fn.getPreprocessor();
    }

    /**
     * Gets the maximum value of the range of this color scheme's intensity
     * function. Values above this cutoff should all return an intensity of 1.
     *
     * @return the intensity value cutoff
     */
    public int getCutoff() {
        return cutoff;
    }

    /**
     * Returns the function used to calculate intensities for sequence values
     *
     * @return the intensity function
     */
    public PreprocessedFn getIntensityFunction() {
        return fn;
    }

    /**
     * Returns the colors that this color scheme may use
     *
     * @return the colors that this color scheme may use
     */
    public Color[] getColors() {
        return colors;
    }

    /**
     * Returns the ith color from this color scheme's fixed array of colors
     *
     * @param i the index of the color to get
     * @return the ith color from this scheme's color array
     */
    public Color getColor(int i) {
        return colors[i];
    }

    /**
     * Returns the number of colors required to properly use this color scheme
     *
     * @return the number of colors required to properly use this color scheme
     */
    public int getNumRequiredColors() {
        return hm.getNumRequiredColors();
    }

    /**
     * Returns the display name of this color scheme
     *
     * @return the display name of this color scheme
     */
    public String getName() {
        return hm.getName();
    }

    /**
     * Returns this color scheme's highlight mode, which is used to determine
     * how the color intensity is interpreted.
     *
     * @return this color scheme's highlight mode
     */
    public HighlightMode getHighlightMode() {
        return hm;
    }

    private Color[] sanitizeColors(List<Color> colors, int numRequiredColors) {
        int numColors = colors.size();
        Color[] result = new Color[numRequiredColors];
        for (int i = 0; i < result.length; i++) {
            if (i < numColors) {
                result[i] = colors.get(i);
            } else {
                result[i] = Color.BLACK;
            }
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ColorScheme)) return false;

        ColorScheme that = (ColorScheme) o;

        if (cutoff != that.cutoff) return false;
        if ((fn != null) ? !fn.equals(that.fn)
                : (that.fn != null))
            return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(colors, that.colors)
                && ((hm != null) ? hm.equals(that.hm) : (that.hm == null));
    }

    @Override
    public int hashCode() {
        int result = (fn != null) ? fn.hashCode() : 0;
        result = (31 * result) + cutoff;
        result = (31 * result) + Arrays.hashCode(colors);
        result = (31 * result) + ((hm != null) ? hm.hashCode() : 0);
        return result;
    }
}
