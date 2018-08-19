package org.codeclubbrasil.certificategenerator;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.codeclubbrasil.certificategenerator.domain.CertificateTemplate;
import org.codeclubbrasil.certificategenerator.domain.TemplateType;
import org.junit.Before;
import org.junit.Test;

public class CertificateTemplateTest {

    private static final String TEMPLATE_TEMP_DIR = System.getProperty("java.io.tmpdir") + "/pdf/";
    private CertificateTemplate template;

    @Before
    public void setUp() throws Exception {
        template = CertificateTemplate.fromTemplateNamePDF("web1");
    }

    @Test
    public void testHashCode() {
        assertThat(template.hashCode(), anything());
    }

    @Test
    public void testFromTemplateNamePDF() {
        assertThat(template.getType(), is(TemplateType.PDF));
    }

    @Test
    public void testGetAbsolutePath() {
        assertThat(template.getAbsolutePath(), containsString("/web1.pdf"));
    }

    @Test
    public void testGetName() {
        assertThat(template.getName(), is(equalTo("web1.pdf")));
    }

    @Test
    public void testGetPath() {
        assertThat(template.getPath(), is(equalTo(TEMPLATE_TEMP_DIR)));
    }

    @Test
    public void testGetType() {
        assertThat(template.getType(), is(TemplateType.PDF));
    }

    @Test
    public void testSetName() {
        template.setName("name");
        assertThat(template.getName(), is(equalTo("name")));
    }

    @Test
    public void testToString() {
        assertThat(template.toString(), containsString("web1.pdf"));
    }

}
