package com.kpberry.app;

import com.kpberry.math.inclusion_criteria.Any;
import com.kpberry.math.inclusion_criteria.Even;
import com.kpberry.math.inclusion_criteria.GT_Zero;
import com.kpberry.math.inclusion_criteria.InclusionCriterion;
import com.kpberry.math.inclusion_criteria.LogN_LT_FC;
import com.kpberry.math.inclusion_criteria.Odd;
import com.kpberry.math.preprocessors.BulkPreprocess;
import com.kpberry.math.preprocessors.Preprocessor;
import com.kpberry.spirals.Spiral;
import com.kpberry.spirals.color_schemes.ColorScheme;
import com.kpberry.spirals.color_schemes.ColorSchemeFactory;
import com.kpberry.spirals.color_schemes.PriorityColorScheme;
import com.kpberry.spirals.drawers.Drawer;
import com.kpberry.spirals.drawers.Hex;
import com.kpberry.spirals.drawers.Square;
import com.kpberry.spirals.highlight_modes.Binary;
import com.kpberry.spirals.highlight_modes.HighlightMode;
import com.kpberry.spirals.highlight_modes.LERP;
import com.kpberry.spirals.highlight_modes.MultipleOfBase;
import com.kpberry.spirals.highlighters.CollatzLength;
import com.kpberry.spirals.highlighters.FactorCount;
import com.kpberry.spirals.highlighters.GoldbachCount;
import com.kpberry.spirals.highlighters.Highlighter;
import com.kpberry.spirals.highlighters.IsPrime;
import com.kpberry.spirals.highlighters.IsTriangular;
import com.kpberry.util.Images;
import com.kpberry.util.InclusionCriterionFactory;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import static javafx.scene.paint.Color.BLACK;

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
    @FXML
    private ChoiceBox<HighlightMode> highlightModeChoiceBox;
    @FXML private ChoiceBox<Drawer> drawerChoiceBox;
    @FXML private ChoiceBox<Highlighter> highlighterChoiceBox;
    @FXML private GridPane colorSelectionGrid;
    @FXML private TextField cutoffTextField;
    @FXML private ListView<InclusionCriterion> inclusionCriteriaListView;
    @FXML private ListView<ColorScheme> selectedColorSchemes;
    @FXML private ListView<ColorScheme> availableColorSchemes;
    @FXML private Text infoText;
    @FXML private ScrollPane spiralPane;
    @FXML private Button updateColorSchemeButton;
    @FXML private ProgressBar spiralProgress;
    @FXML
    private TextField customInclusionCriterion;
    @FXML
    private TextField variableTextField;

    private IntField intSpiralLengthField;
    private IntField intElementSizeField;
    private IntField intCanvasHeightField;
    private IntField intCanvasScaleField;
    private IntField intCutoffTextField;

    private ObservableList<ColorPicker> colorPickers;
    private Spiral current;

    private final double scalingRate = 0.95;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        intSpiralLengthField = new IntField(spiralLengthField);
        intElementSizeField = new IntField(elementSizeField);
        intCanvasHeightField = new IntField(canvasHeightField);
        intCanvasScaleField = new IntField(canvasWidthField);
        intCutoffTextField = new IntField(cutoffTextField);

        spiralCanvas.setWidth(intCanvasScaleField.getValue());
        spiralCanvas.setHeight(intCanvasHeightField.getValue());

        customInclusionCriterion.setOnAction(
                event -> {
                    try {
                        InclusionCriterionFactory icf =
                                new InclusionCriterionFactory(
                                        customInclusionCriterion.getText(),
                                        variableTextField.getText()
                                );
                        if (icf.hasCompileErrors()) {
                            infoText.setText(icf.getCompileErrors());
                        } else {
                            this.inclusionCriteriaListView.getItems().add(
                                    icf.getInstance()
                            );
                        }
                    } catch (NoSuchMethodException | IOException
                            | IllegalAccessException | ClassNotFoundException
                            | InstantiationException e) {
                        e.printStackTrace();
                    }
                }
        );

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

                        if (current != null) {
                            infoText.setText(
                                    current.getColorScheme()
                                            + "\nValue: " + i + ": "
                                            + current.applyHighlighter(i)
                            );
                        }
                    });
                }
        );

        highlightModeChoiceBox.getItems().addAll(
                new Binary(), new LERP(), new MultipleOfBase()
        );
        highlightModeChoiceBox.getSelectionModel().select(0);
        highlightModeChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (!Objects.equals(oldValue, newValue)) {
                        updateColorSelectionGrid(
                                highlightModeChoiceBox.getItems().get((int) newValue)
                        );
                    }
                }
        );

        colorPickers = FXCollections.observableArrayList();
        updateColorSelectionGrid(highlightModeChoiceBox.getValue());

        availableColorSchemes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue == null) {
                        return;
                    }
                    if (selectedColorSchemes.getItems().contains(newValue)) {
                        selectedColorSchemes.getSelectionModel().select(
                                selectedColorSchemes.getItems().indexOf(newValue)
                        );
                    }
                    HighlightMode hm = newValue.getHighlightMode();
                    highlightModeChoiceBox.getSelectionModel().select(hm);
                    updateColorSelectionGrid(hm);
                    Highlighter h = newValue.getHighlighter();
                    highlighterChoiceBox.getSelectionModel().select(h);
                    cutoffTextField.setText(newValue.getCutoff() + "");
                    for (int i = 0; i < newValue.getNumRequiredColors(); i++) {
                        colorPickers.get(i).setValue(newValue.getColors()[i]);
                    }
                }
        );

        selectedColorSchemes.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue == null) {
                        return;
                    }
                    if (availableColorSchemes.getItems().contains(newValue)) {
                        availableColorSchemes.getSelectionModel().select(
                                availableColorSchemes.getItems().indexOf(newValue)
                        );
                    }
                    HighlightMode hm = newValue.getHighlightMode();
                    highlightModeChoiceBox.getSelectionModel().select(hm);
                    updateColorSelectionGrid(hm);
                    Highlighter h = newValue.getHighlighter();
                    highlighterChoiceBox.getSelectionModel().select(h);
                    cutoffTextField.setText(newValue.getCutoff() + "");
                    for (int i = 0; i < newValue.getNumRequiredColors(); i++) {
                        colorPickers.get(i).setValue(newValue.getColors()[i]);
                    }
                }
        );

        selectedColorSchemes.setOnMouseClicked(
                m -> {
                    ColorScheme cs = selectedColorSchemes.getSelectionModel().getSelectedItem();
                    if (cs == null) {
                        return;
                    }
                    if ((m.getClickCount() >= 2)
                            && m.getButton().equals(MouseButton.PRIMARY)) {
                        selectedColorSchemes.getItems().remove(cs);
                    }
                }
        );
        selectedColorSchemes.setCellFactory(c -> new ColorSchemeCell());

        availableColorSchemes.setOnMouseClicked(
                m -> {
                    ColorScheme cs = availableColorSchemes.getSelectionModel().getSelectedItem();
                    if (cs == null) {
                        return;
                    }
                    if ((m.getClickCount() >= 2)
                            && m.getButton().equals(MouseButton.PRIMARY)
                            && !selectedColorSchemes.getItems().contains(cs)) {
                        selectedColorSchemes.getItems().add(cs);
                    }
                }
        );
        availableColorSchemes.setCellFactory(c -> new ColorSchemeCell());

        updateColorSchemeButton.disableProperty().bind(
                availableColorSchemes.getSelectionModel().selectedIndexProperty().lessThan(0)
        );

        highlighterChoiceBox.getItems().addAll(
                new FactorCount(), new GoldbachCount(), new IsPrime(),
                new IsTriangular(), new CollatzLength()
        );
        highlighterChoiceBox.getSelectionModel().select(0);

        drawerChoiceBox.getItems().addAll(
                new Square(), new Hex()
        );
        drawerChoiceBox.getSelectionModel().select(0);

        inclusionCriteriaListView.getSelectionModel()
                .setSelectionMode(SelectionMode.MULTIPLE);
        inclusionCriteriaListView.getItems().addAll(
                new Any(), new GT_Zero(), new LogN_LT_FC(), new Odd(), new Even()
        );
        inclusionCriteriaListView.getSelectionModel().select(0);

        spiralCanvas.setOnScroll(e -> {
            if (e.isControlDown()) {
                double mult = (e.getDeltaY() > 0) ? (1 / scalingRate) : scalingRate;
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

        addColorScheme();
    }

    @FXML public void saveCanvasAsImage() {
        canvasScaleField.setValue((canvasScaleField.getMax() + canvasScaleField.getMin()) / 2);
        Images.captureImage(spiralCanvas);
    }

    @FXML
    public void updateColorScheme() {
        ColorScheme cs = createColorScheme();
        int availableIndex = availableColorSchemes.getItems().indexOf(
                availableColorSchemes.getSelectionModel().getSelectedItem()
        );
        int selectedIndex = selectedColorSchemes.getItems().indexOf(
                availableColorSchemes.getSelectionModel().getSelectedItem()
        );
        availableColorSchemes.getItems().set(availableIndex, cs);
        if (selectedIndex >= 0) {
            selectedColorSchemes.getItems().set(selectedIndex, cs);
        }
    }

    private void updateColorSelectionGrid(HighlightMode hm) {
        colorSelectionGrid.getChildren().clear();
        colorPickers.clear();
        int colorCount = hm.getNumRequiredColors();
        for (int i = 0; i < colorCount; i++) {
            colorSelectionGrid.add(new Label(ordinal(i + 1)), 0, i);
            ColorPicker cp = new ColorPicker(BLACK);
            colorSelectionGrid.add(cp, 1, i);
            colorPickers.add(cp);
        }

        colorSelectionGrid.autosize();
    }

    @SuppressWarnings("MagicNumber")
    private String ordinal(int i) {
        String ending;
        int mod = i % 10;
        if ((mod == 1) && (i != 11)) {
            ending = "st";
        } else if ((mod == 2) && (i != 12)) {
            ending = "nd";
        } else if ((mod == 3) && (i != 13)) {
            ending = "rd";
        } else {
            ending = "th";
        }

        return i + ending;
    }

    @FXML
    void addColorScheme() {
        ColorScheme cs = createColorScheme();
        if (!selectedColorSchemes.getItems().contains(cs)) {
            selectedColorSchemes.getItems().add(cs);
        }
        if (!availableColorSchemes.getItems().contains(cs)) {
            availableColorSchemes.getItems().add(cs);
        }
    }

    @FXML
    public ColorScheme createColorScheme() {
        int cutoff = intCutoffTextField.getValue();
        Highlighter h = highlighterChoiceBox.getValue();
        HighlightMode hm = highlightModeChoiceBox.getValue();
        List<Color> colors = new ArrayList<>();
        colorPickers.forEach(c -> colors.add(c.getValue()));

        return new ColorSchemeFactory().getColorScheme(hm, h, colors, cutoff);
    }

    @FXML
    public void removeColorSchemes() {
        //Copy is necessary since the selected items are observable, so deleting
        //them in one list (resulting in a new set of selected items) would
        //result in the incorrect items being deleted in the other list
        Collection<ColorScheme> selectedItems = new ArrayList<>(
                availableColorSchemes.getSelectionModel().getSelectedItems()
        );
        availableColorSchemes.getItems().removeAll(selectedItems);
        selectedColorSchemes.getItems().removeAll(selectedItems);
    }

    @FXML public void drawSpiral() {
        Predicate<Integer> inclusionCriteria = new Any();
        for (InclusionCriterion i
                : inclusionCriteriaListView.getSelectionModel()
                .getSelectedItems()) {
            inclusionCriteria = inclusionCriteria.and(i);
        }

        List<Preprocessor> preprocessors = new ArrayList<>();
        inclusionCriteriaListView.getSelectionModel().getSelectedItems()
                .forEach(i -> preprocessors.add(i.getPreprocessor()));
        selectedColorSchemes.getItems()
                .forEach(i -> preprocessors.add(i.getPreprocessor()));
        BulkPreprocess preprocessor = new BulkPreprocess(preprocessors);

        spiralProgress.progressProperty().bind(preprocessor.progressBinding());

        ColorScheme cs = new PriorityColorScheme(
                selectedColorSchemes.getItems().toArray(
                        new ColorScheme[selectedColorSchemes.getItems().size()]
                )
        );

        Drawer drawer = drawerChoiceBox.getValue();

        Spiral spiral = new Spiral(drawer, preprocessor, cs, inclusionCriteria);

        drawSpiral(spiral);
    }

    @SuppressWarnings("MagicNumber")
    private void drawSpiral(Spiral spiral) {
        int spiralLength = intSpiralLengthField.getValue();
        int elemSize = intElementSizeField.getValue();
        int canvasHeight = intCanvasHeightField.getValue();
        int canvasWidth = intCanvasScaleField.getValue();

        spiralCanvas.setHeight(canvasHeight);
        spiralCanvas.setWidth(canvasWidth);
        spiralPane.setVvalue(0.5);
        spiralPane.setHvalue(0.5);
        GraphicsContext gc = spiralCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvasWidth, canvasHeight);
        gc.setStroke(BLACK);
        gc.setLineWidth(10);
        gc.strokeRect(5, 5, canvasWidth - 5, canvasHeight - 5);
        gc.setLineWidth(1);
        current = spiral;
        Platform.runLater(() -> spiral.draw(gc, spiralLength, elemSize));
    }
}
