package com.kpberry.spirals.color_schemes;

import com.kpberry.spirals.ColorScheme;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by Kevin on 6/11/2017 for Spirals for Spirals.
 *
 */
public class LERP implements ColorScheme {
    private Color high, low;
    private final Function<Integer, Double> multiplier;

    public LERP(Function<Integer, Double> multiplier, List<Color> colors) {
        this.multiplier = multiplier;
        this.setColors(colors);
    }

    @Override
    public Optional<Color> getNullableColor(int value) {
        return Optional.of(
                low.interpolate(high, Math.min(multiplier.apply(value), 1))
        );
    }

    @Override
    public String toString() {
        return "LERP";
    }

    @Override
    public void setColors(List<Color> colors) {
        if (colors.size() > 0) {
            high = colors.get(0);
        } else {
            high = Color.BLACK;
        }

        if (colors.size() > 1) {
            low = colors.get(1);
        } else {
            low = Color.BLACK;
        }
    }
}
