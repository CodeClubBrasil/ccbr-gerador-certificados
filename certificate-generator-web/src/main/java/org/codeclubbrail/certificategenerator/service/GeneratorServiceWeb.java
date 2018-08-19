package org.codeclubbrail.certificategenerator.service;

import org.codeclubbrail.certificategenerator.resources.model.Certificate;
import org.codeclubbrasil.certificategenerator.domain.CertificateTemplate;
import org.codeclubbrasil.certificategenerator.domain.CodeClubClass;
import org.codeclubbrasil.certificategenerator.domain.GenerateOutput;
import org.codeclubbrasil.certificategenerator.service.GeneratorService;
import org.springframework.stereotype.Service;

@Service
public class GeneratorServiceWeb {

    public GenerateOutput generate(Certificate certificate) throws Exception {
        GeneratorService service = new GeneratorService();
        CertificateTemplate template = CertificateTemplate.fromTemplateNamePDF(certificate.getCourse());
        CodeClubClass codeClass = CodeClubClass.fromClassName(certificate.getCourse());
        codeClass.setLeaderName(certificate.getLeaderName());
        codeClass.setStudentsNames(certificate.getStudentsNamesList());
        GenerateOutput out = service.generateAndZipFile(template, codeClass);
        return out;
    }

}
