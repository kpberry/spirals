package spirals;

import javafx.scene.paint.Color;

import java.util.Optional;

/**
 * Created by Kevin on 5/23/2017 for Spirals for Spirals.
 *
 */
public class PriorityColorScheme implements ColorScheme {
    private final ColorScheme[] colorSchemes;

    public PriorityColorScheme(ColorScheme[] priorityOrderedColorSchemes) {
        this.colorSchemes = priorityOrderedColorSchemes;
    }

    @Override
    public Optional<Color> getNullableColor(int value) {
        Optional<Color> result;
        for (ColorScheme cs : colorSchemes) {
            result = cs.getNullableColor(value);
            if (result.isPresent()) {
                return result;
            }
        }
        return null;
    }
}
