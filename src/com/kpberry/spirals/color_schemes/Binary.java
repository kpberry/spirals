package com.kpberry.spirals.color_schemes;

import com.kpberry.spirals.ColorScheme;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created by Kevin on 6/11/2017 for Spirals for Spirals.
 *
 */
public class Binary implements ColorScheme {
    private final Predicate<Integer> criterion;
    private Color primary, secondary;

    public Binary(Predicate<Integer> criterion, List<Color> colors) {
        this.criterion = criterion;
        this.setColors(colors);
    }

    @Override
    public Optional<Color> getNullableColor(int value) {
        return Optional.of(criterion.test(value) ? primary : secondary);
    }

    @Override
    public String toString() {
        return "Binary";
    }

    @Override
    public void setColors(List<Color> colors) {
        if (colors.size() > 0) {
            primary = colors.get(0);
        } else {
            primary = Color.BLACK;
        }

        if (colors.size() > 1) {
            secondary = colors.get(1);
        } else {
            secondary = Color.BLACK;
        }
    }
}
