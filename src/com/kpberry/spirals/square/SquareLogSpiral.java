package com.kpberry.spirals.square;

import com.kpberry.math.Primes;
import javafx.scene.paint.Color;

import java.util.Optional;

import static com.kpberry.math.Primes.updateFactorCounts;

/**
 * Created by Kevin on 5/21/2017 for Spirals for Spirals.
 *
 */
public class SquareLogSpiral extends SquareSpiral {
    public SquareLogSpiral(Color base) {
        super(
                n -> {
                    int count = Primes.factorCount(n);
                    double red = count * base.getRed();
                    red = red > 1 ? 1 : red;
                    double green = count * base.getGreen();
                    green = green > 1 ? 1 : green;
                    double blue = count * base.getBlue();
                    blue = blue > 1 ? 1 : blue;
                    return Optional.of(new Color(red, green, blue, 1));
                },
                n -> Math.log(n) < Primes.factorCount(n)
        );
    }

    @Override
    public void preprocess(int length) {
        updateFactorCounts(length);
    }
}
