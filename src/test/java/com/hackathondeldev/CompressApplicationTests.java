package com.hackathondeldev;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.hackathondeldev.controller.ImageCompressionAPI;
import com.hackathondeldev.service.ImageCompressionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

class ImageCompressionAPITest {

	@Mock
	private ImageCompressionService compressionService;

	@InjectMocks
	private ImageCompressionAPI imageCompressionAPI;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCompressImage() throws IOException {
		// Arrange
		// Creamos un archivo simulado con algunos bytes aleatorios
		MultipartFile mockFile = new MockMultipartFile("file", "image.jpg", "image/jpeg", new byte[]{10, 20, 30});
		byte[] expectedCompressedImage = new byte[]{1, 2, 3}; // byte array simulado que representa la imagen comprimida

		// Configuramos el servicio simulado para devolver el resultado esperado
		when(compressionService.compressImage(mockFile)).thenReturn(expectedCompressedImage);

		// Act
		// Llamamos al método `compressImage` de `ImageCompressionAPI`
		ResponseEntity<byte[]> response = imageCompressionAPI.compressImage(mockFile);

		// Assert
		// Verificamos el código de estado HTTP (200 OK)
		assertEquals(200, response.getStatusCodeValue());
		// Verificamos el tipo de contenido esperado (image/jpeg)
		assertEquals(MediaType.IMAGE_JPEG, response.getHeaders().getContentType());
		// Verificamos que el cuerpo de la respuesta coincide con la imagen comprimida esperada
		assertArrayEquals(expectedCompressedImage, response.getBody());

		// Verificamos que se haya llamado a `compressImage` en `compressionService` con el archivo simulado
		verify(compressionService).compressImage(mockFile);
	}

	@Test
	void testCompressImage_withNullFile() throws IOException {
		// Act
		ResponseEntity<byte[]> response = imageCompressionAPI.compressImage(null);

		// Assert
		// Verificamos que el código de estado HTTP es 400 Bad Request cuando se pasa un archivo nulo
		assertEquals(400, response.getStatusCodeValue());
	}
}
