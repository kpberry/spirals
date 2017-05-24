package app;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import math.Primes;
import math.Triangular;
import spirals.ColorScheme;
import spirals.InclusionCriterion;
import spirals.PriorityColorScheme;
import spirals.Spiral;
import spirals.hex.HexLogSpiral;
import spirals.hex.HexSpiral;
import spirals.hex.HexUlamSpiral;
import spirals.square.LogSpiral;
import spirals.square.SquareUlamSpiral;
import util.Images;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Kevin on 5/20/2017 for Spirals.
 *
 */
public class AppController implements Initializable {
    @FXML
    private Canvas canvas;
    @FXML
    private TextField spiralLengthField;
    @FXML
    private TextField elementSizeField;
    @FXML
    private TextField canvasHeightField;
    @FXML
    private TextField canvasWidthField;
    @FXML
    private ColorPicker primaryColorPicker;
    @FXML
    private ColorPicker secondaryColorPicker;
    @FXML
    private ProgressBar progressBar;

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

        canvas.setWidth(intCanvasWidthField.getValue());
        canvas.setHeight(intCanvasHeightField.getValue());
        progressBar.setProgress(0);
    }

    @FXML
    public void saveCanvasAsImage() {
        Images.captureImage(canvas);
    }

    @FXML
    public void drawSquareUlamSpiral() {
        Color primary = primaryColorPicker.getValue();
        Color secondary = secondaryColorPicker.getValue();
        drawSpiral(new SquareUlamSpiral(primary, secondary));
    }
    @FXML
    public void drawSquareLogSpiral() {
        drawSpiral(new LogSpiral(primaryColorPicker.getValue()));
    }

    @FXML
    public void drawHexUlamSpiral() {
        Color primary = primaryColorPicker.getValue();
        Color secondary = secondaryColorPicker.getValue();
        drawSpiral(new HexUlamSpiral(primary, secondary));
    }

    //This is admittedly gross, but was pretty easy to write. Just an example
    //of creating a custom spiral inline. Could be used later to make arbitrary
    //spirals from within the GUI
    //TODO make it so that the preprocessing and inclusion criteria can handle arbitrary functions via a CAS
    private void customSpiralExample() {
        Color primary = primaryColorPicker.getValue();
        Color secondary = secondaryColorPicker.getValue();
        Color tertiary = Color.YELLOW;

        drawSpiral(new HexSpiral(
                new PriorityColorScheme(
                        new ColorScheme[]{
                                n -> Optional.ofNullable(Triangular.isTriangular(n) ? tertiary : null),
                                n -> Optional.of(Primes.isPrime(n) ? primary : secondary)
                        }
                ), value -> true
        ) {
            @Override
            public void preprocess(int length) {
                Primes.updateFactorCounts(length);
                Triangular.updateTriangleNumbers(length);
            }
        });
    }

    @FXML
    public void drawHexLogSpiral() {
        drawSpiral(new HexLogSpiral(primaryColorPicker.getValue()));
    }

    public void drawSpiral(Spiral spiral) {
        int spiralLength = intSpiralLengthField.getValue();
        int elemSize = intElementSizeField.getValue();
        int canvasHeight = intCanvasHeightField.getValue();
        int canvasWidth = intCanvasWidthField.getValue();

        canvas.setHeight(canvasHeight);
        canvas.setWidth(canvasWidth);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvasWidth, canvasHeight);
        spiral.draw(gc, spiralLength, elemSize, progressBar);
    }
}
