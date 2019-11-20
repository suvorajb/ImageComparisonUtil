package com.app.imgcompare.util;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.PixelGrabber;

public class ImgVisualCmprUtil {

	public static String[] compareVisualSimilarity(String file1, String file2)
			throws Exception {
		
		long start = System.currentTimeMillis();
		System.out.println("........ starting comparing the two images for similarty .........");
		String similarity = "";
		String elapsed = "";
		// Load the images
		Image image1 = Toolkit.getDefaultToolkit().getImage(file1);
		Image image2 = Toolkit.getDefaultToolkit().getImage(file2);

		PixelGrabber grabImage1Pixels = new PixelGrabber(image1, 0, 0, -1, -1,
				false);
		PixelGrabber grabImage2Pixels = new PixelGrabber(image2, 0, 0, -1, -1,
				false);

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

		similarity = compare(image1Data, image2Data);
		
		long end = System.currentTimeMillis();

		System.out.println("........ comparison of the two images for similarty completed .........");
		elapsed = String.valueOf((end-start))+"ms";
		
		return new String[] {similarity, elapsed};
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
			//System.out.println("matchedPixelCount-" + matchedPixelCount);
			//System.out.println("totalPixelCount-" + totalPixelCount);
			comprdVal = matchedPixelCount / totalPixelCount;			
		}

		if(comprdVal==1.000)
			return "0";
		else 
			return String.format("%.3f", comprdVal);
	}

}
