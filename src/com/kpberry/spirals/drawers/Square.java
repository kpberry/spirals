package com.kpberry.spirals.drawers;

import com.kpberry.spirals.ColorScheme;
import com.kpberry.spirals.Drawer;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.function.Predicate;

/**
 * Created by Kevin on 6/11/2017 for Spirals.
 */
public class Square implements Drawer {
    @Override
    public void draw(GraphicsContext gc, int length, double elemSize,
                     ColorScheme cs, Predicate<Integer> ic) {
        Point2D cur = new Point2D(
                (int) (gc.getCanvas().getWidth() / 2),
                (int) (gc.getCanvas().getHeight() / 2)
        );

        Direction direction = Direction.RIGHT;
        int stride = 1;
        int strideIndex = 0;
        for (int i = 0; i < length; i++) {
            if (ic.test(i)) {
                if (strideIndex == stride) {
                    strideIndex = 0;
                    int index = direction.ordinal() + 1;
                    index %= Direction.values().length;
                    direction = Direction.values()[index];
                    if (direction.equals(Direction.RIGHT)
                            || direction.equals(Direction.LEFT)) {
                        stride += 1;
                    }
                }
                gc.setFill(cs.getColor(i));
                gc.fillRect(cur.getX(), cur.getY(), elemSize, elemSize);

                switch (direction) {
                    case UP:
                        cur = cur.add(0, -elemSize);
                        break;
                    case LEFT:
                        cur = cur.add(-elemSize, 0);
                        break;
                    case RIGHT:
                        cur = cur.add(elemSize, 0);
                        break;
                    case DOWN:
                        cur = cur.add(0, elemSize);
                        break;
                }

                strideIndex++;
            }
        }
    }

    private enum Direction {
        RIGHT, UP, LEFT, DOWN
    }
}
