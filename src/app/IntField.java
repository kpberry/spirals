package app;

import javafx.scene.control.TextField;

/**
 * Created by Kevin on 5/21/2017 for Spirals.
 */
public class IntField {
    private final TextField source;

    public IntField(TextField source) {
        this.source = source;
        this.source.textProperty().addListener(
                (obs, oldValue, newValue) -> {
                    try {
                        Integer.parseInt(newValue);
                    } catch (NumberFormatException noe) {
                        source.setText(oldValue);
                    }
                }
        );
    }

    public int getValue() {
        return Integer.parseInt(source.getText());
    }
}
