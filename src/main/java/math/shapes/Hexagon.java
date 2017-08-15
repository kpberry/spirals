package math.shapes;

/**
 * Created by Kevin on 5/21/2017 for Spirals.
 *
 * Represents a hexagon and computes its points and tiling neighbors.
 */
public class Hexagon extends RegularPolygon {
    /**
     * Makes a hexagon at a specific x and y location with the specified
     * outer radius
     *
     * @param outRadius the radius to the outer vertices of the hexagon
     * @param x         the x coordinate of the hexagon
     * @param y         the y coordinate of the hexagon
     */
    public Hexagon(double outRadius, double x, double y) {
        super(6, outRadius, x, y);
    }

    /**
     * Creates a hexagon adjacent to a base hexagon in a specified tiling
     * direction
     * @param base the hexagon from which the new hexagon will be made
     * @param dir the direction in which the new hexagon will be made
     *            (up-right, up, up-left, down-left, down, or down-right)
     */
    public Hexagon(Hexagon base, TilingDirection dir) {
        this(base.getOutRadius(),
                base.getTilingXLoc(dir), base.getTilingYLoc(dir)
        );
    }

    /**
     * Returns the x location for a new hexagon tiled off of this one in the
     * specified direction
     * @param dir the direction in which the new hexagon will be made
     *            (up-right, up, up-left, down-left, down, or down-right)
     * @return the new hexagon's x location
     */
    private double getTilingXLoc(TilingDirection dir) {
        return (dir.xBase * getInRadius() * 2) + getCenterX();
    }

    /**
     * Returns the y location for a new hexagon tiled off of this one in the
     * specified direction
     * @param dir the direction in which the new hexagon will be made
     *            (up-right, up, up-left, down-left, down, or down-right)
     * @return the new hexagon's y location
     */
    private double getTilingYLoc(TilingDirection dir) {
        return (dir.yBase * getInRadius() * 2) + getCenterY();
    }

    /**
     * Returns whether or not a specific (x, y) coordinate is within this
     * hexagon
     * @param x the x coordinate to check
     * @param y the y coordinate to check
     * @return whether or not the coordinate is within this hexagon
     */
    public boolean containsPoint(double x, double y) {
        //center x and y; no need to create new variables for this
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

    /**
     * Enum specifying in which direction a hexagon can have a tiled neighbor.
     */
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

        /**
         * Constructs a tiling direction using the supplied position to compute
         * the angle and offset from a base hexagon
         * @param position the value by which the base angle of PI / 3 radians
         *                 will be multiplied to get the actual tiling angle
         */
        TilingDirection(double position) {
            final double theta = baseTheta * position;
            this.xBase = Math.cos(theta);
            this.yBase = Math.sin(theta);
        }
    }
}
