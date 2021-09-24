package be.dimitrigevers.hibernate.cyclingacademy.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class TeacherTest {

// MEMBER VARS

    private static final BigDecimal TEST_WAGE = BigDecimal.valueOf(3_000L);
    private Teacher testTeacher;

    @BeforeEach
    void beforeEach(){
        testTeacher = new Teacher("testM", "testM", TEST_WAGE, "testM@test.be", Gender.MALE );
    }


// METHODS

    @Test
    void raiseWage(){
        testTeacher.raiseWage(BigDecimal.TEN);
        Assertions.assertThat(
                testTeacher.getWage()
        ).isEqualByComparingTo(
                "3300"
        );
    }

    @Test
    void raiseWageNull(){
        Assertions.assertThatNullPointerException().isThrownBy(
                () -> testTeacher.raiseWage(null )
        );
    }
    @Test
    void raiseWageZero(){
        Assertions.assertThatIllegalArgumentException().isThrownBy(
                () -> testTeacher.raiseWage( BigDecimal.ZERO )
        );
    }
    @Test
    void raiseWageNegative(){
        Assertions.assertThatIllegalArgumentException().isThrownBy(
                () -> testTeacher.raiseWage( BigDecimal.valueOf(-1L) )
        );
    }

// OVERRIDDEN METHODS

}
