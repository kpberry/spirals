package com.kpberry.spirals.color_schemes;

import com.kpberry.spirals.ColorScheme;
import javafx.scene.paint.Color;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created by Kevin on 6/11/2017 for Spirals.
 */
public class Binary implements ColorScheme {
    private final Predicate<Integer> criterion;
    private final Color primary;
    private final Color secondary;

    public Binary(Predicate<Integer> criterion, Color primary, Color secondary) {
        this.criterion = criterion;
        this.primary = primary;
        this.secondary = secondary;
    }

    @Override
    public Optional<Color> getNullableColor(int value) {
        return Optional.of(criterion.test(value) ? primary : secondary);
    }

    @Override
    public String toString() {
        return "Binary: " + primary + ", " + secondary;
    }
}
