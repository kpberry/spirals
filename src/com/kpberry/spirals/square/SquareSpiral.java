package com.kpberry.spirals.square;

import com.kpberry.spirals.ColorScheme;
import com.kpberry.spirals.Spiral;
import com.kpberry.spirals.drawers.Square;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by Kevin on 5/20/2017 for Spirals.
 *
 */
public class SquareSpiral extends Spiral {
    public SquareSpiral(Consumer<Integer> pr, ColorScheme cs,
                        Predicate<Integer> ic) {
        super(new Square(), pr, cs, ic);
    }
}
