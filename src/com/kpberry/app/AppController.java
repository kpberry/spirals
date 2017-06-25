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
import com.kpberry.spirals.inclusion_criteria.Odd;
import com.kpberry.spirals.inclusion_criteria.Prime;
import com.kpberry.spirals.preprocessors.IdentifyPrimeNumbers;
import com.kpberry.spirals.preprocessors.IdentifyTriangularNumbers;
import com.kpberry.spirals.preprocessors.InitializeFactorCounts;
import com.kpberry.spirals.preprocessors.InitializeGoldbachCounts;
import com.kpberry.util.Images;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static com.kpberry.math.Goldbach.goldbachIndex;
import static com.kpberry.math.Primes.factorCount;

/**
 * Created by Kevin on 5/20/2017 for Spirals.
 *
 */
public class AppController implements Initializable {
    @FXML private Canvas spiralCanvas;
    @FXML private TextField spiralLengthField;
    @FXML private TextField elementSizeField;
    @FXML private TextField canvasHeightField;
    @FXML private TextField canvasWidthField;
    @FXML private Slider canvasScaleField;
    @FXML private ColorPicker newColorPicker;
    @FXML private ChoiceBox<ColorScheme> colorSchemeChoiceBox;
    @FXML private ChoiceBox<Drawer> drawerChoiceBox;
    @FXML private ListView<Predicate<Integer>> inclusionCriteriaListView;
    @FXML private ListView<Consumer<Integer>> preprocessorsListView;
    @FXML private ListView<Predicate<Integer>> highlightCriteriaListView;
    @FXML private ListView<Color> colorsListView;
    @FXML private Text infoText;
    @FXML private ScrollPane spiralPane;

    private IntField intSpiralLengthField;
    private IntField intElementSizeField;
    private IntField intCanvasHeightField;
    private IntField intCanvasWidthField;
    private IntField intCanvasScaleField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        intSpiralLengthField = new IntField(spiralLengthField);
        intElementSizeField = new IntField(elementSizeField);
        intCanvasHeightField = new IntField(canvasHeightField);
        intCanvasWidthField = new IntField(canvasWidthField);

        spiralCanvas.setWidth(intCanvasWidthField.getValue());
        spiralCanvas.setHeight(intCanvasHeightField.getValue());

        GraphicsContext mainGC = spiralCanvas.getGraphicsContext2D();
        spiralCanvas.setOnMouseClicked(
                (mouse) -> {
                    Drawer drawer = drawerChoiceBox.getSelectionModel().getSelectedItem();
                    Platform.runLater(() -> {
                        int i = drawer.mousePositionToN(
                                mainGC, intSpiralLengthField.getValue(),
                                mouse.getX(), mouse.getY(),
                                intElementSizeField.getValue(),
                                inclusionCriteriaListView.getSelectionModel()
                                        .getSelectedItem()
                        );
                        infoText.setText("Value: " + i + "\nProperty: " + goldbachIndex(i));
                    });
                }
        );

        ObservableList<Color> colors = colorsListView.getItems();
        colorSchemeChoiceBox.getItems().addAll(
                new Binary(new Prime(), colors),
                new MultipleOfBase(n -> (double) factorCount(n), colors),
                new MultipleOfBase(n -> (double) goldbachIndex(n), colors)
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
                new Odd()
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
                Color.BLACK
        );

        colorsListView.getSelectionModel().select(0);
        colorsListView.setCellFactory(c -> new ColorCell());
        colorsListView.setTooltip(
                new Tooltip("Click and drag to set the order of colors to " +
                        "be used. The top color will be the first " +
                        "used, the second will be the second, etc.")
        );

        spiralCanvas.setOnScroll(e -> {
            if (e.isControlDown()) {
                double mult = (e.getDeltaY() > 0) ? (1 / 0.95) : 0.95;
                canvasScaleField.setValue(canvasScaleField.getValue() * mult);

                e.consume();
            }
        });

        canvasScaleField.valueProperty().addListener(
                (observable, oldValue, newValue) -> {
                    spiralCanvas.setScaleX(Math.pow(10, (newValue.doubleValue() - 5) / 4));
                    spiralCanvas.setScaleY(Math.pow(10, (newValue.doubleValue() - 5) / 4));
                }
        );
    }

    //TODO finish UI update

    /**
     * features to add:
     *      Highlight criteria selector
     *      Color multiplier selector
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
        colorsListView.refresh();
    }

    //TODO finish this
    private ColorScheme getColorScheme() {
        Predicate<Integer> highlightCriterion = new Prime();
        ObservableList<Predicate<Integer>> highlightCriteria
                = highlightCriteriaListView.getSelectionModel().getSelectedItems();
        if (highlightCriteria.size() > 0) {
            highlightCriterion = highlightCriteria.get(0);
        }

        ObservableList<Color> colors = colorsListView.getItems();

        ColorScheme cs = colorSchemeChoiceBox.getValue();
        cs.setColors(colors);

        return cs;
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

        ColorScheme cs = getColorScheme();
        Drawer drawer = drawerChoiceBox.getValue();

        Spiral spiral = new Spiral(drawer, preprocessor, cs, inclusionCriteria);

        drawSpiral(spiral);
    }

    private void drawSpiral(Spiral spiral) {
        int spiralLength = intSpiralLengthField.getValue();
        int elemSize = intElementSizeField.getValue();
        int canvasHeight = intCanvasHeightField.getValue();
        int canvasWidth = intCanvasWidthField.getValue();

        spiralCanvas.setHeight(canvasHeight);
        spiralCanvas.setWidth(canvasWidth);
        spiralPane.setVvalue(0.5);
        spiralPane.setHvalue(0.5);
        GraphicsContext gc = spiralCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvasWidth, canvasHeight);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(10);
        gc.strokeRect(5, 5, canvasWidth - 5, canvasHeight - 5);
        gc.setLineWidth(1);
        Platform.runLater(() -> spiral.draw(gc, spiralLength, elemSize));
    }
}
