package org.codeclubbrail.certificategenerator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.codeclubbrail.certificategenerator.resources.model.Course;
import org.codeclubbrasil.certificategenerator.domain.AvailableCourse;
import org.junit.Before;
import org.junit.Test;

public class CourseTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void whenAvailableCoursePython1ThenCheckData() {
        Course course = Course.fromAvailableCourse(AvailableCourse.PYTHON1);
        assertThat(course.getCode().concat(course.getDescription()), equalTo("python1".concat("Python 1")));
    }

    @Test
    public void whenAvailableCoursePython2ThenCheckData() {
        Course course = Course.fromAvailableCourse(AvailableCourse.PYTHON2);
        assertThat(course.getCode().concat(course.getDescription()), equalTo("python2".concat("Python 2")));
    }

    @Test
    public void whenAvailableCourseScratch1ThenCheckData() {
        Course course = Course.fromAvailableCourse(AvailableCourse.SCRATCH1);
        assertThat(course.getCode().concat(course.getDescription()), equalTo("scratch1".concat("Scratch 1")));
    }

    @Test
    public void whenAvailableCourseScratch2ThenCheckData() {
        Course course = Course.fromAvailableCourse(AvailableCourse.SCRATCH2);
        assertThat(course.getCode().concat(course.getDescription()), equalTo("scratch2".concat("Scratch 2")));
    }

    @Test
    public void whenAvailableCourseWeb1ThenCheckData() {
        Course course = Course.fromAvailableCourse(AvailableCourse.WEB1);
        assertThat(course.getCode().concat(course.getDescription()), equalTo("web1".concat("Web 1")));
    }

    @Test
    public void whenAvailableCourseWeb2ThenCheckData() {
        Course course = Course.fromAvailableCourse(AvailableCourse.WEB2);
        assertThat(course.getCode().concat(course.getDescription()), equalTo("web2".concat("Web 2")));
    }

    @Test
    public void testFromAllAvaibleCourses() {
        List<Course> allCources = Course.fromAllAvaibleCourses();
        assertThat(allCources.size(), equalTo(AvailableCourse.values().length));
    }

}
