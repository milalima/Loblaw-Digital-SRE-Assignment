package processImage;

import enumerator.StatusProcess;
import common.CommonFunctions;
import dto.ReadCSV;
import dto.WriteCSV;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*

* The brightness of a color by definition is the distance that color has from the black color, so we can measure
* the difference between two colors from that distance.

* To measure this distance the formula used, which helps to calculate the intensity of a color, is the Pythagorean theorem.
* Thus using the values of the red, green and blue variables that contain the differences between the values of the two colors.
*

* Based on this, if the brightness of a color is the distance that color has from the black color, as explained above, then
* we can measure the difference between two colors from that distance. The values of the red,green and blue variables are
* the differences between the values of the two colors. And for that, auxiliary methods were used to separate each of the components

*/


public class ScoreImage {

    public WriteCSV processingImage(ReadCSV path) throws IOException {

        WriteCSV writeLine = new WriteCSV();
        BufferedImage imgA = null;
        BufferedImage imgB = null;
        CommonFunctions validation = new CommonFunctions();
        String imagePath = "";

        long endTime = 0;
        // starting the time of processing each pair of image
        long startTime = System.nanoTime();

        writeLine.readCSV = path;

        // Instances of buffered images

        if (validation.ValidateFileExit(path.image1))
        {
            if (validation.ValidateFileExit(path.image2))
            {
                File fileA = new File(path.image1);
                File fileB = new File(path.image2);
                imgA = ImageIO.read(fileA);
                imgB = ImageIO.read(fileB);

                writeLine.similar = differenceImages(imgA,imgB);
                endTime = System.nanoTime();
                writeLine.elapsed = (endTime - startTime);


            }else{
                imagePath = path.image2;
            }
        }else {
            imagePath = path.image1;
        }

        CommonFunctions showAlert = new CommonFunctions();
        showAlert.showMessage(StatusProcess.FILEDONTEXIST, imagePath);

        return writeLine;
    }

    public static int red(int c) {
        return (c >> 16) & 0xff;
    }

    public static int green(int c) {
        return (c >> 8) & 0xff;
    }

    public static int blue(int c) {
        return c & 0xff;
    }

    public static double distanceColors(int a, int b) {
        return distanceColors(red(a), green(a), blue(a), red(b), green(b), blue(b));
    }

    public static double differenceImages(BufferedImage a, BufferedImage b) {

        int h = a.getHeight();
        int w = a.getWidth();
        double dif = 1.0;

        if (w != b.getWidth() || h != b.getHeight()) {
            return dif;
        }

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int colour1 = a.getRGB(x, y);
                int colour2 = b.getRGB(x, y);
                dif += distanceColors(colour1, colour2);
            }
        }
        return (dif / (h * w)) / 100;
    }

    // Measuring the difference between two colors from that distance
    public static double distanceColors(int r1, int g1, int b1, int r2, int g2, int b2) {
        int dr = r1 - r2;
        int dg = g1 - g2;
        int db = b1 - b2;
        return Math.sqrt(0.299 * dr * dr + 0.587 * dg * dg + 0.114 * db * db);
    }

}
