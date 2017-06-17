package com.kpberry.spirals.drawers;

import com.kpberry.spirals.ColorScheme;
import com.kpberry.spirals.Drawer;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.Iterator;
import java.util.function.Predicate;

/**
 * Created by Kevin on 6/11/2017 for Spirals.
 */
public class Square implements Drawer {
    private enum Direction {
        RIGHT, UP, LEFT, DOWN
    }

    public int mousePositionToN(GraphicsContext gc, int length,
                                double mouseX, double mouseY,
                                double elemSize, Predicate<Integer> ic) {
        double centerX = gc.getCanvas().getWidth() / 2;
        double centerY = gc.getCanvas().getHeight() / 2;
        int x = (int) Math.floor((mouseX - centerX) / elemSize);
        int y = (int) Math.floor((mouseY - centerY) / elemSize);
        Point2D cur;

        SquareIterator iterator = new SquareIterator(length, ic);
        while (iterator.hasNext()) {
            cur = iterator.next();
            if (cur.getX() == x && cur.getY() == y) {
                return iterator.value;
            }
        }

        return -1;
    }

    @Override
    public void draw(GraphicsContext gc, int length, double elemSize,
                     ColorScheme cs, Predicate<Integer> ic) {
        int x = (int) (gc.getCanvas().getWidth() / 2);
        int y = (int) (gc.getCanvas().getHeight() / 2);
        Point2D cur;

        SquareIterator iterator = new SquareIterator(length, ic);
        while (iterator.hasNext()) {
            cur = iterator.next().multiply(elemSize).add(x, y);
            gc.setFill(cs.getColor(iterator.value));
            gc.fillRect(cur.getX(), cur.getY(), elemSize, elemSize);
        }
    }

    private class SquareIterator implements Iterator<Point2D> {
        private int length;
        private Predicate<Integer> ic;
        private Direction direction;
        private int stride;
        private int strideIndex;
        private int index;
        private int value;
        private int drawn;
        private Point2D cur;

        public SquareIterator(int length, Predicate<Integer> ic) {
            this.length = length;
            this.ic = ic;
            direction = Direction.RIGHT;
            stride = 1;
            strideIndex = 0;
            cur = new Point2D(0, 0);
            index = 0;
            value = 0;
            drawn = 0;
        }

        @Override
        public boolean hasNext() {
            return drawn < length;
        }

        @Override
        public Point2D next() {
            Point2D prev = cur;

            while (!ic.test(index)) {
                index++;
            }

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

            switch (direction) {
                case UP:
                    cur = cur.add(0, -1);
                    break;
                case LEFT:
                    cur = cur.add(-1, 0);
                    break;
                case RIGHT:
                    cur = cur.add(1, 0);
                    break;
                case DOWN:
                    cur = cur.add(0, 1);
                    break;
            }

            strideIndex++;
            drawn++;
            value = index;
            index++;
            return prev;
        }
    }

    @Override
    public String toString() {
        return "Square";
    }
}
