package com.kpberry.app;

import com.kpberry.math.Primes;
import com.kpberry.math.Triangular;
import com.kpberry.spirals.ColorScheme;
import com.kpberry.spirals.PriorityColorScheme;
import com.kpberry.spirals.Spiral;
import com.kpberry.spirals.hex.HexGoldbachSpiral;
import com.kpberry.spirals.hex.HexLogSpiral;
import com.kpberry.spirals.hex.HexSpiral;
import com.kpberry.spirals.hex.HexUlamSpiral;
import com.kpberry.spirals.square.SquareGoldbachSpiral;
import com.kpberry.spirals.square.SquareLogSpiral;
import com.kpberry.spirals.square.SquareUlamSpiral;
import com.kpberry.util.Images;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

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
    }

    @FXML
    public void saveCanvasAsImage() {
        Images.captureImage(canvas);
    }

    //Ulam Spirals
    @FXML
    public void drawSquareUlamSpiral() {
        Color primary = primaryColorPicker.getValue();
        Color secondary = secondaryColorPicker.getValue();
        drawSpiral(new SquareUlamSpiral(primary, secondary));
    }

    @FXML
    public void drawHexUlamSpiral() {
        Color primary = primaryColorPicker.getValue();
        Color secondary = secondaryColorPicker.getValue();
        drawSpiral(new HexUlamSpiral(primary, secondary));
    }

    //Log Spirals
    @FXML
    public void drawSquareLogSpiral() {
        drawSpiral(new SquareLogSpiral(primaryColorPicker.getValue()));
    }

    @FXML
    public void drawHexLogSpiral() {
        drawSpiral(new HexLogSpiral(primaryColorPicker.getValue()));
    }

    //Goldbach Spirals
    @FXML
    public void drawSquareGoldbachSpiral() {
        drawSpiral(new SquareGoldbachSpiral(primaryColorPicker.getValue()));
    }

    @FXML
    public void drawHexGoldbachSpiral() {
        drawSpiral(new HexGoldbachSpiral(primaryColorPicker.getValue()));
    }

    //This is admittedly gross, but was pretty easy to write. Just an example
    //of creating a custom spiral inline. Could be used later to make arbitrary
    //com.kpberry.spirals from within the GUI
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

    public void drawSpiral(Spiral spiral) {
        int spiralLength = intSpiralLengthField.getValue();
        int elemSize = intElementSizeField.getValue();
        int canvasHeight = intCanvasHeightField.getValue();
        int canvasWidth = intCanvasWidthField.getValue();

        canvas.setHeight(canvasHeight);
        canvas.setWidth(canvasWidth);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvasWidth, canvasHeight);
        spiral.draw(gc, spiralLength, elemSize);
    }
}
