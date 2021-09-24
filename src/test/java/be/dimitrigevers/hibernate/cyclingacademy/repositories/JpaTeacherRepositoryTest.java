package be.dimitrigevers.hibernate.cyclingacademy.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import be.dimitrigevers.hibernate.cyclingacademy.domain.Gender;
import be.dimitrigevers.hibernate.cyclingacademy.domain.Teacher;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;

@DataJpaTest
@Sql("/insertTestTeacher.sql")
@Import(JpaTeacherRepository.class)
class JpaTeacherRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    private static final String TEACHERS = "teachers";
    private final JpaTeacherRepository jpaTeacherRepository;
    private Teacher teacher;

    @BeforeEach
    void beforeEach(){
        teacher = new Teacher("testF", "testL", BigDecimal.TEN, "testL@test.be", Gender.FEMALE);
    }

    public JpaTeacherRepositoryTest(JpaTeacherRepository jpaDocentRepository) {
        this.jpaTeacherRepository = jpaDocentRepository;
    }

    private long idMaleTeacher() {
        return super.jdbcTemplate.queryForObject(
                "select id from teachers where firstname = 'testNameM'",
                Long.class
        );
    }

    private long idFeMaleTeacher() {
        return super.jdbcTemplate.queryForObject(
                "select id from teachers where firstname = 'testNameF'",
                Long.class
        );
    }

    private long idOtherTeacher() {
        return super.jdbcTemplate.queryForObject(
                "select id from teachers where firstname = 'testNameX'",
                Long.class
        );
    }

    @Test
    void findByIdExisting(){
        long id = idFeMaleTeacher();
        Assertions.assertThat(
                jpaTeacherRepository.findById(id).get().getFirstname()
        ).isEqualTo("testNameF");
    }

    @Test
    void findByIdNonExisting(){
        assertThat(
                jpaTeacherRepository.findById(-1)
        ).isNotPresent();
    }

    @Test
    void male(){
        Assertions.assertThat(
                jpaTeacherRepository.findById(idMaleTeacher()).get().getGender()
        ).isEqualTo(Gender.MALE);
    }

    @Test
    void female(){
        Assertions.assertThat(
                jpaTeacherRepository.findById(idFeMaleTeacher()).get().getGender()
        ).isEqualTo(Gender.FEMALE);
    }

    @Test
    void other(){
        Assertions.assertThat(
                jpaTeacherRepository.findById(idOtherTeacher()).get().getGender()
        ).isEqualTo(Gender.OTHER);
    }

    @Test
    void create(){
        jpaTeacherRepository.create(teacher);
        assertThat(teacher.getId()).isPositive();
        assertThat(
                super.countRowsInTableWhere(TEACHERS, "id="+teacher.getId())
        ).isOne();
    }
}