package org.codeclubbrasil.certificategenerator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.codeclubbrasil.certificategenerator.domain.CertificateTemplate;
import org.codeclubbrasil.certificategenerator.domain.CodeClubClass;
import org.codeclubbrasil.certificategenerator.domain.GenerateOutput;
import org.codeclubbrasil.certificategenerator.service.GeneratorService;
import org.hamcrest.io.FileMatchers;
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
		callGenerate("Scratch1", templateName, leader, students);
	}

	@Test()
	public void whenDataOkThenGenerateScratch2() throws Exception {
		String templateName = "scratch2";
		String leader = "Sandro Luciano Giacomozzi";
		List<String> students = Arrays.asList("ARTHUR SCHRAMM DE LIMA", "EVILYN BIANCA DE PAULA JUNG");
		callGenerate("Scratch2", templateName, leader, students);
	}

	@Test()
	public void whenDataOkThenGeneratePhyton1() throws Exception {
		String templateName = "phyton1";
		String leader = "Sandro Luciano Giacomozzi";
		List<String> students = Arrays.asList("FERNANDO CORRÊA RODRIGUES DOS SANTOS",
				"GUILHERME HANDRYCH DE SOUZA ALVES");
		callGenerate("Phyton1", templateName, leader, students);
	}

	@Test()
	public void whenDataOkThenGeneratePhyton2() throws Exception {
		String templateName = "phyton2";
		String leader = "Sandro Luciano Giacomozzi";
		List<String> students = Arrays.asList("RAFAEL PONTES STENGER", "LARISSA MARTINS DO AMARAL");
		callGenerate("Phyton2", templateName, leader, students);
	}

	@Test()
	public void whenDataOkThenGenerateWeb1() throws Exception {
		String templateName = "web1";
		String leader = "Sandro Luciano Giacomozzi";
		List<String> students = Arrays.asList("WENDELL KAWE SAMPAIO DE SOUZA", "RICHARD PAULINO DA SILVA");
		callGenerate("Web1", templateName, leader, students);
	}

	@Test()
	public void whenDataOkThenGenerateWeb2() throws Exception {
		String templateName = "web2";
		String leader = "Sandro Luciano Giacomozzi";
		List<String> students = Arrays.asList("TAMARIS DA SILVA DE CARVALHO", "CAROLINA FORTUNATO DA LUZ",
				"RICHARD YAGO SAMPAIO DE SOUZA");
		callGenerate("Web2", templateName, leader, students);
	}

	
	@Test()
	public void whenDataOkThenGenerateZipWeb2() throws Exception {
		String templateName = "web2";
		String leader = "Sandro Luciano Giacomozzi";
		List<String> students = Arrays.asList("TAMARIS DA SILVA DE CARVALHO", "CAROLINA FORTUNATO DA LUZ",
				"RICHARD YAGO SAMPAIO DE SOUZA");
		callGenerateAndZip("Web2", templateName, leader, students);
	}


	private void callGenerate(String className, String templateName, String leader, List<String> students)
			throws Exception {
		GeneratorService service = new GeneratorService();
		CertificateTemplate template = CertificateTemplate.fromTemplateNamePDF(templateName);
		CodeClubClass codeClass = CodeClubClass.fromClassName(className);
		codeClass.setLeaderName(leader);
		codeClass.setStudentsNames(students);
		GenerateOutput out = service.generate(template, codeClass);
		this.outDir = new File(out.getOutputDir());
		assertThat(outDir.list().length, equalTo(codeClass.getStudentsNames().size()));
	}

	private void callGenerateAndZip(String className, String templateName, String leader, List<String> students) 
			throws Exception {
		GeneratorService service = new GeneratorService();
		CertificateTemplate template = CertificateTemplate.fromTemplateNamePDF(templateName);
		CodeClubClass codeClass = CodeClubClass.fromClassName(className);
		codeClass.setLeaderName(leader);
		codeClass.setStudentsNames(students);
		GenerateOutput out = service.generateAndZipFile(template, codeClass);
		File outZip = new File(out.getOutputZipFileMame());
		this.outDir = new File(out.getOutputDir());
		assertThat(outZip, FileMatchers.anExistingFile());
	}

}
