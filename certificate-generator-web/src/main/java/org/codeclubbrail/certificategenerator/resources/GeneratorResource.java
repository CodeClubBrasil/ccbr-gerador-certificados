package org.codeclubbrail.certificategenerator.resources;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.codeclubbrail.certificategenerator.resources.model.Certificate;
import org.codeclubbrail.certificategenerator.resources.model.Course;
import org.codeclubbrasil.certificategenerator.domain.CertificateTemplate;
import org.codeclubbrasil.certificategenerator.domain.CodeClubClass;
import org.codeclubbrasil.certificategenerator.domain.GenerateOutput;
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
		GeneratorService service = new GeneratorService();
		CertificateTemplate template = CertificateTemplate.fromTemplateNamePDF(certificate.getCourse());
		CodeClubClass codeClass = CodeClubClass.fromClassName(certificate.getCourse());
		codeClass.setLeaderName(certificate.getLeaderName());
		codeClass.setStudentsNames(certificate.getStudentsNamesList());
		GenerateOutput out = service.generateAndZipFile(template, codeClass);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(out.getOutputZipFileMame()));

		return ResponseEntity.ok()
				.header("Content-Disposition", "attachment; filename=" + codeClass.getClassName() + ".zip")
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
