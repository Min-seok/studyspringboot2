package com.study.web;

import com.study.domain.academy.*;
import com.study.service.academy.AcademyService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AcademyControllerTest {

    @Autowired
    private AcademyRepository academyRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    AcademyService academyService;

    @After
    public void tearDown() throws Exception {
        academyRepository.deleteAllInBatch();
    }

    @Test
    public void querydsl_테스트() {

        String name = "oh";
        String address = "";
        String phoneNumber = "010-0000-0000";

        academyRepository.save(new Academy(name, address, phoneNumber));

        List<Academy> result = academyService.findDynamicQuery(name, address, phoneNumber);

        assertThat(result.size(), is(1));
        assertThat(result.get(0).getAddress(), is(address));
    }

    @Test
    public void querydsl_테스트11() {

        String name = "oh";
        String address = "";
        String phoneNumber = "010-0000-0000";

        String teacherName = "qqq";

        Academy academy = new Academy(name, address, phoneNumber);
        academyRepository.save(academy);
        teacherRepository.save(new Teacher(academy.getId(), teacherName));

//        List<Academy> result = academyService.findDynamicQuery(name, address, phoneNumber);
        List<AcademyTeacher> academyTeacherList = academyService.findAllAcademyTearch();

        assertThat(academyTeacherList.size(), is(1));
        assertThat(academyTeacherList.get(0).getAcademyName(), is(name));
        assertThat(academyTeacherList.get(0).getTeacherName(), is(teacherName));
    }
}