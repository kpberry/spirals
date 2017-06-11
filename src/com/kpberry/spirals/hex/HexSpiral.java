package com.kpberry.spirals.hex;

import com.kpberry.math.Hexagon;
import com.kpberry.spirals.ColorScheme;
import com.kpberry.spirals.InclusionCriterion;
import com.kpberry.spirals.Spiral;
import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Kevin on 5/21/2017 for Spirals for Spirals.
 *
 */
public abstract class HexSpiral extends Spiral {

    public HexSpiral(ColorScheme cs, InclusionCriterion ic) {
        super(cs, ic);
    }

    @Override
    public void draw(GraphicsContext gc, int length, double elemSize) {
        preprocess(length);

        double x = gc.getCanvas().getWidth() / 2;
        double y = gc.getCanvas().getHeight() / 2;

        int stride = 1;

        int strideIndex = 0;
        final ColorScheme cs = this.getCs();
        final InclusionCriterion ic = this.getIc();

        Hexagon cur = new Hexagon(elemSize, x, y, getCs().getColor(1));
        cur.fill(gc);
        Hexagon.TilingDirection direction = Hexagon.TilingDirection.UP_RIGHT;
        cur = new Hexagon(cur, direction, cs.getColor(2));
        cur.fill(gc);
        direction = Hexagon.TilingDirection.UP_LEFT;

        for (int i = 0; i < length; i++) {
            if (ic.shouldBeIncluded(i)) {
                if (strideIndex == stride || direction == Hexagon.TilingDirection.UP && strideIndex == stride - 1) {
                    strideIndex = 0;
                    int index = direction.ordinal() + 1;
                    index %= Hexagon.TilingDirection.values().length;
                    direction = Hexagon.TilingDirection.values()[index];
                    if (direction.equals(Hexagon.TilingDirection.UP_RIGHT)) {
                        stride += 1;
                    }
                }

                cur = new Hexagon(cur, direction, cs.getColor(i));
                cur.fill(gc);
                strideIndex++;
            }
        }
    }
}
