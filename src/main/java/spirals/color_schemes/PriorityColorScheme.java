package spirals.color_schemes;

import javafx.scene.paint.Color;
import spirals.highlight_modes.HighlightMode;
import spirals.highlighters.Highlighter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kevin on 5/23/2017 for Spirals.
 *
 */
public class PriorityColorScheme extends ColorScheme {
    private final ColorScheme[] colorSchemes;

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

    private static Highlighter aggregateHighlighter(ColorScheme[] colorSchemes) {
        return integer -> {
            for (ColorScheme cs : colorSchemes) {
                if (!cs.isLow(integer)) {
                    return cs.applyHighlighter(integer);
                }
            }
            return colorSchemes[colorSchemes.length - 1].applyHighlighter(integer);
        };
    }

    private static int numRequiredColors(ColorScheme[] colorSchemes) {
        int count = 0;
        for (ColorScheme cs : colorSchemes) {
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
