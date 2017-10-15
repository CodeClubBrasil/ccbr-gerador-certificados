package org.codeclubbrasil.certificategenerator;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.codeclubbrasil.certificategenerator.domain.CertificateTemplate;
import org.codeclubbrasil.certificategenerator.domain.CodeClubClass;
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
		String templateName = "scratch1";
		String leader = "Sandro Giacomozzi";
		List<String> students = Arrays.asList("ADRIANA KRUGER GIACOMOZZI", "ISABELA KRUGER GIACOMOZZI");
		assertTrue(callGenerate("Scratch1", templateName, leader, students));
	}

	@Test()
	public void whenDataOkThenGenerateScratch2() throws Exception {
		String templateName = "scratch2";
		String leader = "Sandro Luciano Giacomozzi";
		List<String> students = Arrays.asList("ARTHUR SCHRAMM DE LIMA", "EVILYN BIANCA DE PAULA JUNG");
		assertTrue(callGenerate("Scratch2", templateName, leader, students));
	}

	@Test()
	public void whenDataOkThenGeneratepython1() throws Exception {
		String templateName = "python1";
		String leader = "Sandro Luciano Giacomozzi";
		List<String> students = Arrays.asList("FERNANDO CORRÊA RODRIGUES DOS SANTOS",
				"GUILHERME HANDRYCH DE SOUZA ALVES");
		assertTrue(callGenerate("python1", templateName, leader, students));
	}

	@Test()
	public void whenDataOkThenGeneratepython2() throws Exception {
		String templateName = "python2";
		String leader = "Sandro Luciano Giacomozzi";
		List<String> students = Arrays.asList("RAFAEL PONTES STENGER", "LARISSA MARTINS DO AMARAL");
		assertTrue(callGenerate("python2", templateName, leader, students));
	}

	@Test()
	public void whenDataOkThenGenerateWeb1() throws Exception {
		String templateName = "web1";
		String leader = "Sandro Luciano Giacomozzi";
		List<String> students = Arrays.asList("WENDELL KAWE SAMPAIO DE SOUZA", "RICHARD PAULINO DA SILVA");
		assertTrue(callGenerate("Web1", templateName, leader, students));
	}

	@Test()
	public void whenDataOkThenGenerateWeb2() throws Exception {
		String templateName = "web2";
		String leader = "Sandro Luciano Giacomozzi";
		List<String> students = Arrays.asList("TAMARIS DA SILVA DE CARVALHO", "CAROLINA FORTUNATO DA LUZ",
				"RICHARD YAGO SAMPAIO DE SOUZA");
		assertTrue(callGenerate("Web2", templateName, leader, students));
	}

	@Test()
	public void whenGeneratorZipThenGetZip() throws Exception {
		String templateName = "web2";
		String leader = "Sandro Giacomozzi";
		List<String> students = Arrays.asList("TAMARIS DA SILVA DE CARVALHO", "CAROLINA FORTUNATO DA LUZ",
				"RICHARD YAGO SAMPAIO DE SOUZA");
		assertTrue(callGenerateAndZip("Web2Zip", templateName, leader, students));
	}

	private boolean callGenerate(String className, String templateName, String leader, List<String> students)
			throws Exception {
		GeneratorService service = new GeneratorService();
		CertificateTemplate template = CertificateTemplate.fromTemplateNamePDF(templateName);
		CodeClubClass codeClass = CodeClubClass.fromClassName(className);
		codeClass.setLeaderName(leader);
		codeClass.setStudentsNames(students);
		File outDir = new File(service.generate(template, codeClass));
		this.outDir = outDir;
		return (outDir.list().length == codeClass.getStudentsNames().size());
	}

	private boolean callGenerateAndZip(String className, String templateName, String leader, List<String> students)
			throws Exception {
		GeneratorService service = new GeneratorService();
		CertificateTemplate template = CertificateTemplate.fromTemplateNamePDF(templateName);
		CodeClubClass codeClass = CodeClubClass.fromClassName(className);
		codeClass.setLeaderName(leader);
		codeClass.setStudentsNames(students);
		File outDir = new File(service.generateAndSaveZip(template, codeClass));
		this.outDir = new File(outDir.getParent());
		return (outDir.exists());
	}

}
