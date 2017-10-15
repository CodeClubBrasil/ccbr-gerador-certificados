package org.codeclubbrail.certificategenerator.resources.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Course {

	private String code;
	private String description;

	public static Course fromData(String code, String description) {
		Course course = new Course();
		course.setCode(code);
		course.setDescription(description);
		return course;
	}

	public static List<Course> fromAllAvaibleCourses() {
		List<Course> allCources = new ArrayList<Course>();
		allCources.add(fromData("scratch1", "Scratch 1"));
		allCources.add(fromData("scratch2", "Scratch 2"));
		allCources.add(fromData("python1", "Python 1"));
		allCources.add(fromData("python2", "Python 2"));
		allCources.add(fromData("web1", "Web 1"));
		allCources.add(fromData("web2", "Web 2"));
		return allCources;
	}

}
