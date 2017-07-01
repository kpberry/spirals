package com.kpberry.app;

import com.kpberry.spirals.color_schemes.ColorScheme;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

/**
 * Created by Kevin on 6/28/2017 for Spirals for Spirals.
 *
 */
public class ColorSchemeBox extends HBox {
    public ColorSchemeBox(ColorScheme cs) {
        setSpacing(5);
        String s = cs.getName() + " [" + cs.getHighlighter() + "]";
        getChildren().add(new Text(s));
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
