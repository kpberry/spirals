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
public class MultipleOfBase implements ColorScheme {
    private Color base;
    private final Function<Integer, Double> multiplier;

    public MultipleOfBase(Function<Integer, Double> multiplier, List<Color> colors) {
        this.multiplier = multiplier;
        this.setColors(colors);
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

    @Override
    public void setColors(List<Color> colors) {
        if (colors.size() > 0) {
            base = colors.get(0);
        } else {
            base = Color.BLACK;
        }
    }
}
