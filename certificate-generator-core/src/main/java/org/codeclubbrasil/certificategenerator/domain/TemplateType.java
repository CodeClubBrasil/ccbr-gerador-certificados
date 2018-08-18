package org.codeclubbrasil.certificategenerator.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Tipos de template
 */
@AllArgsConstructor
public enum TemplateType {

    /**
     * PDF
     */
    PDF(".pdf");

    @Getter
    private String extension;

}
