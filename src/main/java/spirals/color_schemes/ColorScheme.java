package spirals.color_schemes;

import javafx.scene.paint.Color;
import math.preprocessors.Preprocessor;
import spirals.highlight_modes.HighlightMode;
import spirals.highlighters.Highlighter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Kevin on 5/21/2017 for Spirals.
 *
 */
public abstract class ColorScheme {
    private final Highlighter highlighter;
    private final int cutoff;
    private final Color[] colors;
    private final HighlightMode hm;

    public ColorScheme(HighlightMode hm, List<Color> colors, Highlighter h, int cutoff) {
        this.hm = hm;
        this.highlighter = h;
        this.cutoff = cutoff;
        this.colors = sanitizeColors(colors, hm.getNumRequiredColors());
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public abstract boolean isLow(int value);

    public abstract Color computeColor(int value);

    public double applyHighlighter(int n) {
        return highlighter.apply(n);
    }

    public Preprocessor getPreprocessor() {
        return highlighter.getPreprocessor();
    }

    public int getCutoff() {
        return cutoff;
    }

    public Highlighter getHighlighter() {
        return highlighter;
    }

    public Color[] getColors() {
        return colors;
    }

    public Color getColor(int i) {
        return colors[i];
    }

    public int getNumRequiredColors() {
        return hm.getNumRequiredColors();
    }

    public String getName() {
        return hm.getName();
    }

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
        if ((highlighter != null) ? !highlighter.equals(that.highlighter) : (that.highlighter != null))
            return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(colors, that.colors) && ((hm != null) ? hm.equals(that.hm) : (that.hm == null));
    }

    @Override
    public int hashCode() {
        int result = (highlighter != null) ? highlighter.hashCode() : 0;
        result = (31 * result) + cutoff;
        result = (31 * result) + Arrays.hashCode(colors);
        result = (31 * result) + ((hm != null) ? hm.hashCode() : 0);
        return result;
    }
}
