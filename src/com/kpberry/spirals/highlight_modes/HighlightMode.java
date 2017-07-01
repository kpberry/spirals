package com.kpberry.spirals.highlight_modes;

/**
 * Created by Kevin on 7/1/2017 for Spirals for Spirals.
 */
public class HighlightMode {
    private final int numRequiredColors;
    private final String name;

    public HighlightMode(String name, int numRequiredColors) {
        this.name = name;
        this.numRequiredColors = numRequiredColors;
    }

    public int getNumRequiredColors() {
        return numRequiredColors;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HighlightMode)) {
            return false;
        }

        HighlightMode that = (HighlightMode) o;

        return (numRequiredColors == that.numRequiredColors)
                && ((name != null) ? name.equals(that.name) : (that.name == null));
    }

    @Override
    public int hashCode() {
        int result = numRequiredColors;
        result = (31 * result) + ((name != null) ? name.hashCode() : 0);
        return result;
    }
}
