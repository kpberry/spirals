package com.kpberry.spirals.square;

import com.kpberry.spirals.color_schemes.Binary;
import com.kpberry.spirals.inclusion_criteria.GT_Zero;
import com.kpberry.spirals.inclusion_criteria.Prime;
import com.kpberry.spirals.preprocessors.IdentifyPrimeNumbers;
import javafx.scene.paint.Color;

/**
 * Created by Kevin on 5/21/2017 for Spirals for Spirals.
 *
 */
public class SquareUlamSpiral extends SquareSpiral {
    public SquareUlamSpiral(Color primeColor, Color nonPrimeColor) {
        super(
                new IdentifyPrimeNumbers(),
                new Binary(new Prime(), primeColor, nonPrimeColor),
                new GT_Zero()
        );
    }
}
