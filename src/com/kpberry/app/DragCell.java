package com.kpberry.app;

import javafx.collections.ObservableList;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kevin on 6/25/2017 for Spirals.
 */
public class DragCell<T> extends ListCell<T> {
    private static Map<String, Object> clipboard = new HashMap<>();



    public DragCell() {
        setContentDisplay(ContentDisplay.TEXT_ONLY);

        ListCell thisCell = this;

        setOnDragDetected(event -> {
            if (getItem() == null) {
                return;
            }

            Dragboard db = startDragAndDrop(TransferMode.MOVE);

            ClipboardContent content = new ClipboardContent();
            content.putString(getItem().toString());
            clipboard.put(getItem().toString(), getItem());
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

            setTextFill(Color.BLACK);

            event.consume();
        });

        setOnDragDropped(event -> {
            if (getItem() == null) {
                return;
            }

            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                T other = (T) clipboard.get(db.getString());
                T thisItem = getItem();

                ObservableList<T> items = getListView().getItems();
                int otherIndex = -1, thisItemIndex = -1;
                for (int i = 0; i < items.size() && otherIndex == -1; i++) {
                    if (items.get(i).toString().equals(other.toString())) {
                        otherIndex = i;
                    }
                }

                for (int i = 0; i < items.size() && thisItemIndex == -1; i++) {
                    if (items.get(i).toString().equals(thisItem.toString())) {
                        thisItemIndex = i;
                    }
                }

                items.set(otherIndex, thisItem);
                items.set(thisItemIndex, other);

                clipboard.remove(db.getString());

                success = true;
            }

            event.setDropCompleted(success);

            event.consume();
        });

        setOnDragDone(event -> {
            clipboard.clear();
            event.consume();
        });
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        if (item == null) {
            return;
        }

        super.updateItem(item, empty);
        setText(item.toString());
        //setFont(Font.font("System", FontWeight.BOLD, 12));
    }
}
