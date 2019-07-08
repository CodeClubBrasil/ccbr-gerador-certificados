package org.codeclubbrasil.certificategenerator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.codeclubbrasil.certificategenerator.domain.AvailableCourse;
import org.codeclubbrasil.certificategenerator.domain.CertificateTemplate;
import org.codeclubbrasil.certificategenerator.domain.CodeClubClass;
import org.codeclubbrasil.certificategenerator.domain.GenerateOutput;
import org.codeclubbrasil.certificategenerator.service.GeneratorService;
import org.junit.After;
import org.junit.Test;

public class GeneratorServiceFilesTest {

    private static final String LEADER = "Sandro Giacomozzi";
    private File outDir;

    @After
    public void cleanUp() throws Exception {
        FileUtils.deleteDirectory(outDir);
    }

    @Test()
    public void whenDataOkThenGenerateScratch1() throws Exception {
        List<String> students = Arrays.asList("ADRIANA KRUGER GIACOMOZZI", "ISABELA KRUGER GIACOMOZZI");
        callGenerate(AvailableCourse.SCRATCH1.getName(), AvailableCourse.SCRATCH1.getCode(), LEADER, students);
    }

    @Test()
    public void whenDataOkThenGenerateScratch2() throws Exception {
        List<String> students = Arrays.asList("ARTHUR SCHRAMM DE LIMA", "EVILYN BIANCA DE PAULA JUNG");
        callGenerate(AvailableCourse.SCRATCH2.getName(), AvailableCourse.SCRATCH2.getCode(), LEADER, students);
    }

    @Test
    public void whenDataOkThenGenerateScratch3() throws Exception {
        List<String> students = Arrays.asList("ARTHUR SCHRAMM DE LIMA", "EVILYN BIANCA DE PAULA JUNG");
        callGenerate(AvailableCourse.SCRATCH3.getName(), AvailableCourse.SCRATCH3.getCode(), LEADER, students);
    }

    @Test()
    public void whenDataOkThenGeneratePython1() throws Exception {
        List<String> students = Arrays.asList("FERNANDO CORRÊA RODRIGUES DOS SANTOS",
                "GUILHERME HANDRYCH DE SOUZA ALVES");
        callGenerate(AvailableCourse.PYTHON1.getName(), AvailableCourse.PYTHON1.getCode(), LEADER, students);

    }

    @Test()
    public void whenDataOkThenGeneratePython2() throws Exception {
        List<String> students = Arrays.asList("RAFAEL PONTES STENGER", "LARISSA MARTINS DO AMARAL");
        callGenerate(AvailableCourse.PYTHON2.getName(), AvailableCourse.PYTHON2.getCode(), LEADER, students);

    }

    @Test()
    public void whenDataOkThenGenerateWeb1() throws Exception {
        List<String> students = Arrays.asList("WENDELL KAWE SAMPAIO DE SOUZA", "RICHARD PAULINO DA SILVA");
        callGenerate(AvailableCourse.WEB1.getName(), AvailableCourse.WEB1.getCode(), LEADER, students);

    }

    @Test()
    public void whenDataOkThenGenerateWeb2() throws Exception {
        List<String> students = Arrays.asList("TAMARIS DA SILVA", "CAROLINA DA LUZ",
                "RICHARD YAGO");
        callGenerate(AvailableCourse.WEB2.getName(), AvailableCourse.WEB2.getCode(), LEADER, students);

    }

    @Test()
    public void whenDataOkThenGenerateZipWeb2() throws Exception {
        List<String> students = Arrays.asList("TAMARIS DA DE CARVALHO", "CAROLINA FORTUNATO DA LUZ",
                "RICHARD DE SOUZA");
        callGenerateAndZip(AvailableCourse.WEB2.getName(), AvailableCourse.WEB2.getCode(), LEADER, students);
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
        assertThat(outZip.exists(), is(equalTo(true)));
    }

}
