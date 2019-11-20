package com.app.imgcompare.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import com.app.imgcompare.ImageDataBean;

public class CsvUtil {

	private static final char DEFAULT_SEPARATOR = ' ';

	public static void writeLine(String outputCSV, List<ImageDataBean> values)
			throws IOException {
		
		FileWriter w = new FileWriter(outputCSV);
		
		StringBuilder sb = new StringBuilder();
		for (ImageDataBean value : values) {

			sb.append(value.getImage1())
			  .append(DEFAULT_SEPARATOR)
			  .append(value.getImage2())
			  .append(DEFAULT_SEPARATOR)
			  .append(value.getSimilarity())
			  .append(DEFAULT_SEPARATOR)
			  .append(value.getElapsed())
			  .append("\n");
		}
		sb.append("\n");
		w.append(sb.toString());
		
		w.flush();
        w.close();
	}

}