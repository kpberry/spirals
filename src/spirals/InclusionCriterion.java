package spirals;

/**
 * Created by Kevin on 5/21/2017 for Spirals.
 */
@FunctionalInterface
public interface InclusionCriterion {
    boolean shouldBeIncluded(int value);
}
