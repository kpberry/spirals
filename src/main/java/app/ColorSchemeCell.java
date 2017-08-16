package app;

import spirals.color_schemes.ColorScheme;

/**
 * Created by Kevin on 6/16/2017 for Spirals.
 * List cell which displays a color scheme.
 */
class ColorSchemeCell extends DragCell<ColorScheme> {
    @Override
    protected void updateItem(ColorScheme item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null) {
            setGraphic(null);
            return;
        }

        setGraphic(new ColorSchemeBox(item));
    }
}
