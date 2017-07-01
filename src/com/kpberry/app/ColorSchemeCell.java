package com.kpberry.app;

import com.kpberry.spirals.color_schemes.ColorScheme;

/**
 * Created by Kevin on 6/16/2017 for Spirals for Spirals.
 *
 */
class ColorSchemeCell extends DragCell<ColorScheme> {
    public ColorSchemeCell() {

    }

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
