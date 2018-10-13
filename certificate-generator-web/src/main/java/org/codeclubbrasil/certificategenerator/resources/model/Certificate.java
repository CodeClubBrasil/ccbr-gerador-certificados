package org.codeclubbrasil.certificategenerator.resources.model;

import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class Certificate {

    private String leaderName;
    private String students;
    private String course;

    public List<String> getStudentsNamesList() {
        setStudents(getStudents().replaceAll("\n", ";"));
        setStudents(getStudents().replaceAll("\r", ""));
        return Arrays.asList(getStudents().split(";"));
    }
}
