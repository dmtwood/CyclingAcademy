package be.dimitrigevers.hibernate.cyclingacademy.services;

import be.dimitrigevers.hibernate.cyclingacademy.domain.Gender;
import be.dimitrigevers.hibernate.cyclingacademy.domain.Teacher;
import be.dimitrigevers.hibernate.cyclingacademy.domain.TeacherService;
import be.dimitrigevers.hibernate.cyclingacademy.exceptions.TeacherNotFoundException;
import be.dimitrigevers.hibernate.cyclingacademy.repositories.JpaTeacherRepository;
import be.dimitrigevers.hibernate.cyclingacademy.repositories.TeacherRepository;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DefaultTeacherServiceTest {

    DefaultTeacherService teacherService;

    @Autowired
    @Mock
    private Teacher testTeacher;
    @Autowired
    @Mock
    TeacherRepository teacherRepository;

    @BeforeEach
    void beforeEach() {
        testTeacher = new Teacher("testM", "testM", BigDecimal.TEN, "testM@test.be", Gender.MALE);
        teacherService = new DefaultTeacherService(teacherRepository);
    }

    @Test
    void raiseTeacherWageWith() {
        Assertions.setMaxStackTraceElementsDisplayed(70);
        // train the mock -> map the testTeacher to id 1
        when(
                teacherRepository.findById(1L)
        ).thenReturn(
                Optional.of(testTeacher)
        );

        // test the method
        teacherService.raiseTeacherWageWith(1, BigDecimal.TEN);
        assertThat(
                testTeacher.getWage()
        ).isEqualByComparingTo("11");

        // verify that method (getWage) was called with given parameter (id 1)
        verify(teacherRepository).findById(1);
    }

    @Test
    void raiseTeacherWageWithNonExistingTeacher(){
        assertThatExceptionOfType( TeacherNotFoundException.class )
                .isThrownBy(
                        () -> teacherService.raiseTeacherWageWith(-1, BigDecimal.TEN)
                );

        // verify that method (getWage) was called with given parameter (id -1)
        verify(teacherRepository).findById(-1);
    }
}