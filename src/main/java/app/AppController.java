package app;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
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
import math.inclusion_criteria.Any;
import math.inclusion_criteria.Even;
import math.inclusion_criteria.GT_Zero;
import math.inclusion_criteria.InclusionCriterion;
import math.inclusion_criteria.LogN_LT_FC;
import math.inclusion_criteria.Odd;
import math.preprocessors.BulkPreprocess;
import math.preprocessors.Preprocessor;
import spirals.Spiral;
import spirals.color_schemes.ColorScheme;
import spirals.color_schemes.ColorSchemeFactory;
import spirals.color_schemes.PriorityColorScheme;
import spirals.drawers.Drawer;
import spirals.drawers.Hex;
import spirals.drawers.Square;
import spirals.highlight_modes.Binary;
import spirals.highlight_modes.HighlightMode;
import spirals.highlight_modes.LERP;
import spirals.highlight_modes.MultipleOfBase;
import spirals.highlighters.CollatzLength;
import spirals.highlighters.DiffFactorCount;
import spirals.highlighters.FactorCount;
import spirals.highlighters.GoldbachCount;
import spirals.highlighters.IsPrime;
import spirals.highlighters.IsTriangular;
import spirals.highlighters.NameLengths;
import spirals.highlighters.PreprocessedFn;
import spirals.highlighters.PrimeFactorCount;
import util.Images;
import util.InclusionCriterionFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import static javafx.scene.paint.Color.BLACK;

/**
 * Created by Kevin on 5/20/2017 for Spirals.
 */
public class AppController implements Initializable {
    private final double scalingRate = 0.95;
    @FXML
    private Canvas spiralCanvas;
    @FXML
    private TextField spiralLengthField;
    @FXML
    private TextField elementSizeField;
    @FXML
    private TextField canvasHeightField;
    @FXML
    private TextField canvasWidthField;
    @FXML
    private Slider canvasScaleField;
    @FXML
    private ChoiceBox<HighlightMode> highlightModeChoiceBox;
    @FXML
    private ChoiceBox<Drawer> drawerChoiceBox;
    @FXML
    private ChoiceBox<PreprocessedFn> highlighterChoiceBox;
    @FXML
    private GridPane colorSelectionGrid;
    @FXML
    private TextField cutoffTextField;
    @FXML
    private ListView<InclusionCriterion> inclusionCriteriaListView;
    @FXML
    private ListView<ColorScheme> selectedColorSchemes;
    @FXML
    private ListView<ColorScheme> availableColorSchemes;
    @FXML
    private Text infoText;
    @FXML
    private ScrollPane spiralPane;
    @FXML
    private Button updateColorSchemeButton;
    @FXML
    private ProgressBar spiralProgress;
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
                    Drawer drawer = drawerChoiceBox.getSelectionModel()
                            .getSelectedItem();
                    Platform.runLater(() -> {
                        int i = drawer.mousePositionToN(
                                mouse.getX(), mouse.getY(), mainGC,
                                intSpiralLengthField.getValue(),
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
        highlightModeChoiceBox.getSelectionModel().selectedIndexProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {
                            if (!oldValue.equals(newValue)) {
                                HighlightMode item = highlightModeChoiceBox
                                        .getItems().get((int) newValue);
                                updateColorSelectionGrid(item);
                            }
                        }
                );

        colorPickers = FXCollections.observableArrayList();
        updateColorSelectionGrid(highlightModeChoiceBox.getValue());

        pairSelectionModels(selectedColorSchemes, availableColorSchemes);
        addColorSchemeSelectionListener(selectedColorSchemes);
        addColorSchemeSelectionListener(availableColorSchemes);

        selectedColorSchemes.setOnMouseClicked(
                m -> {
                    ColorScheme cs = selectedColorSchemes.getSelectionModel()
                            .getSelectedItem();
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
                    ColorScheme cs = availableColorSchemes.getSelectionModel()
                            .getSelectedItem();
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
                availableColorSchemes.getSelectionModel()
                        .selectedIndexProperty().lessThan(0)
        );

        highlighterChoiceBox.getItems().addAll(
                new IsPrime(),
                new FactorCount(),
                new PrimeFactorCount(),
                new DiffFactorCount(),
                new GoldbachCount(),
                new IsTriangular(),
                new CollatzLength(),
                new NameLengths()
        );
        highlighterChoiceBox.getSelectionModel().select(0);

        drawerChoiceBox.getItems().addAll(new Square(), new Hex());
        drawerChoiceBox.getSelectionModel().select(0);

        inclusionCriteriaListView.getSelectionModel()
                .setSelectionMode(SelectionMode.MULTIPLE);
        inclusionCriteriaListView.getItems().addAll(
                new Any(),
                new GT_Zero(),
                new LogN_LT_FC(),
                new Odd(),
                new Even()
        );
        inclusionCriteriaListView.getSelectionModel().select(0);

        spiralCanvas.setOnScroll(e -> {
            if (e.isControlDown()) {
                double mult;
                if (e.getDeltaY() > 0) {
                    mult = 1 / scalingRate;
                } else {
                    mult = scalingRate;
                }
                canvasScaleField.setValue(canvasScaleField.getValue() * mult);

                e.consume();
            }
        });

        canvasScaleField.valueProperty().addListener(
                (observable, oldValue, newValue) -> {
                    double value = newValue.doubleValue();
                    double scale = Math.pow(10, (value - 5) / 4);
                    spiralCanvas.setScaleX(scale);
                    spiralCanvas.setScaleY(scale);
                }
        );

        addColorScheme();
    }

    private void
    pairSelectionModels(ListView<ColorScheme> a, ListView<ColorScheme> b) {
        a.getSelectionModel().selectedItemProperty().addListener(
                getSelectionModelListener(b)
        );
        b.getSelectionModel().selectedItemProperty().addListener(
                getSelectionModelListener(a)
        );
    }

    private ChangeListener<ColorScheme>
    getSelectionModelListener(ListView<ColorScheme> a) {
        return (observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            if (a.getItems().contains(newValue)) {
                a.getSelectionModel().select(a.getItems().indexOf(newValue));
            }
        };
    }

    private void addColorSchemeSelectionListener(ListView<ColorScheme> cslv) {
        cslv.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if ((newValue == null) || (newValue == oldValue)) {
                        return;
                    }
                    HighlightMode hm = newValue.getHighlightMode();
                    highlightModeChoiceBox.getSelectionModel().select(hm);
                    AppController.this.updateColorSelectionGrid(hm);
                    PreprocessedFn h = newValue.getIntensityFunction();
                    highlighterChoiceBox.getSelectionModel().select(h);
                    cutoffTextField.setText(newValue.getCutoff() + "");
                    for (int i = 0; i < newValue.getNumRequiredColors(); i++) {
                        colorPickers.get(i).setValue(newValue.getColors()[i]);
                    }
                }
        );
    }

    @FXML
    public void saveCanvasAsImage() {
        double total = canvasScaleField.getMax() + canvasScaleField.getMin();
        canvasScaleField.setValue(total / 2);
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

    private ColorScheme createColorScheme() {
        int cutoff = intCutoffTextField.getValue();
        PreprocessedFn h = highlighterChoiceBox.getValue();
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

    @FXML
    public void drawSpiral() {
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

        spiralProgress.progressProperty().bind(preprocessor.progressProperty());

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
