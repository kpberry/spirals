package com.kpberry.spirals.square;

import com.kpberry.math.Goldbach;
import com.kpberry.math.Primes;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by Kevin on 5/21/2017 for Spirals for Spirals.
 *
 */
public class SquareGoldbachSpiral extends SquareSpiral {

    public SquareGoldbachSpiral(Color base) {
        super(
                n -> {
                    double count = Math.cbrt(Goldbach.goldbachIndex(n));
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
        Primes.updateFactorCounts(length);
        try {
            Goldbach.readGoldbachIndices();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Goldbach.updateGoldbachIndices(length);
    }
}
