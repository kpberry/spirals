package com.kpberry.spirals.drawers;

import com.kpberry.math.Hexagon;
import com.kpberry.spirals.ColorScheme;
import com.kpberry.spirals.Drawer;
import javafx.scene.canvas.GraphicsContext;

import java.util.function.Predicate;

/**
 * Created by Kevin on 6/11/2017 for Spirals.
 */
public class Hex implements Drawer {
    @Override
    public void draw(GraphicsContext gc, int length, double elemSize,
                     ColorScheme cs, Predicate<Integer> ic) {
        double x = gc.getCanvas().getWidth() / 2;
        double y = gc.getCanvas().getHeight() / 2;

        int stride = 1;

        int strideIndex = 0;

        Hexagon cur = new Hexagon(elemSize, x, y, cs.getColor(1));
        cur.fill(gc);
        Hexagon.TilingDirection direction = Hexagon.TilingDirection.UP_RIGHT;
        cur = new Hexagon(cur, direction, cs.getColor(2));
        cur.fill(gc);
        direction = Hexagon.TilingDirection.UP_LEFT;

        for (int i = 0; i < length; i++) {
            if (ic.test(i)) {
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

    @Override
    public String toString() {
        return "Hex";
    }
}
