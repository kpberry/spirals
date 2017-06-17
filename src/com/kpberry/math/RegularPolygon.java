package com.kpberry.math;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Created by Kevin on 5/21/2017 for Spirals for Spirals.
 *
 */
public class RegularPolygon {
    private final int numPoints;
    private final double outRadius;
    private final double inRadius;
    private double centerX, centerY;
    private final double[] xPoints;
    private final double[] yPoints;

    public RegularPolygon(int numPoints, double outRadius) {
        this(numPoints, outRadius, 0, 0);
    }

    public RegularPolygon(int numPoints, double outRadius,
                          double x, double y) {
        this.numPoints = numPoints;
        this.outRadius = outRadius;
        this.xPoints = new double[numPoints];
        this.yPoints = new double[numPoints];
        this.centerX = x;
        this.centerY = y;

        double theta = Math.PI * 2 / numPoints;
        this.inRadius = Math.cos(theta / 2) * outRadius;
        for (int i = 0; i < numPoints; i++) {
            double curTheta = theta * i;
            xPoints[i] = Math.cos(curTheta) * outRadius + x;
            yPoints[i] = Math.sin(curTheta) * outRadius + y;
        }
    }

    public void fill(GraphicsContext gc, Color color) {
        gc.setFill(color);
        gc.fillPolygon(this.getXPoints(), this.getYPoints(), numPoints);
    }

    public void stroke(GraphicsContext gc, Color color) {
        gc.setStroke(color);
        gc.strokePolygon(this.getXPoints(), this.getYPoints(), numPoints);
    }

    public double[] getXPoints() {
        return xPoints;
    }

    public double[] getYPoints() {
        return yPoints;
    }

    public int getNumPoints() {
        return numPoints;
    }

    public void translate(double x, double y) {
        for (int i = 0; i < numPoints; i++) {
            xPoints[i] += x;
            yPoints[i] += y;
        }
        centerX += x;
        centerY += y;
    }

    public double getOutRadius() {
        return outRadius;
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

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
