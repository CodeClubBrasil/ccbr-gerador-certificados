package org.codeclubbrasil.certificategenerator.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.codeclubbrasil.certificategenerator.Generator;
import org.codeclubbrasil.certificategenerator.domain.CertificateTemplate;
import org.codeclubbrasil.certificategenerator.domain.CodeClubClass;
import org.codeclubbrasil.certificategenerator.domain.GenerateOutput;
import org.codeclubbrasil.certificategenerator.exception.GeneratorException;
import org.codeclubbrasil.certificategenerator.exception.InvalidClassException;
import org.codeclubbrasil.certificategenerator.exception.InvalidTemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Gerador de PDF
 */
public final class PDFGenerator implements Generator {

    private static final Logger LOG = LoggerFactory.getLogger(PDFGenerator.class);
    private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
    private static final String GENERATE_DIR = "/pdfgenerador";
    private static PDFGenerator instance;

    private PDFGenerator() {
    }

    static Generator getInstance() {
        if (instance == null) {
            return new PDFGenerator();
        }
        return instance;
    }

    @Override
    public GenerateOutput generate(CertificateTemplate template, CodeClubClass codeClass)
            throws GeneratorException, InvalidClassException, InvalidTemplateException {
        validateClass(codeClass);
        validateTemplate(template);
        return generatePdf(template, codeClass);
    }

    /**
     * Generate a massive pdf files from template and class data
     *
     * @param template
     * @param codeClass
     * @throws GeneratorException
     */
    private GenerateOutput generatePdf(CertificateTemplate template, CodeClubClass codeClass) throws GeneratorException {
        LOG.info("PDFGenerator.generatePdf");
        List<String> names = codeClass.getStudentsNames();
        String outDir = getOutDir();
        String leader = codeClass.getLeaderName();

        PDAcroForm pDAcroForm = null;

        PDField field = null;

        for (int i = 0; i < names.size(); i++) {
            PDDocument pDDocument;
            try {
                LOG.info("Using template: " + template.getAbsolutePath());
                pDDocument = PDDocument.load(new File(template.getAbsolutePath()));
            } catch (IOException e) {
                throw new GeneratorException("Error on load document template", e);
            }
            pDAcroForm = pDDocument.getDocumentCatalog().getAcroForm();
            pDAcroForm.setNeedAppearances(false);
            field = pDAcroForm.getField("txt_lider");
            try {
                field.setValue(leader);
                String aluno = names.get(i);
                field = pDAcroForm.getField("txt_aluno");
                field.setValue(aluno);
                pDDocument.save(outDir + aluno + ".pdf");
                pDDocument.close();
            } catch (IOException e) {
                throw new GeneratorException("Error on generate PDF document", e);
            }

        }

        GenerateOutput out = new GenerateOutput();
        out.setOutputDir(outDir);
        out.setTotalFiles(names.size());

        return out;

    }

    private String getOutDir() {
        String outDir = null;
        try {
            String path = new File(TEMP_DIR).getAbsolutePath();
            LOG.info("path = " + path);
            File out = new File(path + GENERATE_DIR);
            LOG.info("out = " + out);
            FileUtils.deleteDirectory(out);
            out.mkdir();
            outDir = out.getAbsolutePath() + File.separator;
            LOG.info("outDir = " + outDir);
        } catch (Exception e) {
            LOG.error(e.toString());
        }
        return outDir;
    }

    private void validateTemplate(CertificateTemplate template) throws InvalidTemplateException {
        if (template == null) {
            throw new InvalidTemplateException("Template can not be null!");
        }
        if (template.getAbsolutePath() == null) {
            throw new InvalidTemplateException("Template absolutePath can not be null!");
        }
        File f = new File(template.getAbsolutePath());
        if (!f.exists()) {
            throw new InvalidTemplateException("Template file not found on " + template.getAbsolutePath());
        }

    }

    private void validateClass(CodeClubClass codeClass) throws InvalidClassException {
        if (codeClass == null) {
            throw new InvalidClassException("CodeClubClass can not be null!");
        }
        if (codeClass.getLeaderName() == null || codeClass.getLeaderName().isEmpty()) {
            throw new InvalidClassException("Code Club Leader can not be null!");
        }
        if (codeClass.getStudentsNames() == null || codeClass.getStudentsNames().isEmpty()) {
            throw new InvalidClassException("Code Club Students can not be null!");
        }
    }

}
