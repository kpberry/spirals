package com.kpberry.app;

import javafx.collections.ObservableList;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Created by Kevin on 6/16/2017 for Spirals for Spirals.
 *
 */
class ColorCell extends ListCell<Color> {
    public ColorCell() {
        setContentDisplay(ContentDisplay.TEXT_ONLY);

        ListCell thisCell = this;

        setOnDragDetected(event -> {
            if (getItem() == null) {
                return;
            }

            Dragboard db = startDragAndDrop(TransferMode.MOVE);

            ClipboardContent content = new ClipboardContent();
            content.putString(getItem().toString());
            db.setContent(content);

            event.consume();
        });

        setOnDragOver(event -> {
           if (event.getGestureSource() != thisCell
                   && event.getDragboard().hasString()) {
               event.acceptTransferModes(TransferMode.MOVE);
           }

           event.consume();
        });

        setOnDragEntered(event -> {
            if (getItem() == null) {
                return;
            }

            if (event.getGestureSource() != thisCell
                    && event.getDragboard().hasString()) {
                thisCell.setTextFill(Color.GRAY);
            }

            event.consume();
        });

        setOnDragExited(event -> {
            if (getItem() == null) {
                return;
            }

            thisCell.setTextFill(getItem());

            event.consume();
        });

        setOnDragDropped(event -> {
            if (getItem() == null) {
                return;
            }

            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                Color other = Color.valueOf(db.getString());
                Color thisColor = getItem();

                ObservableList<Color> items = getListView().getItems();
                int otherIndex = -1, thisColorIndex = -1;
                for (int i = 0; i < items.size() && otherIndex == -1; i++) {
                    if (items.get(i).toString().equals(other.toString())) {
                        otherIndex = i;
                    }
                }

                for (int i = 0; i < items.size() && thisColorIndex == -1; i++) {
                    if (items.get(i).toString().equals(thisColor.toString())) {
                        thisColorIndex = i;
                    }
                }

                items.set(otherIndex, thisColor);
                items.set(thisColorIndex, other);

                success = true;
            }

            event.setDropCompleted(success);

            event.consume();
        });
    }

    @Override
    protected void updateItem(Color item, boolean empty) {
        if (item == null) {
            return;
        }

        item = Color.valueOf(item.toString());
        super.updateItem(item, empty);
        setText(item.toString());
        setTextFill(item);
        setFont(Font.font("System", FontWeight.BOLD, 12));
    }
}
