package com.kpberry.spirals.color_schemes;

import com.kpberry.spirals.base.ColorScheme;
import com.kpberry.spirals.base.ColorSchemeFactory;
import com.kpberry.spirals.base.Highlighter;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kevin on 5/23/2017 for Spirals for Spirals.
 *
 */
public class PriorityColorScheme extends ColorScheme {
    private final ColorScheme[] colorSchemes;

    public PriorityColorScheme(ColorScheme[] priorityOrderedColorSchemes) {
        super(
                allColors(priorityOrderedColorSchemes),
                reconstructedFactory(priorityOrderedColorSchemes),
                aggregateHighlighter(priorityOrderedColorSchemes),
                priorityOrderedColorSchemes[0].getCutoff()
        );
        this.colorSchemes = priorityOrderedColorSchemes;
    }

    private static Color[] allColors(ColorScheme[] colorSchemes) {
        List<Color> colors = new ArrayList<>();
        for (ColorScheme cs : colorSchemes) {
            colors.addAll(Arrays.asList(cs.getColors()));
        }
        return colors.toArray(new Color[colors.size()]);
    }

    private static Highlighter aggregateHighlighter(ColorScheme[] colorSchemes) {
        return integer -> {
            for (ColorScheme cs : colorSchemes) {
                if (!cs.isLow(integer)) {
                    return cs.getHighlighter().apply(integer);
                }
            }
            return colorSchemes[colorSchemes.length - 1].getHighlighter().apply(integer);
        };
    }

    private static ColorSchemeFactory reconstructedFactory(ColorScheme[] colorSchemes) {
        return new ColorSchemeFactory() {
            @Override
            public int getNumRequiredColors() {
                return allColors(colorSchemes).length;
            }

            @Override
            public ColorScheme getColorScheme(Highlighter h, List<Color> cs, int cutoff) {
                return null;
            }
        };
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
    public Color getColor(int value) {
        for (ColorScheme cs : colorSchemes) {
            if (!cs.isLow(value)) {
                return cs.getColor(value);
            }
        }
        return colorSchemes[colorSchemes.length - 1].getColor(value);
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
