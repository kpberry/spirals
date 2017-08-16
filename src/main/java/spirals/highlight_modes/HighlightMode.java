package spirals.highlight_modes;

/**
 * Created by Kevin on 7/1/2017 for Spirals.
 * Class to keep track of the name and number of colors which will be used
 * in a color scheme
 */
public class HighlightMode {
    private final int numRequiredColors;
    private final String name;

    /**
     * Creates a highlight mode with the given name and number of required
     * colors
     *
     * @param name              the name of the highlight mode
     * @param numRequiredColors the number of colors required by color schemes
     *                          using this highlight mode
     */
    public HighlightMode(String name, int numRequiredColors) {
        this.name = name;
        this.numRequiredColors = numRequiredColors;
    }

    /**
     * Returns the number of colors a color scheme using this highlight mode
     * would need
     *
     * @return the number of colors a color scheme using this highlight mode
     * would need
     */
    public int getNumRequiredColors() {
        return numRequiredColors;
    }

    /**
     * Returns the name of this highlight mode
     *
     * @return the name of this highlight mode
     */
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

        return (numRequiredColors == that.numRequiredColors) && ((name != null)
                ? name.equals(that.name) : (that.name == null));
    }

    @Override
    public int hashCode() {
        int result = numRequiredColors;
        result = (31 * result) + ((name != null) ? name.hashCode() : 0);
        return result;
    }
}
