package org.codeclubbrail.certificategenerator.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.codeclubbrail.certificategenerator.resources.model.Certificate;
import org.codeclubbrail.certificategenerator.resources.model.Course;
import org.codeclubbrasil.certificategenerator.domain.CodeClubClass;
import org.codeclubbrasil.certificategenerator.domain.Template;
import org.codeclubbrasil.certificategenerator.domain.TemplateType;
import org.codeclubbrasil.certificategenerator.service.GeneratorService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@EnableAutoConfiguration
@RequestMapping("/certificate")
public class GeneratorResource {

	private static final String MAIN_VIEW = "GeradorCertificado";

	@RequestMapping(path = "/generate", method = RequestMethod.POST)
	public ResponseEntity<Resource> generate(Certificate certificate) throws Exception {

		String templateName = "/pdf/" + certificate.getCourse() + ".pdf";
		String leader = certificate.getLeaderName();

		certificate.setStudents(certificate.getStudents().replaceAll("\n", ";"));
		certificate.setStudents(certificate.getStudents().replaceAll("\r", ""));

		List<String> students = Arrays.asList(certificate.getStudents().split(";"));

		InputStream initialStream = getClass().getResourceAsStream(templateName);

		String templateTempDir = System.getProperty("java.io.tmpdir") + "/pdf/";
		String templateTempFile = templateTempDir + certificate.getCourse();

		File tempDir = new File(templateTempDir);

		try {
			FileUtils.forceDelete(tempDir);
		} catch (Exception e) {
		}

		try {
			FileUtils.forceMkdir(tempDir);
		} catch (Exception e) {
		}

		File templateFile = new File(templateTempFile);

		java.nio.file.Files.copy(initialStream, templateFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

		IOUtils.closeQuietly(initialStream);

		GeneratorService service = new GeneratorService();
		Template template = new Template(TemplateType.PDF);
		template.setName(templateFile.getName());
		template.setPath(templateFile.getParentFile().toString());
		CodeClubClass codeClass = new CodeClubClass(certificate.getCourse());
		codeClass.setLeader(leader);
		codeClass.setStudents(students);
		String zipFile = service.generateAndSaveZip(template, codeClass);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(zipFile));

		return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=" + codeClass.getName() + ".zip")
				.contentLength(new File(zipFile).length())
				.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);

	}

	@RequestMapping("/")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(MAIN_VIEW);
		Certificate certificate = new Certificate();
		mv.addObject("certificate", certificate);
		return mv;
	}

	@ModelAttribute("allCourses")
	public List<Course> allCources() {
		List<Course> allCources = new ArrayList<Course>();
		allCources.add(new Course("scratch1", "Scratch 1"));
		allCources.add(new Course("scratch2", "Scratch 2"));
		allCources.add(new Course("phyton1", "Phyton 1"));
		allCources.add(new Course("phyton2", "Phyton 2"));
		allCources.add(new Course("web1", "Web 1"));
		allCources.add(new Course("web2", "Web 2"));

		return allCources;
	}

}
