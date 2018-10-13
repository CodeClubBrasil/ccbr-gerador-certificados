package org.codeclubbrasil.certificategenerator.api.v1.delegate;

import org.codeclubbrasil.certificategenerator.api.v1.dto.GenerateDTO;
import org.codeclubbrasil.certificategenerator.domain.CertificateTemplate;
import org.codeclubbrasil.certificategenerator.domain.CodeClubClass;
import org.codeclubbrasil.certificategenerator.domain.GenerateOutput;
import org.codeclubbrasil.certificategenerator.service.GeneratorService;

public class GeneratorDelegate {

    public static GenerateOutput generateOutput(GenerateDTO generateDTO) throws Exception {
        GeneratorService service = new GeneratorService();
        CertificateTemplate template = CertificateTemplate.fromTemplateNamePDF(generateDTO.getCourse());
        CodeClubClass codeClass = CodeClubClass.fromClassName(generateDTO.getCourse());
        codeClass.setLeaderName(generateDTO.getLeaderName());
        codeClass.setStudentsNames(generateDTO.getStudents());
        GenerateOutput out = service.generateAndZipFile(template, codeClass);
        return out;
    }

}
