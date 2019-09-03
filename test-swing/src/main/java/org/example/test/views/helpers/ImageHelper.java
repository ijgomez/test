package org.example.test.views.helpers;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;

import org.apache.commons.io.IOUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ImageHelper {

	public static Image loadImage(String path) {
		assert (path != null) : "parameter 'path' cannot be null!";
		assert (path.startsWith("/")) : "value of parameter 'path' should not be relative to the classpath";

		try {
			log.trace("Path image: {}", path);

			InputStream inputStream = ImageHelper.class.getResourceAsStream(path);

			assert (inputStream != null) : "inputStream no puede ser null";

			Image image = new ImageIcon(IOUtils.toByteArray(inputStream)).getImage();
			return image;
		} catch (IOException e) {
			log.error("Image not found: {}", path);
			throw new IllegalArgumentException("Image not found");
		}
	}

	public static ImageIcon loadImageIcon(String path) {
		assert (path != null) : "parameter 'path' cannot be null!";
		assert (path.startsWith("/")) : "value of parameter 'path' should not be relative to the classpath";

		try {
			log.trace("Path image: {}", path);

			InputStream inputStream = ImageHelper.class.getResourceAsStream(path);
			ImageIcon image = new ImageIcon(IOUtils.toByteArray(inputStream));
			return image;
		} catch (IOException e) {
			log.error("Image not found: {}", path);
			throw new IllegalArgumentException("Image not found");
		}
	}

	public static Image loadImage(byte[] bytes) {
		assert (bytes != null) : "parameter 'bytes' cannot be null!";
		return new ImageIcon(bytes).getImage();
	}
}
