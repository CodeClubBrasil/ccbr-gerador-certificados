package org.codeclubbrasil.certificategenerator.domain;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.codeclubbrasil.certificategenerator.exception.InvalidTemplateException;

import lombok.Data;

@Data
public class CertificateTemplate {

	private static final String templateTempDir = System.getProperty("java.io.tmpdir") + "/pdf/";
	private String name;
	private String path;
	private TemplateType type;

	public static CertificateTemplate fromTemplateNamePDF(String name) throws Exception {
		TemplateType templateType = TemplateType.PDF;
		CertificateTemplate template = new CertificateTemplate(templateType);
		template.setName(name + templateType.getExtension());
		template.setPath(templateTempDir);
		prepateTempDir();
		saveTemplateFile(template);
		return template;
	}

	private static void prepateTempDir() {
		File tempDir = new File(templateTempDir);
		try {
			FileUtils.forceDelete(tempDir);
		} catch (IOException e) {
		}
		try {
			FileUtils.forceMkdir(tempDir);
		} catch (IOException e) {
		}

	}

	private static void saveTemplateFile(CertificateTemplate template) throws InvalidTemplateException {
		String templateResource = "/templates/" + template.getName();
		InputStream initialStream = template.getClass().getResourceAsStream(templateResource);
		if (initialStream == null) {
			throw new InvalidTemplateException("Template Resource not found: " + templateResource);
		}
		String templateTempFile = templateTempDir + template.getName();
		File templateFile = new File(templateTempFile);
		try {
			java.nio.file.Files.copy(initialStream, templateFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new InvalidTemplateException("Error on copy template to temp dir: " + templateFile, e);
		}
		IOUtils.closeQuietly(initialStream);
	}

	private CertificateTemplate(TemplateType type) {
		this.type = type;
	}

	public String getAbsolutePath() {
		return path + File.separator + name;
	}

}
