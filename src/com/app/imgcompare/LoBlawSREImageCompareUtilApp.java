package com.app.imgcompare;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.app.imgcompare.util.CsvUtil;
import com.app.imgcompare.util.ImgVisualCmprUtil;

public class LoBlawSREImageCompareUtilApp {

	public static void main(String[] args) {

		String inputCSV = args[0];
		String outputCSV = args[1];

		// read the CSV file
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = " ";

		if (inputCSV == null || "".contains(inputCSV)) {
			System.out.println(" The Inpout CSV file is not supplied ");
			System.exit(0);
		} else {

			List<ImageDataBean> imagedataList = new ArrayList<ImageDataBean>();
			try {

				br = new BufferedReader(new FileReader(inputCSV));
				while ((line = br.readLine()) != null) {

					String[] images = line.split(cvsSplitBy);
					// check if this is header then skip
					if (!"image1".contains(images[0])) {
						System.out.println("image1 [" + images[0]
								+ "] image2 [" + images[1] + "]");
						ImageDataBean value = new ImageDataBean();
						value.setImage1(images[0]);
						value.setImage2(images[1]);

						String[] processData = ImgVisualCmprUtil
								.compareVisualSimilarity(images[0], images[1]);

						value.setSimilarity(processData[0]);
						value.setElapsed(processData[1]);

						imagedataList.add(value);
					}
				}

				if (imagedataList.size() > 0) {
					if(outputCSV==null || "".contains(outputCSV)) {
						outputCSV = "outputImages.csv";
					}
					
					CsvUtil.writeLine(outputCSV, imagedataList);
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
		}
	}
}
