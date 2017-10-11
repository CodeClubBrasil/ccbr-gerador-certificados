package org.codeclubbrail.certificategenerator.resources.model;

public class Course {

	private String code;
	private String description;
	
	public Course(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
