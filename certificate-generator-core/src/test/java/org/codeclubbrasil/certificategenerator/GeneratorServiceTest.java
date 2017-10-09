package org.codeclubbrasil.certificategenerator;

import org.codeclubbrasil.certificategenerator.domain.Template;
import org.codeclubbrasil.certificategenerator.domain.TemplateType;
import org.codeclubbrasil.certificategenerator.exception.GeneratorException;
import org.codeclubbrasil.certificategenerator.exception.InvalidTemplateException;
import org.codeclubbrasil.certificategenerator.service.GeneratorService;
import org.junit.Test;

public class GeneratorServiceTest {

	@Test(expected = GeneratorException.class)
	public void whenTemplateIsNullThenException() throws Exception {
		GeneratorService service = new GeneratorService();
		service.generate(null, null);

	}

	@Test(expected = InvalidTemplateException.class)
	public void whenTemplateIsNotPDFThenException() throws Exception {
		GeneratorService service = new GeneratorService();
		Template template = new Template(TemplateType.DOC);
		service.generate(template, null);
	}

}
