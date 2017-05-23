package spirals;

import com.sun.istack.internal.NotNull;
import javafx.scene.paint.Color;

import java.util.Optional;

/**
 * Created by Kevin on 5/21/2017 for Spirals.
 */
@FunctionalInterface
public interface ColorScheme {
    Optional<Color> getNullableColor(int value);

    @NotNull
    default Color getColor(int value) {
        Optional<Color> result = getNullableColor(value);
        return result.orElse(Color.BLACK);
    }
}
