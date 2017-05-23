package util;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by Kevin on 5/19/2017 for genes.
 */
public class Images {
    public static void captureImage(Canvas canvas) {
        captureImage(canvas, DataLogging.getNumberedOutputFile("images", "png"));
    }

    public static void captureImage(Canvas canvas, Path destination) {
        WritableImage capture = new WritableImage(
                (int) canvas.getWidth(), (int) canvas.getHeight()
        );

        canvas.snapshot(null, capture);

        new Thread(() -> {
            BufferedImage out = SwingFXUtils.fromFXImage(capture, null);

            try {
                ImageIO.write(out, "png", destination.toFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
