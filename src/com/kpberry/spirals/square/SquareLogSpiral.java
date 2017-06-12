package com.kpberry.spirals.square;

import com.kpberry.math.Primes;
import com.kpberry.spirals.color_schemes.MultipleOfBase;
import com.kpberry.spirals.inclusion_criteria.LogN_LT_FC;
import com.kpberry.spirals.preprocessors.InitializeFactorCounts;
import javafx.scene.paint.Color;

/**
 * Created by Kevin on 5/21/2017 for Spirals for Spirals.
 *
 */
public class SquareLogSpiral extends SquareSpiral {
    public SquareLogSpiral(Color base) {
        super(
                new InitializeFactorCounts(),
                new MultipleOfBase(n -> (double) Primes.factorCount(n), base),
                new LogN_LT_FC()
        );
    }
}
