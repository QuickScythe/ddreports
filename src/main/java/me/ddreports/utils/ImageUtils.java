package me.ddreports.utils;

import java.awt.*;
import java.awt.image.*;

public class ImageUtils {

    public static BufferedImage convertToGrayscale(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage grayscaleImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = image.getRGB(x, y);

                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                // Calculate average
                int avg = (r + g + b) / 3;

                // Replace RGB value with avg
                p = (a << 24) | (avg << 16) | (avg << 8) | avg;

                grayscaleImage.setRGB(x, y, p);
            }
        }
        return grayscaleImage;
    }

    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    public static BufferedImage scaleImage(BufferedImage image, double scale){
        int width = (int) (image.getWidth() * scale);
        int height = (int) (image.getHeight() * scale);
        return resizeImage(image, width, height);
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        Image tmp = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return resizedImage;
    }

    public static BufferedImage binarizeImage(BufferedImage image) {
        BufferedImage binarizedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D g2d = binarizedImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return binarizedImage;
    }

    public static BufferedImage removeNoise(BufferedImage image) {
        // Apply a simple noise removal filter
        float[] matrix = {
                1f/9f, 1f/9f, 1f/9f,
                1f/9f, 1f/9f, 1f/9f,
                1f/9f, 1f/9f, 1f/9f
        };
        BufferedImageOp op = new ConvolveOp(new Kernel(3, 3, matrix));
        return op.filter(image, null);
    }

    public static BufferedImage sharpenImage(BufferedImage image) {
        // Apply a simple sharpening filter
        float[] matrix = {
                0, -1, 0,
                -1, 5, -1,
                0, -1, 0
        };
        BufferedImageOp op = new ConvolveOp(new Kernel(3, 3, matrix));
        return op.filter(image, null);
    }

    public static BufferedImage adaptiveBinarizeImage(BufferedImage image) {
        // Apply adaptive binarization (simple thresholding for demonstration)
        BufferedImage binarizedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
        Graphics2D g2d = binarizedImage.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return binarizedImage;
    }

    public static BufferedImage adjustContrast(BufferedImage image, float scaleFactor, float offset) {
        RescaleOp rescaleOp = new RescaleOp(scaleFactor, offset, null);
        return rescaleOp.filter(image, null);
    }
}
