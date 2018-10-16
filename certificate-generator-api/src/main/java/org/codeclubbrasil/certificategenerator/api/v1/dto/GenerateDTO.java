package org.codeclubbrasil.certificategenerator.api.v1.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Certificate Generate")
public class GenerateDTO {

    @ApiModelProperty(value = "Nome do líder", required = true)
    private String leaderName;
    @ApiModelProperty(value = "Lista de alunos", required = true)
    private List<String> students;
    @ApiModelProperty(value = "Código do curso", required = true)
    private String course;

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public static final class GenerateDTOBuilder {
        private GenerateDTO generateDTO;

        private GenerateDTOBuilder() {
            generateDTO = new GenerateDTO();
        }

        public static GenerateDTOBuilder of() {
            return new GenerateDTOBuilder();
        }

        public GenerateDTOBuilder withLeaderName(String leaderName) {
            generateDTO.setLeaderName(leaderName);
            return this;
        }

        public GenerateDTOBuilder withStudents(List<String> students) {
            generateDTO.setStudents(students);
            return this;
        }

        public GenerateDTOBuilder withCourse(String course) {
            generateDTO.setCourse(course);
            return this;
        }

        public GenerateDTO build() {
            return generateDTO;
        }
    }
}
