package com.kpberry.spirals.color_schemes;

import com.kpberry.spirals.ColorScheme;
import javafx.scene.paint.Color;

import java.util.Optional;
import java.util.function.Function;

/**
 * Created by Kevin on 6/11/2017 for Spirals.
 */
public class MultipleOfBase implements ColorScheme {
    private final Color base;
    private final Function<Integer, Double> multiplier;

    public MultipleOfBase(Function<Integer, Double> multiplier, Color base) {
        this.base = base;
        this.multiplier = multiplier;
    }

    @Override
    public Optional<Color> getNullableColor(int value) {
        double multiple = multiplier.apply(value);
        double red = multiple * base.getRed();
        red = red > 1 ? 1 : red;
        double green = multiple * base.getGreen();
        green = green > 1 ? 1 : green;
        double blue = multiple * base.getBlue();
        blue = blue > 1 ? 1 : blue;
        return Optional.of(new Color(red, green, blue, 1));
    }

    @Override
    public String toString() {
        return "Multiple of Base: " + base;
    }
}
