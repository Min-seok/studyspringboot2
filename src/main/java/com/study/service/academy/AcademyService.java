package com.study.service.academy;

import com.study.domain.academy.Academy;
import com.study.domain.academy.AcademyQueryRepository;
import com.study.domain.academy.AcademyTeacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AcademyService {

    private final AcademyQueryRepository academyQueryRepository;

    @Transactional(readOnly = true)
    public List<Academy> findByName(String name) {
        List<Academy> academyList = academyQueryRepository.findByName(name);

        return academyList;
    }

    @Transactional(readOnly = true)
    public List<Academy> findDynamicQuery(String name, String address, String phoneNumber) {
        List<Academy> academyList = academyQueryRepository.findDynamicQuery(name, address, phoneNumber);

        return academyList;
    }

    @Transactional(readOnly = true)
    public List<AcademyTeacher> findAllAcademyTearch() {
        List<AcademyTeacher> academyTeacherList = academyQueryRepository.findAllAcademyTearch();

        return academyTeacherList;
    }
}
