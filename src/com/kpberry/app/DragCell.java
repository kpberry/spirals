package com.kpberry.app;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kevin on 6/25/2017 for Spirals for Spirals for Spirals.
 *
 */
public class DragCell<T> extends ListCell<T> {
    private static final Map<String, Object> clipboard = new HashMap<>();

    public DragCell() {
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
            if ((event.getGestureSource() != thisCell)
                    && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }

            event.consume();
        });

        setOnDragEntered(event -> {
            if (getItem() == null) {
                return;
            }

            if ((event.getGestureSource() != thisCell)
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
                @SuppressWarnings("unchecked") T other
                        = (T) clipboard.get(db.getString());
                T thisItem = getItem();

                ObservableList<T> items = getListView().getItems();
                int otherIndex = -1;
                int thisItemIndex = -1;
                for (int i = 0; (i < items.size()) && (otherIndex == -1); i++) {
                    if (items.get(i).toString().equals(other.toString())) {
                        otherIndex = i;
                    }
                }

                for (int i = 0; (i < items.size()) && (thisItemIndex == -1); i++) {
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
        super.updateItem(item, empty);
    }
}
