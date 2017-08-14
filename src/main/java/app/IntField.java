package app;

import javafx.scene.control.TextField;

/**
 * Created by Kevin on 5/21/2017 for Spirals.
 *
 */
public class IntField {
    private final TextField source;

    public IntField(TextField source) {
        this.source = source;
        this.source.textProperty().addListener(
                (obs, oldValue, newValue) -> {
                    if (source.getText().isEmpty()) {
                        //do nothing if the text is empty
                        return;
                    }
                    try {
                        /*
                        Tries to parse the number. Upon failing, parseInt
                        throws a NumberFormatException. However, if the result
                        is negative, this should also throw an exception, so
                        this is done manually.
                        */
                        if (Integer.parseInt(newValue) < 0) {
                            throw new NumberFormatException();
                        }
                    } catch (NumberFormatException noe) {
                        source.setText(oldValue);
                    }
                }
        );
    }

    public int getValue() {
        String text = source.getText();
        if (text.isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(source.getText());
        }
    }
}
