package org.codeclubbrasil.certificategenerator.service;

import org.codeclubbrasil.certificategenerator.Generator;
import org.codeclubbrasil.certificategenerator.domain.CodeClubClass;
import org.codeclubbrasil.certificategenerator.domain.Template;
import org.codeclubbrasil.certificategenerator.exception.GeneratorException;
import org.codeclubbrasil.certificategenerator.exception.InvalidTemplateException;
import org.codeclubbrasil.certificategenerator.utils.ZipUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Certificate Generator Service
 * 
 * @author sandrogiacom@gmail.com
 *
 */
public class GeneratorService {

	private final static Logger log = LoggerFactory.getLogger(GeneratorService.class);

	public String generate(Template template, CodeClubClass codeClass) throws Exception {
		log.info("GeneratorService.generate");
		Generator gen = getGenerator(template);
		String outputDir = gen.generate(template, codeClass);
		log.info("GeneratorService output: " + outputDir);
		return outputDir;
	}

	public void generateAndSaveZip(Template template, CodeClubClass codeClass, String zipFileName) throws Exception {
		log.info("GeneratorService.generateAndSaveZip");
		byte[] zip = generateBytes(template, codeClass);
		ZipUtils.saveZipFile(zip, zipFileName);
	}

	public byte[] generateBytes(Template template, CodeClubClass codeClass) throws Exception {
		log.info("GeneratorService.generateBytes");
		return ZipUtils.zipFiles(generate(template, codeClass));
	}

	public String generateAndSaveZip(Template template, CodeClubClass codeClass) throws Exception {
		log.info("GeneratorService.generateAndSaveZip");
		String pdfInput = generate(template, codeClass);
		byte[] zip = ZipUtils.zipFiles(pdfInput);
		String zipFileName = pdfInput + codeClass.getName() + ".zip";
		ZipUtils.saveZipFile(zip, zipFileName);
		log.info("GeneratorService.zipFileName: " + zipFileName);
		return zipFileName;
	}

	private Generator getGenerator(Template template) throws GeneratorException, InvalidTemplateException {

		if (template == null) {
			throw new GeneratorException("Template can not be null!");
		}

		switch (template.getType()) {
		case PDF:
			return PDFGenerator.getInstance();
		default:
			throw new InvalidTemplateException("TemplateType is null or invalid. Only accepts PDF type!");
		}
	}

}
