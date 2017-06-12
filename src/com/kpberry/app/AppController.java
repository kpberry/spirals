package com.kpberry.app;

import com.kpberry.spirals.ColorScheme;
import com.kpberry.spirals.Drawer;
import com.kpberry.spirals.Spiral;
import com.kpberry.spirals.color_schemes.Binary;
import com.kpberry.spirals.color_schemes.MultipleOfBase;
import com.kpberry.spirals.drawers.Hex;
import com.kpberry.spirals.drawers.Square;
import com.kpberry.spirals.inclusion_criteria.Any;
import com.kpberry.spirals.inclusion_criteria.GT_Zero;
import com.kpberry.spirals.inclusion_criteria.LogN_LT_FC;
import com.kpberry.spirals.inclusion_criteria.Prime;
import com.kpberry.spirals.preprocessors.IdentifyPrimeNumbers;
import com.kpberry.spirals.preprocessors.IdentifyTriangularNumbers;
import com.kpberry.spirals.preprocessors.InitializeFactorCounts;
import com.kpberry.spirals.preprocessors.InitializeGoldbachCounts;
import com.kpberry.util.Images;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static com.kpberry.math.Primes.factorCount;

/**
 * Created by Kevin on 5/20/2017 for Spirals.
 *
 */
public class AppController implements Initializable {
    @FXML private Canvas spiralCanvas;
    @FXML private Canvas textCanvas;
    @FXML private TextField spiralLengthField;
    @FXML private TextField elementSizeField;
    @FXML private TextField canvasHeightField;
    @FXML private TextField canvasWidthField;
    @FXML private ColorPicker newColorPicker;
    @FXML private ChoiceBox<ColorScheme> colorSchemeChoiceBox;
    @FXML private ChoiceBox<Drawer> drawerChoiceBox;
    @FXML private ListView<Predicate<Integer>> inclusionCriteriaListView;
    @FXML private ListView<Consumer<Integer>> preprocessorsListView;
    @FXML private ListView<Predicate<Integer>> highlightCriteriaListView;
    @FXML private ListView<Color> colorsListView;

    private IntField intSpiralLengthField;
    private IntField intElementSizeField;
    private IntField intCanvasHeightField;
    private IntField intCanvasWidthField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        intSpiralLengthField = new IntField(spiralLengthField);
        intElementSizeField = new IntField(elementSizeField);
        intCanvasHeightField = new IntField(canvasHeightField);
        intCanvasWidthField = new IntField(canvasWidthField);

        spiralCanvas.setWidth(intCanvasWidthField.getValue());
        spiralCanvas.setHeight(intCanvasHeightField.getValue());

        GraphicsContext textGC = textCanvas.getGraphicsContext2D();
        /*textCanvas.setOnMouseMoved(
                (mouse) -> {
                    textGC.clearRect(
                            0, 0, textCanvas.getWidth(), textCanvas.getHeight()
                    );

                    textGC.setFill(Color.WHITE);
                    textGC.fillRect(
                            mouse.getX(), mouse.getY(), 200, 200
                    );

                    textGC.setStroke(Color.BLACK);
                    textGC.strokeText(
                            "Hello!", mouse.getX() + 30, mouse.getY() + 30
                    );
                }
        );*/

        colorSchemeChoiceBox.getItems().addAll(
                new Binary(new Prime(), null, null),
                new MultipleOfBase(n -> (double) factorCount(n), Color.RED)
        );

        colorSchemeChoiceBox.getSelectionModel().select(0);

        drawerChoiceBox.getItems().addAll(
                new Square(),
                new Hex()
        );

        drawerChoiceBox.getSelectionModel().select(0);

        inclusionCriteriaListView.getSelectionModel()
                .setSelectionMode(SelectionMode.MULTIPLE);
        preprocessorsListView.getSelectionModel()
                .setSelectionMode(SelectionMode.MULTIPLE);

        inclusionCriteriaListView.getItems().addAll(
                new Any(),
                new GT_Zero(),
                new LogN_LT_FC(),
                new Prime()
        );

        inclusionCriteriaListView.getSelectionModel().select(0);

        preprocessorsListView.getItems().addAll(
                new InitializeFactorCounts(),
                new InitializeGoldbachCounts(),
                new IdentifyPrimeNumbers(),
                new IdentifyTriangularNumbers()
        );

        preprocessorsListView.getSelectionModel().select(0);

        colorsListView.getItems().addAll(
                Color.RED,
                Color.GREEN,
                Color.BLUE,
                Color.GRAY
        );

        colorsListView.getSelectionModel().select(0);
    }

    //TODO finish UI update

    /**
     * features to add:
     *      Inclusion criteria selector
     *      Highlight criteria selector
     *      Color multiplier selector
     *      Color selector
     *      Base color schemes selector
     *      Base color schemes orderer
     */
    //TODO add tooltip for each hexagon to show its rgb and which rules it follows

    @FXML public void saveCanvasAsImage() {
        Images.captureImage(spiralCanvas);
    }

    @FXML public void addColor() {
        if (!colorsListView.getItems().contains(newColorPicker.getValue())) {
            colorsListView.getItems().add(newColorPicker.getValue());
        }
    }

    @FXML public void drawSpiral() {
        Predicate<Integer> inclusionCriteria = (n) -> true;
        for (Predicate<Integer> p
                : inclusionCriteriaListView.getSelectionModel()
                .getSelectedItems()) {
            inclusionCriteria = inclusionCriteria.and(p);
        }

        Consumer<Integer> preprocessor = (n) -> {};
        for (Consumer<Integer> c
                : preprocessorsListView.getSelectionModel()
                .getSelectedItems()) {
            preprocessor = preprocessor.andThen(c);
        }

        Predicate<Integer> highlightCriterion = new Prime();
        ObservableList<Predicate<Integer>> highlightCriteria
                = highlightCriteriaListView.getSelectionModel().getSelectedItems();
        if (highlightCriteria.size() > 0) {
            highlightCriterion = highlightCriteria.get(0);
        }

        Color primary = Color.RED;
        Color secondary = Color.BLACK;
        ObservableList<Color> colors = colorsListView.getSelectionModel().getSelectedItems();
        if (colors.size() > 0) {
            primary = colors.get(0);
        }
        if (colors.size() > 1) {
            secondary = colors.get(1);
        }

        ColorScheme cs = colorSchemeChoiceBox.getValue();
        if (cs instanceof Binary) {
            cs = new Binary(highlightCriterion, primary, secondary);
        } else {
            cs = new MultipleOfBase(t -> (double) factorCount(t), primary);
        }

        Spiral spiral = new Spiral(
                drawerChoiceBox.getValue(),
                preprocessor,
                cs,
                inclusionCriteria
        );

        drawSpiral(spiral);
    }

    public void drawSpiral(Spiral spiral) {
        int spiralLength = intSpiralLengthField.getValue();
        int elemSize = intElementSizeField.getValue();
        int canvasHeight = intCanvasHeightField.getValue();
        int canvasWidth = intCanvasWidthField.getValue();

        spiralCanvas.setHeight(canvasHeight);
        spiralCanvas.setWidth(canvasWidth);
        GraphicsContext gc = spiralCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvasWidth, canvasHeight);
        spiral.draw(gc, spiralLength, elemSize);
    }
}
