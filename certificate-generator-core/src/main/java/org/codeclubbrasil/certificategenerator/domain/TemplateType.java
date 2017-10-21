package org.codeclubbrasil.certificategenerator.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TemplateType {

	PDF(".pdf"), DOC(".doc"), XLS(".xls");

	@Getter
	private String extension;

}
