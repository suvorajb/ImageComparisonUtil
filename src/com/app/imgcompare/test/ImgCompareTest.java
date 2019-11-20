package com.app.imgcompare.test;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;
import java.util.Arrays;

public class ImgCompareTest {

	public static void main(String[] args) {
		processImage();

	}

	static void processImage() {

		String file1 = "C:/temp/imgcmpare/LennaBad.jpg";
		String file2 = "C:/temp/imgcmpare/Lenna50.jpg";

		try {

			// Load the images
			Image image1 = Toolkit.getDefaultToolkit().getImage(file1);
			Image image2 = Toolkit.getDefaultToolkit().getImage(file2);

			PixelGrabber grabImage1Pixels = new PixelGrabber(image1, 0, 0, -1,
					-1, false);
			PixelGrabber grabImage2Pixels = new PixelGrabber(image2, 0, 0, -1,
					-1, false);

			int[] image1Data = null;

			if (grabImage1Pixels.grabPixels()) {
				int width = grabImage1Pixels.getWidth();
				int height = grabImage1Pixels.getHeight();
				image1Data = new int[width * height];
				image1Data = (int[]) grabImage1Pixels.getPixels();
			}

			int[] image2Data = null;

			if (grabImage2Pixels.grabPixels()) {
				int width = grabImage2Pixels.getWidth();
				int height = grabImage2Pixels.getHeight();
				image2Data = new int[width * height];
				image2Data = (int[]) grabImage2Pixels.getPixels();
			}

			System.out
					.println("The similarity percent between the 2 images is: "
							+ compare(image1Data, image2Data));

		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private static String compare(int[] data1, int[] data2) {
		float comprdVal = 0;
		if (data1.length == data2.length) {
			float totalPixelCount = data1.length;
			float matchedPixelCount = 0;
			for (int i = 0; i < data2.length; i++) {
				if (data1[i] == data2[i])
					matchedPixelCount++;
			}
			comprdVal = matchedPixelCount / totalPixelCount * 100;
		}
		return String.format("%.2f", comprdVal);
	}

}
