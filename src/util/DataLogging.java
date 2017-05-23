package util;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kevin on 5/19/2017 for genes.
 */
public class DataLogging {
    private static Map<String, Integer> dirIndices = new HashMap<>();

    /**
     * Gets a new numbered file with a given extension within a desired
     * directory. Ensures that the returned file will not be the same as any
     * existing files within the directory.
     * @param dirName The (possibly non-existent) directory where the file will
     *                be created
     * @param extension The extension for the file type to return
     * @return A new unique file within the given directory
     */
    public static Path getNumberedOutputFile(String dirName, String extension) {
        Path targetDir = Paths.get("./out/" + dirName);
        try {
            Files.createDirectories(targetDir);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        if (!dirIndices.containsKey(dirName) || dirIndices.get(dirName) == 0) {
            try (DirectoryStream<Path> dir = Files.newDirectoryStream(targetDir)) {
                int max = 0;
                for (Path p : dir) {
                    String filename = p.getFileName().toString();
                    int extensionIndex = filename.indexOf("." + extension);
                    if (extensionIndex > 0) {
                        String noExtFilename = filename.substring(0, extensionIndex);

                        try {
                            int dirIndex = Integer.parseInt(noExtFilename);
                            if (dirIndex > max) {
                                max = dirIndex;
                            }
                        } catch (NumberFormatException ignored) {

                        }
                    }
                }

                dirIndices.put(dirName, max);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        dirIndices.put(dirName, dirIndices.get(dirName) + 1);
        Path file = targetDir.resolve(dirIndices.get(dirName) + "." + extension);
        try {
            Files.createFile(file);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return file;
    }

    public static PrintWriter getNumberedDataFileWriter(
            String dirName, String extension) throws IOException {
        Path file = getNumberedOutputFile(dirName, extension);
        return new PrintWriter(Files.newBufferedWriter(file));
    }

    public static Chart getBarHistogram(double[] data, double[] ranges) {
        double[] rangeCounts = new double[ranges.length - 1];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < ranges.length - 1; j++) {
                if (data[i] >= ranges[j] && data[i] < ranges[j + 1]) {
                    rangeCounts[j]++;
                }
            }
        }

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> result = new BarChart<>(xAxis, yAxis);

        for (int i = 0; i < ranges.length - 1; i++) {
            XYChart.Series<String, Number> s = new XYChart.Series<>();
            s.setName("[" + ranges[i] + ", " + ranges[i + 1] + ")");
            s.getData().add(new XYChart.Data<>("", rangeCounts[i]));
            result.getData().add(s);
        }
        return result;
    }

    public static Chart getLineHistogram(double[] data, double[] ranges) {
        double[] rangeCounts = new double[ranges.length - 1];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < ranges.length - 1; j++) {
                if (data[i] >= ranges[j] && data[i] < ranges[j + 1]) {
                    rangeCounts[j]++;
                }
            }
        }

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number, Number> result = new LineChart<>(xAxis, yAxis);

        XYChart.Series<Number, Number> s = new XYChart.Series<>();
        result.getData().add(s);
        for (int i = 0; i < ranges.length - 1; i++) {
            s.setName("[" + ranges[i] + ", " + ranges[i + 1] + ")");
            s.getData().add(new XYChart.Data<>(ranges[i], rangeCounts[i]));
        }
        return result;
    }

    public static Chart getLineCDF(double[] data, double[] ranges) {
        double[] rangeCounts = new double[ranges.length - 1];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < ranges.length - 1; j++) {
                if (data[i] <= ranges[j]) {
                    rangeCounts[j]++;
                }
            }
        }

        for (int i = 0; i < rangeCounts.length; i++) {
            rangeCounts[i] /= data.length * 1.0;
        }

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number, Number> result = new LineChart<>(xAxis, yAxis);

        XYChart.Series<Number, Number> s = new XYChart.Series<>();
        result.getData().add(s);
        for (int i = 0; i < ranges.length - 1; i++) {
            s.setName("[" + ranges[i] + ", " + ranges[i + 1] + ")");
            s.getData().add(new XYChart.Data<>(ranges[i], rangeCounts[i]));
        }
        return result;
    }
}
