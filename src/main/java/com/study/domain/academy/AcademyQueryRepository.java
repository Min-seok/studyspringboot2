package com.study.domain.academy;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.study.domain.academy.QAcademy.academy;
import static com.study.domain.academy.QTeacher.teacher;

@RequiredArgsConstructor
@Repository
public class AcademyQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Academy> findByName(String name) {
        return queryFactory.selectFrom(academy)
                .where(academy.name.eq(name))
                .fetch();
    }

    public List<AcademyTeacher> findAllAcademyTearch() {

        return queryFactory
                .select(Projections.fields(AcademyTeacher.class,
                        academy.name.as("academyName"),
                        teacher.name.as("teacherName")
                ))
                .from(academy)
                .join(teacher).on(academy.id.eq(teacher.academyId))
                .fetch();
    }

    public List<Academy> findDynamicQuery(String name, String address, String phoneNumber) {

        return queryFactory
                .selectFrom(academy)
                .where(eqName(name),
                        eqAddress(address),
                        eqPhoneNumber(phoneNumber))
                .fetch();
    }

    private BooleanExpression eqName(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        return academy.name.eq(name);
    }

    private BooleanExpression eqAddress(String address) {
        if (StringUtils.isEmpty(address)) {
            return null;
        }
        return academy.address.eq(address);
    }

    private BooleanExpression eqPhoneNumber(String phoneNumber) {
        if (StringUtils.isEmpty(phoneNumber)) {
            return null;
        }
        return academy.phoneNumber.eq(phoneNumber);
    }
}
