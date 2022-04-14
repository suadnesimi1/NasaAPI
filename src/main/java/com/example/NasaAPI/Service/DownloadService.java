package com.example.NasaAPI.Service;

import com.example.NasaAPI.Domain.Photos;


import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


@Service
public class DownloadService {
    private static final Logger LOGGER = LoggerFactory
            .getLogger(DownloadService.class);
    private static final String DOWNLOAD_FOLDER = System
            .getProperty("user.home") + "/Downloads/";

    public void downloadPhoto(Photos photo) {
        if (photo != null && photo.getImgSrc() != null
                && photo.getImgSrc().getFile() != null) {
            String temp = photo.getImgSrc().getFile();
            String fileName = temp.substring(temp.lastIndexOf("/") + 1);
            String filePath = DOWNLOAD_FOLDER + fileName;
            LOGGER.debug("File path :: {}", filePath);
            BufferedImage image = null;
            try{
                image = ImageIO.read(photo.getImgSrc());
                ImageIO.write(image, "jpg", new File(filePath));
                LOGGER.debug(image.toString());

            } catch (IIOException e) {
                LOGGER.debug(e.getMessage());
                throw new IllegalArgumentException();
            } catch (IOException e1) {
                LOGGER.debug(e1.getMessage());
                throw new IllegalArgumentException();
            }
        }
    }
}
