package be.dimitrigevers.hibernate.cyclingacademy.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@DataJpaTest
@Sql("/insertTestTeacher.sql")
@Import(JpaTeacherRepository.class)
class JpaTeacherRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    private final JpaTeacherRepository jpaTeacherRepository;

    public JpaTeacherRepositoryTest(JpaTeacherRepository jpaDocentRepository) {
        this.jpaTeacherRepository = jpaDocentRepository;
    }

    private long idVanTestDocent() {
        return super.jdbcTemplate.queryForObject(
                "select id from teachers where firstname = 'testName'",
                Long.class
        );
    }

    @Test
    void findByIdExisting(){
        long id = idVanTestDocent();
        Assertions.assertThat(
                jpaTeacherRepository.findById(id).get().getFirstname()
        ).isEqualTo("testName");
    }

    @Test
    void findByIdNonExisting(){
        assertThat(
                jpaTeacherRepository.findById(-1)
        ).isNotPresent();
    }


}