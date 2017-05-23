package spirals.square;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ProgressBar;
import spirals.ColorScheme;
import spirals.InclusionCriterion;
import spirals.Spiral;

/**
 * Created by Kevin on 5/20/2017 for Spirals.
 *
 */
public abstract class SquareSpiral extends Spiral {
    public SquareSpiral(ColorScheme cs, InclusionCriterion ic) {
        super(cs, ic);
    }

    @Override
    public void draw(GraphicsContext gc, int length, double elemSize, ProgressBar progressBar) {
        preprocess(length);

        Point2D cur = new Point2D(
                (int) (gc.getCanvas().getWidth() / 2),
                (int) (gc.getCanvas().getHeight() / 2)
        );

        Direction direction = Direction.RIGHT;
        double progressBase = 1.0 / length;
        int stride = 1;
        int strideIndex = 0;
        final ColorScheme cs = this.getCs();
        final InclusionCriterion ic = this.getIc();
        for (int i = 0; i < length; i++) {
            if (ic.shouldBeIncluded(i)) {
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

            if (progressBar != null) {
                progressBar.setProgress(progressBase * i);
            }
        }
    }

    private enum Direction {
        RIGHT, UP, LEFT, DOWN
    }
}
