package org.codeclubbrasil.certificategenerator.domain;

public enum TemplateType {

	PDF(".pdf"), DOC(".doc"), XLS(".xls");

	private String extension;

	TemplateType(String extension) {
		this.extension = extension;
	}

	public String getExtension() {
		return extension;
	}

}
