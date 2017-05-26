package spirals;

import javafx.scene.canvas.GraphicsContext;

/**
 * Created by Kevin on 5/21/2017 for Spirals for Spirals.
 *
 */
public abstract class Spiral {
    private ColorScheme cs;
    private InclusionCriterion ic;

    public Spiral(ColorScheme cs, InclusionCriterion ic) {
        this.cs = cs;
        this.ic = ic;
    }

    public abstract void draw(
            GraphicsContext gc, int spiralLength, double elemSize
    );

    public abstract void preprocess(int length);

    public ColorScheme getCs() {
        return cs;
    }

    public void setCs(ColorScheme cs) {
        this.cs = cs;
    }

    public InclusionCriterion getIc() {
        return ic;
    }

    public void setIc(InclusionCriterion ic) {
        this.ic = ic;
    }
}
