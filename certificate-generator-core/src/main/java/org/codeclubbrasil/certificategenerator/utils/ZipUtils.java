package org.codeclubbrasil.certificategenerator.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ZipUtils {

	private final static Logger log = LoggerFactory.getLogger(ZipUtils.class);

	public static byte[] zipFiles(String inputputDir) throws IOException {
		File[] filesArray = new File(inputputDir).listFiles();
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ZipOutputStream zipOut = new ZipOutputStream(bo);
		for (File pdfFile : filesArray) {
			if (!pdfFile.isFile())
				continue;
			ZipEntry zipEntry = new ZipEntry(pdfFile.getName());
			zipOut.putNextEntry(zipEntry);
			zipOut.write(IOUtils.toByteArray(new FileInputStream(pdfFile)));
			zipOut.closeEntry();
		}

		zipOut.close();
		return bo.toByteArray();

	}

	public static void saveZipFile(byte[] zipArray, String zipFileName) throws IOException {
		FileUtils.writeByteArrayToFile(new File(zipFileName), zipArray);
		log.info("zip file saved in: " + zipFileName);
	}

}
