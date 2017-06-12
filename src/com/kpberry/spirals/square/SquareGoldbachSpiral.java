package com.kpberry.spirals.square;

import com.kpberry.math.Goldbach;
import com.kpberry.spirals.color_schemes.MultipleOfBase;
import com.kpberry.spirals.inclusion_criteria.LogN_LT_FC;
import com.kpberry.spirals.preprocessors.InitializeGoldbachCounts;
import javafx.scene.paint.Color;

/**
 * Created by Kevin on 5/21/2017 for Spirals for Spirals.
 *
 */
public class SquareGoldbachSpiral extends SquareSpiral {

    public SquareGoldbachSpiral(Color base) {
        super(
                new InitializeGoldbachCounts(),
                new MultipleOfBase(n -> Math.cbrt(Goldbach.goldbachIndex(n)), base),
                new LogN_LT_FC()
        );
    }
}
