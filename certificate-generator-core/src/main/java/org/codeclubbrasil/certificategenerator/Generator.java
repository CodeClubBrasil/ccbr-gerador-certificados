package org.codeclubbrasil.certificategenerator;

import org.codeclubbrasil.certificategenerator.domain.CodeClubClass;
import org.codeclubbrasil.certificategenerator.domain.Template;
import org.codeclubbrasil.certificategenerator.exception.GeneratorException;
import org.codeclubbrasil.certificategenerator.exception.InvalidClassException;
import org.codeclubbrasil.certificategenerator.exception.InvalidTemplateException;

/**
 * Generator interface for all supported types
 * 
 * @author sandrogiacom@gmail.com
 * 
 */
public interface Generator {

	String generate(Template template, CodeClubClass codeClass)
			throws GeneratorException, InvalidClassException, InvalidTemplateException;

}
