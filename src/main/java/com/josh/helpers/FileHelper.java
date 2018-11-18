package com.josh.helpers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHelper {

	public static String CheckIfFileExistsAndIncrement(String filePath, String fileName, String extension) {

		File file = new File(filePath, fileName + extension);

		for (int num = 0; file.exists(); num++) {
			file = new File(filePath, fileName + "_" + num + extension);
		}

		return file.getName();
	}

	public static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
}
