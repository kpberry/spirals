package app;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import spirals.color_schemes.ColorScheme;

/**
 * Created by Kevin on 6/28/2017 for Spirals.
 *
 */
public class ColorSchemeBox extends HBox {
    public ColorSchemeBox(ColorScheme cs) {
        setSpacing(5);
        String s = cs.getName() + " [" + cs.getHighlighter() + "]";
        getChildren().add(new Label(s));
        Pane pane = new Pane();
        HBox.setHgrow(pane, Priority.ALWAYS);
        getChildren().add(pane);
        for (int i = 0; i < cs.getNumRequiredColors(); i++) {
            Text c = new Text("███");
            c.setFill(cs.getColors()[i]);
            getChildren().add(c);
        }
    }
}
