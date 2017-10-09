package org.codeclubbrasil.certificategenerator.domain;

import java.io.File;
import java.util.Map;

/**
 * Represents a certificate template
 * 
 * @author sandrogiacom@gmail.com
 *
 */
public class Template {

	/**
	 * Template file name
	 */
	private String name;
	/**
	 * Template file path
	 */
	private String path;
	/**
	 * Template editable fields
	 */
	private Map<String, String> fields;
	/**
	 * Type of template
	 */
	private TemplateType type;

	public Template(TemplateType type) {
		this.type = type;
	}

	public String getAbsolutePath() {
		return path + File.separator + name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Map<String, String> getFields() {
		return fields;
	}

	public void setFields(Map<String, String> fields) {
		this.fields = fields;
	}

	public TemplateType getType() {
		return type;
	}

	public void setType(TemplateType type) {
		this.type = type;
	}

}
