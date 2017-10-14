package org.codeclubbrasil.certificategenerator.domain;

import lombok.Data;

@Data
public class GenerateOutput {

	private String outputDir;
	private int totalFiles;
	private byte[] outputBytes;
	private String outputZipFileMame;

}
