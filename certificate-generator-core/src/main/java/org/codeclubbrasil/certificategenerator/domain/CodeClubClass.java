package org.codeclubbrasil.certificategenerator.domain;

import java.util.List;

/**
 * 
 * Represents a Code Club Class
 * 
 * @author sandrogiacom@gmail.com
 *
 */
public class CodeClubClass {

	/**
	 * Name of class
	 */
	private String name;
	/**
	 * Code Club Leader
	 */
	private String leader;
	/**
	 * Names of Code Club students
	 */
	private List<String> students;

	public CodeClubClass(String name) {
		super();
		this.name = name;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public List<String> getStudents() {
		return students;
	}

	public void setStudents(List<String> students) {
		this.students = students;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
