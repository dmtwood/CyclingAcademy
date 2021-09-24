package be.dimitrigevers.hibernate.cyclingacademy.services;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
// import the service of wich the integration will be tested
@Import(DefaultTeacherService.class)
// component scan spots package private variables from other packages
@ComponentScan(
        value = "be.dimitrigevers.hibernate.cyclingacademy.repositories",
        resourcePattern = "JpaTeacherRepository.class")
@Sql("/insertTestTeacher.sql")
class DefaultDocentServiceIntegrationTest  extends AbstractTransactionalJUnit4SpringContextTests {

// MEMBER VARS

    private final DefaultTeacherService teacherService;
    private final EntityManager entityManager;

// CONSTRUCTORS

    public DefaultDocentServiceIntegrationTest(DefaultTeacherService teacherService, EntityManager entityManager) {
        this.teacherService = teacherService;
        this.entityManager = entityManager;
    }


// METHODS

    private long idTestPerson(){
        return super.jdbcTemplate.queryForObject(
                "select id from teachers where firstname = 'testNameM'",
                Long.class
        );
    }


// TEST METHODS

    @Test
    void RaiseWage() {
        var id = idTestPerson();
        teacherService.raiseTeacherWageWith(id, BigDecimal.TEN);
        entityManager.flush();
        assertThat(
                super.jdbcTemplate.queryForObject(
                        "select wage from teachers where id=" + id,
                        BigDecimal.class
                        )
                ).isEqualByComparingTo("3300");
    }
}
