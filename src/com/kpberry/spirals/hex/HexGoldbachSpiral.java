package com.kpberry.spirals.hex;

import com.kpberry.math.Goldbach;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.Optional;

import static com.kpberry.math.Primes.updateFactorCounts;

/**
 * Created by Kevin on 5/21/2017 for Spirals for Spirals.
 *
 */
public class HexGoldbachSpiral extends HexSpiral {

    public HexGoldbachSpiral(Color base) {
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
                n -> true
        );
    }

    @Override
    public void preprocess(int length) {
        updateFactorCounts(length);
        try {
            Goldbach.readGoldbachIndices();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Goldbach.updateGoldbachIndices(length);
    }
}
