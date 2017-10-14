package org.codeclubbrasil.certificategenerator.domain;

import java.util.List;

import lombok.Data;

@Data
public class CodeClubClass {

	private String className;
	private String leaderName;
	private List<String> studentsNames;

	public static CodeClubClass fromClassName(String className) {
		CodeClubClass codeClubClass = new CodeClubClass();
		codeClubClass.setClassName(className);
		return codeClubClass;
	}

	
}
