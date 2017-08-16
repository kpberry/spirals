package math.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Kevin on 5/21/2017 for Spirals.
 */
public class RegularPolygon {
    private final int numPoints;
    private final double outRadius;
    private final double inRadius;
    private final double[] xPoints;
    private final double[] yPoints;
    private final double centerX;
    private final double centerY;

    /**
     * Makes a regular polygon at a specific x and y location with the specified
     * outer radius
     *
     * @param numPoints the number of vertices that this polygon will have
     * @param outRadius the radius to the outer vertices of the polygon
     * @param x         the x coordinate of the polygon
     * @param y         the y coordinate of the polygon
     */
    public RegularPolygon(int numPoints, double outRadius,
                          double x, double y) {
        this.numPoints = numPoints;
        this.outRadius = outRadius;
        this.xPoints = new double[numPoints];
        this.yPoints = new double[numPoints];
        this.centerX = x;
        this.centerY = y;

        double theta = (Math.PI * 2) / numPoints;
        this.inRadius = Math.cos(theta / 2) * outRadius;
        for (int i = 0; i < numPoints; i++) {
            double curTheta = theta * i;
            xPoints[i] = (Math.cos(curTheta) * outRadius) + x;
            yPoints[i] = (Math.sin(curTheta) * outRadius) + y;
        }
    }

    /**
     * Fills in this polygon in a graphics context with the specified color
     *
     * @param gc    the graphics context in which to draw the shape
     * @param color the color to use when drawing the shape
     */
    public void fill(GraphicsContext gc, Color color) {
        gc.setFill(color);
        gc.fillPolygon(this.getXPoints(), this.getYPoints(), numPoints);
    }

    /**
     * Outlines this polygon in a graphics context with the specified color
     *
     * @param gc    the graphics context in which to draw the shape
     * @param color the color to use when drawing the shape
     */
    public void stroke(GraphicsContext gc, Color color) {
        gc.setStroke(color);
        gc.strokePolygon(this.getXPoints(), this.getYPoints(), numPoints);
    }

    /**
     * Returns the x coordinates of each of this polygon's vertices
     *
     * @return the x coordinates of each of this polygon's vertices
     */
    public double[] getXPoints() {
        return xPoints;
    }

    /**
     * Returns the y coordinates of each of this polygon's vertices
     *
     * @return the y coordinates of each of this polygon's vertices
     */
    public double[] getYPoints() {
        return yPoints;
    }

    /**
     * Returns the outer radius of this polygon
     *
     * @return the outer radius of this polygon
     */
    public double getOutRadius() {
        return outRadius;
    }

    /**
     * Returns the x coordinate of the center of this polygon
     *
     * @return the x coordinate of the center of this polygon
     */
    public double getCenterX() {
        return centerX;
    }

    /**
     * Returns the y coordinate of the center of this polygon
     *
     * @return the y coordinate of the center of this polygon
     */
    public double getCenterY() {
        return centerY;
    }

    /**
     * Returns the inner radius of this polygon
     *
     * @return the inner radius of this polygon
     */
    public double getInRadius() {
        return inRadius;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < numPoints; i++) {
            result.append("(").append(xPoints[i]).append(", ")
                    .append(yPoints[i]).append(") ");
        }
        return result.toString().trim() + "]";
    }
}
