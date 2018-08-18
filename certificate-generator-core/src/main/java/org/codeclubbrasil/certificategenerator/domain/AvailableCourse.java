package org.codeclubbrasil.certificategenerator.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
/**
 * Certificados disponiveis
 */
public enum AvailableCourse {

    PYTHON1("python1", "Python 1"),
    PYTHON2("python2", "Python 2"),
    SCRATCH1("scratch1", "Scratch 1"),
    SCRATCH2("scratch2", "Scratch 2"),
    SCRATCH3("scratch3", "Scratch 3"),
    WEB1("web1", "Web 1"),
    WEB2("web2", "Web 2");

    @Getter
    /**
     * Código do certificado
     */
    private String code;
    @Getter
    /**
     * Nome do certificado
     */
    private String name;

}
