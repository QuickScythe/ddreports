package me.ddreports;

import me.ddreports.storage.StorageManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication

public class Main  {


    public static void main(String[] args) {


//        // Save the grayscale image
//
//        File input = new File("img.jpg");
//        File output = new File("grayscale_img.png");
//        System.out.println("Input file exists: " + input.exists());
//        System.out.println("Output file exists: " + output.exists());
//        try {
//            BufferedImage normalImage = ImageIO.read(input);
//            BufferedImage grayscaleImage = ImageUtils.convertToGrayscale(normalImage);
//            BufferedImage noiseRemovedImage = ImageUtils.removeNoise(grayscaleImage);
//            BufferedImage sharpenedImage = ImageUtils.sharpenImage(noiseRemovedImage);
//            BufferedImage binarizedImage = ImageUtils.adaptiveBinarizeImage(sharpenedImage);
//            System.out.println("Result: " + ImageIO.write(binarizedImage, "png", output));
////            output.createNewFile();
//        } catch (IOException e) {
//            System.out.println("Error saving image");
//            throw new RuntimeException(e);
//        }
//        System.out.println("Output file exists (After grayscale): " + output.exists());
//
//        Tesseract tess = new Tesseract();
//        try {
//            tess.setDatapath("");
//            tess.setLanguage("eng");
//            tess.setTessVariable("user_defined_dpi", "2400");
//            tess.setTessVariable("tessedit_char_whitelist", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789.$ ([{}])\n:");
////            System.out.println("File Exists: " + output.exists());// Set the path to the tessdata directory
//            String text = tess.doOCR(output);
//            System.out.println(text);
//        } catch (TesseractException e) {
//            e.printStackTrace();
//        }
        StorageManager.init();
        SpringApplication.run(Main.class, args);
    }


}