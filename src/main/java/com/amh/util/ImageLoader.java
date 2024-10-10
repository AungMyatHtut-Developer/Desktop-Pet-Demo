package com.amh.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ImageLoader {

    public static BufferedImage GetResourceImage(String fileName) {
        BufferedImage image = null;
        try (InputStream inputStream = ImageLoader.class.getResourceAsStream("/images/"+fileName)){
            if (inputStream == null) {
                throw new IOException("Image not found!");
            }
            image = ImageIO.read(inputStream);
            System.out.println("We found the image");

        } catch (Exception e) {
            System.out.println("Exception happened when loading Sprite. Error : "+ e);
        }
        return image;
    }
}
