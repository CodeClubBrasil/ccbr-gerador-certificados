package org.codeclubbrasil.certificategenerator.domain;

import lombok.Data;

/**
 * Representa a saída de uma geração de certificado
 */
@Data
public class GenerateOutput {

    private String outputDir;
    private int totalFiles;
    private byte[] outputBytes;
    private String outputZipFileMame;

}
