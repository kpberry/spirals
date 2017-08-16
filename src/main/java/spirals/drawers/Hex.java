package spirals.drawers;

import javafx.scene.canvas.GraphicsContext;
import math.shapes.Hexagon;
import spirals.color_schemes.ColorScheme;

import java.util.Iterator;
import java.util.function.Predicate;

/**
 * Created by Kevin on 6/11/2017 for Spirals.
 * <p>
 * Class which draws spirals with a hexagon tiling.
 */
public class Hex implements Drawer {
    @Override
    public int mousePositionToN(double mouseX, double mouseY,
                                GraphicsContext gc, int length,
                                double elemSize, Predicate<Integer> ic) {
        double cx = gc.getCanvas().getWidth() / 2;
        double cy = gc.getCanvas().getHeight() / 2;

        Hexagon cur = new Hexagon(elemSize, cx, cy);
        HexIterator iterator = new HexIterator(length, ic, cur);

        while (iterator.hasNext()) {
            cur = iterator.next();
            if (cur.containsPoint(mouseX, mouseY)) {
                return iterator.value;
            }
        }

        return -1;
    }

    @Override
    public void draw(GraphicsContext gc, int length, double elemSize,
                     ColorScheme cs, Predicate<Integer> ic) {
        double x = gc.getCanvas().getWidth() / 2;
        double y = gc.getCanvas().getHeight() / 2;

        Hexagon cur = new Hexagon(elemSize, x, y);
        HexIterator iterator = new HexIterator(length, ic, cur);

        while (iterator.hasNext()) {
            cur = iterator.next();
            cur.fill(gc, cs.computeColor(iterator.value));
        }
    }

    @Override
    public String toString() {
        return "Hex";
    }

    private class HexIterator implements Iterator<Hexagon> {
        private final int length;
        private final Predicate<Integer> ic;
        private Hexagon.TilingDirection direction;
        private int stride;
        private int strideIndex;
        private int index;
        private int value;
        private Hexagon cur;

        HexIterator(int length, Predicate<Integer> ic, Hexagon base) {
            this.length = length;
            this.ic = ic;
            direction = Hexagon.TilingDirection.UP_RIGHT;
            stride = 1;
            strideIndex = 0;
            cur = base;
            index = 0;
            value = 0;
        }

        @Override
        public boolean hasNext() {
            return index < length;
        }

        @Override
        public Hexagon next() {
            Hexagon result = cur;

            while (!ic.test(index) && (index < length)) {
                index++;
            }

            if ((strideIndex == stride)
                    || ((direction == Hexagon.TilingDirection.UP)
                    && (strideIndex == (stride - 1)))) {
                strideIndex = 0;
                int index = direction.ordinal() + 1;
                index %= Hexagon.TilingDirection.values().length;
                direction = Hexagon.TilingDirection.values()[index];
                if (direction.equals(Hexagon.TilingDirection.UP_RIGHT)) {
                    stride += 1;
                } else if ((direction == Hexagon.TilingDirection.UP)
                        && (strideIndex == (stride - 1))) {
                    direction = Hexagon.TilingDirection.UP_LEFT;
                }
            }

            cur = new Hexagon(cur, direction);
            value = index;

            strideIndex++;
            index++;
            return result;
        }
    }
}
