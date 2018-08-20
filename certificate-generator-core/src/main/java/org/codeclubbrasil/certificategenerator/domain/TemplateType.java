package org.codeclubbrasil.certificategenerator.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TemplateType {

    PDF(".pdf");

    @Getter
    private String extension;

}
