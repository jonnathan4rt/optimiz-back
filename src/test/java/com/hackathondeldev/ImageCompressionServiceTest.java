package com.hackathondeldev;

import static org.junit.jupiter.api.Assertions.*;
import com.hackathondeldev.service.ImageCompressionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

class ImageCompressionServiceTest {

    private ImageCompressionService imageCompressionService;

    @BeforeEach
    void setUp() {
        imageCompressionService = new ImageCompressionService();
    }

    @Test
    void testCompressImage() throws IOException {
        // Arrange
        // Carga una imagen de prueba desde los recursos
        try (InputStream imageStream = getClass().getResourceAsStream("/test-image.jpg")) {
            assertNotNull(imageStream, "No se pudo cargar la imagen de prueba.");

            MultipartFile file = new MockMultipartFile("file", "test-image.jpg", "image/jpeg", imageStream);

            // Act
            byte[] compressedImage = imageCompressionService.compressImage(file);

            // Assert
            assertNotNull(compressedImage, "La imagen comprimida no debería ser nula.");
            assertTrue(compressedImage.length > 0, "La longitud de la imagen comprimida debería ser mayor a cero.");

            // Puedes realizar más verificaciones, por ejemplo, comparar el tamaño de la imagen comprimida con la original.
        }
    }
}

