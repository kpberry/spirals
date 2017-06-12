package com.kpberry.spirals.preprocessors;

import java.util.function.Consumer;

import static com.kpberry.math.Triangular.updateTriangleNumbers;

/**
 * Created by Kevin on 6/11/2017 for Spirals.
 */
public class IdentifyTriangularNumbers implements Consumer<Integer> {
    @Override
    public void accept(Integer spiralLength) {
        updateTriangleNumbers(spiralLength);
    }

    @Override
    public String toString() {
        return "Identify Triangular Numbers";
    }
}
