package org.codeclubbrasil.certificategenerator;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.codeclubbrasil.certificategenerator.domain.CodeClubClass;
import org.codeclubbrasil.certificategenerator.domain.Template;
import org.codeclubbrasil.certificategenerator.domain.TemplateType;
import org.codeclubbrasil.certificategenerator.service.GeneratorService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GeneratorServiceFilesTest {

	private File outDir;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void cleanUp() throws Exception {
		FileUtils.deleteDirectory(outDir);
	}

	@Test()
	public void whenDataOkThenGenerateScratch1() throws Exception {
		String templateName = "templates/scratch1.pdf";
		String leader = "Sandro Giacomozzi";
		List<String> students = Arrays.asList("ADRIANA KRUGER GIACOMOZZI", "ISABELA KRUGER GIACOMOZZI");
		assertTrue(callGenerate("Scratch1", templateName, leader, students));
	}

	@Test()
	public void whenDataOkThenGenerateScratch2() throws Exception {
		String templateName = "templates/scratch2.pdf";
		String leader = "Sandro Luciano Giacomozzi";
		List<String> students = Arrays.asList("ARTHUR SCHRAMM DE LIMA", "EVILYN BIANCA DE PAULA JUNG");
		assertTrue(callGenerate("Scratch2", templateName, leader, students));
	}

	@Test()
	public void whenDataOkThenGeneratePhyton1() throws Exception {
		String templateName = "templates/phyton1.pdf";
		String leader = "Sandro Luciano Giacomozzi";
		List<String> students = Arrays.asList("FERNANDO CORRÊA RODRIGUES DOS SANTOS",
				"GUILHERME HANDRYCH DE SOUZA ALVES");
		assertTrue(callGenerate("Phyton1", templateName, leader, students));
	}

	@Test()
	public void whenDataOkThenGeneratePhyton2() throws Exception {
		String templateName = "templates/phyton2.pdf";
		String leader = "Sandro Luciano Giacomozzi";
		List<String> students = Arrays.asList("RAFAEL PONTES STENGER", "LARISSA MARTINS DO AMARAL");
		assertTrue(callGenerate("Phyton2", templateName, leader, students));
	}

	@Test()
	public void whenDataOkThenGenerateWeb1() throws Exception {
		String templateName = "templates/web1.pdf";
		String leader = "Sandro Luciano Giacomozzi";
		List<String> students = Arrays.asList("WENDELL KAWE SAMPAIO DE SOUZA", "RICHARD PAULINO DA SILVA");
		assertTrue(callGenerate("Web1", templateName, leader, students));
	}

	@Test()
	public void whenDataOkThenGenerateWeb2() throws Exception {
		String templateName = "templates/web2.pdf";
		String leader = "Sandro Luciano Giacomozzi";
		List<String> students = Arrays.asList("TAMARIS DA SILVA DE CARVALHO", "CAROLINA FORTUNATO DA LUZ",
				"RICHARD YAGO SAMPAIO DE SOUZA");
		assertTrue(callGenerate("Web2", templateName, leader, students));
	}

	@Test()
	public void whenGeneratorZipThenGetZip() throws Exception {
		String templateName = "templates/web2.pdf";
		String leader = "Sandro Giacomozzi";
		List<String> students = Arrays.asList("TAMARIS DA SILVA DE CARVALHO", "CAROLINA FORTUNATO DA LUZ",
				"RICHARD YAGO SAMPAIO DE SOUZA");
		assertTrue(callGenerateAndZip("Web2Zip", templateName, leader, students));
	}

	private boolean callGenerate(String className, String templateName, String leader, List<String> students)
			throws Exception {
		GeneratorService service = new GeneratorService();
		Template template = new Template(TemplateType.PDF);
		URL templateURL = ClassLoader.getSystemResource(templateName);
		File templateFile = new File(templateURL.getFile());
		template.setName(templateFile.getName());
		template.setPath(templateFile.getParentFile().toString());
		CodeClubClass codeClass = new CodeClubClass(className);
		codeClass.setLeader(leader);
		codeClass.setStudents(students);
		File outDir = new File(service.generate(template, codeClass));
		this.outDir = outDir;
		return (outDir.list().length == codeClass.getStudents().size());
	}

	private boolean callGenerateAndZip(String className, String templateName, String leader, List<String> students)
			throws Exception {
		GeneratorService service = new GeneratorService();
		Template template = new Template(TemplateType.PDF);
		URL templateURL = ClassLoader.getSystemResource(templateName);
		File templateFile = new File(templateURL.getFile());
		template.setName(templateFile.getName());
		template.setPath(templateFile.getParentFile().toString());
		CodeClubClass codeClass = new CodeClubClass(className);
		codeClass.setLeader(leader);
		codeClass.setStudents(students);
		File outDir = new File(service.generateAndSaveZip(template, codeClass));
		this.outDir = new File(outDir.getParent());
		return (outDir.exists());
	}

}
