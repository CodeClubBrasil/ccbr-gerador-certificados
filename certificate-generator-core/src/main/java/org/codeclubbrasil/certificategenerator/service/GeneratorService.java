package org.codeclubbrasil.certificategenerator.service;

import java.io.IOException;

import org.codeclubbrasil.certificategenerator.Generator;
import org.codeclubbrasil.certificategenerator.domain.CertificateTemplate;
import org.codeclubbrasil.certificategenerator.domain.CodeClubClass;
import org.codeclubbrasil.certificategenerator.domain.GenerateOutput;
import org.codeclubbrasil.certificategenerator.exception.GeneratorException;
import org.codeclubbrasil.certificategenerator.exception.InvalidClassException;
import org.codeclubbrasil.certificategenerator.exception.InvalidTemplateException;
import org.codeclubbrasil.certificategenerator.utils.ZipUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneratorService {

    private static final Logger LOG = LoggerFactory.getLogger(GeneratorService.class);

    public GenerateOutput generate(CertificateTemplate template, CodeClubClass codeClass) throws GeneratorException, InvalidTemplateException, InvalidClassException {
        LOG.info("GeneratorService.generate");
        Generator gen = getGenerator(template);
        GenerateOutput out = gen.generate(template, codeClass);
        out.setOutputBytes(getBytesFromZip(out.getOutputDir()));
        return out;
    }

    public GenerateOutput generateAndZipFile(CertificateTemplate template, CodeClubClass codeClass) throws InvalidTemplateException, InvalidClassException, GeneratorException {
        LOG.info("GeneratorService.generateAndZipFile");
        GenerateOutput out = generate(template, codeClass);
        out.setOutputZipFileMame(saveZipFile(out, codeClass.getClassName()));
        LOG.info("GeneratorService outputZipFileName: " + out.getOutputZipFileMame());
        return out;
    }

    private byte[] getBytesFromZip(String outputDir) throws GeneratorException {
        LOG.info("GeneratorService.generateBytes");
        try {
            return ZipUtils.zipFiles(outputDir);
        } catch (IOException e) {
            throw new GeneratorException(e.toString());
        }
    }

    private String saveZipFile(GenerateOutput out, String fileName) throws GeneratorException {
        LOG.info("GeneratorService.saveZipFile");
        String zipFileName = out.getOutputDir() + fileName + ".zip";
        try {
            ZipUtils.saveZipFile(out.getOutputBytes(), zipFileName);
        } catch (IOException e) {
            throw new GeneratorException(e.toString());
        }
        LOG.info("GeneratorService.zipFileName: " + zipFileName);
        return zipFileName;
    }

    private Generator getGenerator(CertificateTemplate template) throws GeneratorException, InvalidTemplateException {
        if (template == null) {
            throw new InvalidTemplateException("Template can not be null!");
        }
        switch (template.getType()) {
            case PDF:
                return PDFGenerator.getInstance();
                default:
        }
        throw new GeneratorException("TemplateType is null or invalid. Only accepts PDF type!");
    }

}
