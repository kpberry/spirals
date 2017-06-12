package com.kpberry.spirals.hex;

import com.kpberry.spirals.ColorScheme;
import com.kpberry.spirals.Spiral;
import com.kpberry.spirals.drawers.Hex;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by Kevin on 5/21/2017 for Spirals for Spirals.
 *
 */
public class HexSpiral extends Spiral {
    public HexSpiral(Consumer<Integer> pr, ColorScheme cs, Predicate<Integer> ic) {
        super(new Hex(), pr, cs, ic);
    }
}
