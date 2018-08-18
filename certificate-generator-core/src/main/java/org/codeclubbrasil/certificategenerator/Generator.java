package org.codeclubbrasil.certificategenerator;

import org.codeclubbrasil.certificategenerator.domain.CertificateTemplate;
import org.codeclubbrasil.certificategenerator.domain.CodeClubClass;
import org.codeclubbrasil.certificategenerator.domain.GenerateOutput;
import org.codeclubbrasil.certificategenerator.exception.GeneratorException;
import org.codeclubbrasil.certificategenerator.exception.InvalidClassException;
import org.codeclubbrasil.certificategenerator.exception.InvalidTemplateException;

/**
 * Interface que permite gerar certificados
 */
public interface Generator {

    /**
     * Gera um conjunto de certificados conforme os parâmetros informados
     * @param template CertificateTemplate
     * @param codeClass CodeClubClass
     * @return GenerateOutput
     * @throws GeneratorException
     * @throws InvalidClassException
     * @throws InvalidTemplateException
     */
    GenerateOutput generate(CertificateTemplate template, CodeClubClass codeClass)
            throws GeneratorException, InvalidClassException, InvalidTemplateException;

}
