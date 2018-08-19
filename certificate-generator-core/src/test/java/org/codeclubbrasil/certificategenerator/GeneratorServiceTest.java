package org.codeclubbrasil.certificategenerator;

import org.codeclubbrasil.certificategenerator.domain.CodeClubClass;
import org.codeclubbrasil.certificategenerator.exception.InvalidTemplateException;
import org.codeclubbrasil.certificategenerator.service.GeneratorService;
import org.junit.Test;

public class GeneratorServiceTest {

    @Test(expected = InvalidTemplateException.class)
    public void whenTemplateIsNullThenException() throws Exception {
        new GeneratorService().generate(null, CodeClubClass.fromClassName("test"));
    }


}
