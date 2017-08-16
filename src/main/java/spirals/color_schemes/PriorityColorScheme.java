package spirals.color_schemes;

import javafx.scene.paint.Color;
import spirals.highlight_modes.HighlightMode;
import spirals.highlighters.PreprocessedFn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kevin on 5/23/2017 for Spirals.
 * <p>
 * Class that aggregates an array of color schemes and evaluates each in
 * sequence until a non-low color is found. If no non-low color is found,
 * returns black.
 */
public class PriorityColorScheme extends ColorScheme {
    private final ColorScheme[] colorSchemes;

    /**
     * Constructs a new priority color scheme from an array of other color
     * schemes, ordered by priority.
     *
     * @param priorityOrderedColorSchemes the color schemes to aggregate
     */
    public PriorityColorScheme(ColorScheme[] priorityOrderedColorSchemes) {
        super(
                new HighlightMode(
                        "Priority Aggregate",
                        numRequiredColors(priorityOrderedColorSchemes)
                ), allColors(priorityOrderedColorSchemes),
                aggregateHighlighter(priorityOrderedColorSchemes),
                priorityOrderedColorSchemes[0].getCutoff()
        );
        int length = priorityOrderedColorSchemes.length;
        this.colorSchemes = Arrays.copyOf(priorityOrderedColorSchemes, length);
    }

    private static List<Color> allColors(ColorScheme[] colorSchemes) {
        List<Color> colors = new ArrayList<>();
        for (ColorScheme cs : colorSchemes) {
            colors.addAll(Arrays.asList(cs.getColors()));
        }
        return colors;
    }

    private static PreprocessedFn
    aggregateHighlighter(ColorScheme[] colorSchemes) {
        return integer -> {
            for (final ColorScheme cs : colorSchemes) {
                if (!cs.isLow(integer)) {
                    return cs.apply(integer);
                }
            }
            final ColorScheme cs = colorSchemes[colorSchemes.length - 1];
            return cs.apply(integer);
        };
    }

    private static int numRequiredColors(ColorScheme[] colorSchemes) {
        int count = 0;
        for (final ColorScheme cs : colorSchemes) {
            count += cs.getNumRequiredColors();
        }
        return count;
    }

    @Override
    public boolean isLow(int value) {
        for (ColorScheme cs : colorSchemes) {
            if (!cs.isLow(value)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Color computeColor(int value) {
        for (ColorScheme cs : colorSchemes) {
            if (!cs.isLow(value)) {
                return cs.computeColor(value);
            }
        }
        return colorSchemes[colorSchemes.length - 1].computeColor(value);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Priority Aggregate of ");
        for (ColorScheme cs : colorSchemes) {
            result.append(cs).append(", ");
        }
        return result.substring(0, result.length() - 2);
    }
}
