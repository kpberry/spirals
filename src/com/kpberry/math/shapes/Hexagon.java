package com.kpberry.math.shapes;

/**
 * Created by Kevin on 5/21/2017 for Spirals for Spirals for Spirals.
 *
 */
public class Hexagon extends RegularPolygon {
    public Hexagon(double outRadius) {
        this(outRadius, 0, 0);
    }

    public Hexagon(double outRadius, double x, double y) {
        super(6, outRadius, x, y);
    }

    public Hexagon(Hexagon base, TilingDirection dir) {
        this(base.getOutRadius(),
                base.getTilingXLoc(dir), base.getTilingYLoc(dir)
        );
    }

    private double getTilingXLoc(TilingDirection dir) {
        return (dir.xBase * getInRadius() * 2) + getCenterX();
    }

    private double getTilingYLoc(TilingDirection dir) {
        return (dir.yBase * getInRadius() * 2) + getCenterY();
    }

    public boolean containsPoint(double x, double y) {
        x = Math.abs(this.getCenterX() - x);
        y = Math.abs(this.getCenterY() - y);
        double out = this.getOutRadius();
        double in = this.getInRadius();
        if ((2 * x) <= out) {
            return y <= in;
        } else {
            return ((y * out) <= (2 * in * (out - x))) && ((2 * x) >= out);
        }
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
