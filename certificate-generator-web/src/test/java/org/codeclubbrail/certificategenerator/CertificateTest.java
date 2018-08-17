package org.codeclubbrail.certificategenerator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

import org.codeclubbrail.certificategenerator.resources.model.Certificate;
import org.junit.Before;
import org.junit.Test;

public class CertificateTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testGetStudentsNamesList() {
        Certificate certificate = new Certificate();
        certificate.setStudents("ab;c;ddddd");
        assertArrayEquals(certificate.getStudentsNamesList().toArray(),
                new String[]{"ab", "c", "ddddd"});
    }

    @Test
    public void whenSetLeaderNameThenGetLeaderName() {
        Certificate certificate = new Certificate();
        String leaderName = "Leader Name";
        certificate.setLeaderName(leaderName);
        assertThat(certificate.getLeaderName(), equalTo(leaderName));
    }

    @Test
    public void testGetStudents() {
        //fail("Not yet implemented");
    }

    @Test
    public void testGetCourse() {
        //fail("Not yet implemented");
    }

}
