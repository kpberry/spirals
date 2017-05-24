package math;

import javafx.scene.paint.Color;

/**
 * Created by Kevin on 5/21/2017 for Spirals for Spirals.
 *
 */
public class Hexagon extends RegularPolygon {
    public Hexagon(double outRadius) {
        this(outRadius, 0, 0, Color.BLACK);
    }

    public Hexagon(double outRadius, double x, double y, Color color) {
        super(6, outRadius, x, y, color);
    }

    public Hexagon(Hexagon base, TilingDirection dir, Color color) {
        this(base.getOutRadius(),
                base.getTilingXLoc(dir), base.getTilingYLoc(dir),
                color
        );
    }

    private double getTilingXLoc(TilingDirection dir) {
        return dir.xBase * getInRadius() * 2 + getCenterX();
    }

    private double getTilingYLoc(TilingDirection dir) {
        return dir.yBase * getInRadius() * 2 + getCenterY();
    }

    public enum TilingDirection {
        UP_RIGHT(5.5),
        UP(4.5),
        UP_LEFT(3.5),
        DOWN_LEFT(2.5),
        DOWN(1.5),
        DOWN_RIGHT(0.5);


        private static final double baseTheta = Math.PI / 3;
        private final double xBase;
        private final double yBase;

        TilingDirection(double position) {
            final double theta = baseTheta * position;
            this.xBase = Math.cos(theta);
            this.yBase = Math.sin(theta);
        }
    }
}
