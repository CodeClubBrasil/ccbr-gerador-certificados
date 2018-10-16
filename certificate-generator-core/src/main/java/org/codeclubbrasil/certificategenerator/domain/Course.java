package org.codeclubbrasil.certificategenerator.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Course {

    private String code;
    private String description;

    public static Course fromAvailableCourse(AvailableCourse availableCourse) {
        Course course = new Course();
        course.setCode(availableCourse.getCode());
        course.setDescription(availableCourse.getName());
        return course;
    }

    public static List<Course> fromAllAvaibleCourses() {
        List<Course> allCources = new ArrayList<Course>();
        for (AvailableCourse course : AvailableCourse.values()) {
            allCources.add(fromAvailableCourse(course));
        }
        return allCources;
    }

}
