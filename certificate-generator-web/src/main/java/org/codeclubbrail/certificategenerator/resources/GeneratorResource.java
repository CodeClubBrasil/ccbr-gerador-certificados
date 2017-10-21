package org.codeclubbrail.certificategenerator.resources;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.codeclubbrail.certificategenerator.resources.model.Certificate;
import org.codeclubbrail.certificategenerator.resources.model.Course;
import org.codeclubbrail.certificategenerator.service.GeneratorServiceWeb;
import org.codeclubbrasil.certificategenerator.domain.GenerateOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@EnableAutoConfiguration
@RequestMapping("/certificate")
public class GeneratorResource {

	private static final String MAIN_VIEW = "GeradorCertificado";

	@Autowired
	private GeneratorServiceWeb generatorService;

	@PostMapping(path = "/generate")
	public ResponseEntity<Resource> generate(Certificate certificate) throws Exception {
		GenerateOutput out = generatorService.generate(certificate);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(out.getOutputZipFileMame()));
		return ResponseEntity.ok()
				.header("Content-Disposition", "attachment; filename=" + certificate.getCourse() + ".zip")
				.contentLength(new File(out.getOutputZipFileMame()).length())
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
		return Course.fromAllAvaibleCourses();
	}

}
