package com.hackathondeldev.service;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ImageCompressionService {

    public byte[] compressImage(MultipartFile file) throws IOException {
        BufferedImage originalImage = ImageIO.read(file.getInputStream());

        ByteArrayOutputStream compressedOutputStream = new ByteArrayOutputStream();

        Thumbnails.of(originalImage)
                .size(originalImage.getWidth() / 2, originalImage.getHeight() / 2)
                .outputFormat("jpg")
                .outputQuality(0.6)
                .toOutputStream(compressedOutputStream);

        return compressedOutputStream.toByteArray();
    }
}