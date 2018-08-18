package org.codeclubbrasil.certificategenerator.domain;

import java.util.List;

import lombok.Data;

/**
 * Representa uma turma
 */
@Data
public class CodeClubClass {

    private String className;
    private String leaderName;
    private List<String> studentsNames;

    /**
     * Cria uma classe pelo nome
     * @param className String
     * @return CodeClubClass
     */
    public static CodeClubClass fromClassName(String className) {
        CodeClubClass codeClubClass = new CodeClubClass();
        codeClubClass.setClassName(className);
        return codeClubClass;
    }

}
