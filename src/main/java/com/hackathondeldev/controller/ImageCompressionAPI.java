package com.hackathondeldev.controller;

import com.hackathondeldev.service.ImageCompressionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ImageCompressionAPI {

    private final ImageCompressionService compressionService;

    public ImageCompressionAPI(ImageCompressionService compressionService) {
        this.compressionService = compressionService;
    }

    @PostMapping(value = "/compress", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> compressImage(@RequestPart MultipartFile file) throws IOException {
        if (file != null) {
            byte[] compressedImage = compressionService.compressImage(file);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentDispositionFormData("attachment", "comprimido_img.jpg");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(compressedImage);
        }
        return ResponseEntity.badRequest().build();
    }
}