package org.codeclubbrasil.certificategenerator.api.v1.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.codeclubbrasil.certificategenerator.api.v1.delegate.GeneratorDelegate;
import org.codeclubbrasil.certificategenerator.api.v1.dto.GenerateDTO;
import org.codeclubbrasil.certificategenerator.domain.Course;
import org.codeclubbrasil.certificategenerator.domain.GenerateOutput;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Gerador de Certificados", tags = {"Gerador de Certificados"},  description = "Gerador de certificados para o Code Club")
public class CertificateGeneratorController {

    @PostMapping(value = "/generate", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Gerar certificados")
    public ResponseEntity<InputStreamResource> generate(
        @ApiParam(value = "Dados para geração", required = true) @RequestBody GenerateDTO generateDTO) throws Exception {

        GenerateOutput out = GeneratorDelegate.generateOutput(generateDTO);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(out.getOutputZipFileMame()));

        return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=" + generateDTO.getCourse() + ".zip")
            .contentLength(new File(out.getOutputZipFileMame()).length())
            .contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
    }

    @GetMapping(value = "/courses", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "Listar cursos disponíveis")
    public List<Course> allCources() {
        return Course.fromAllAvaibleCourses();
    }

}
