package be.dimitrigevers.hibernate.cyclingacademy.repositories;

import be.dimitrigevers.hibernate.cyclingacademy.domain.Gender;
import be.dimitrigevers.hibernate.cyclingacademy.domain.Teacher;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql("/insertTestTeacher.sql")
@Import(JpaTeacherRepository.class)
class JpaTeacherRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    private static final String TEACHERS = "teachers";
    private final JpaTeacherRepository jpaTeacherRepository;
    private Teacher teacher;
    private final EntityManager entityManager;

    @BeforeEach
    void beforeEach() {
        teacher = new Teacher("testF", "testL", BigDecimal.TEN, "testL@test.be", Gender.FEMALE);
    }

    public JpaTeacherRepositoryTest(JpaTeacherRepository jpaDocentRepository, EntityManager entityManager) {
        this.jpaTeacherRepository = jpaDocentRepository;
        this.entityManager = entityManager;
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
    void findByIdExisting() {
        long id = idFeMaleTeacher();
        Assertions.assertThat(
                jpaTeacherRepository.findById(id).get().getFirstname()
        ).isEqualTo("testNameF");
    }

    @Test
    void findByIdNonExisting() {
        assertThat(
                jpaTeacherRepository.findById(-1)
        ).isNotPresent();
    }

    @Test
    void male() {
        Assertions.assertThat(
                jpaTeacherRepository.findById(idMaleTeacher()).get().getGender()
        ).isEqualTo(Gender.MALE);
    }

    @Test
    void female() {
        Assertions.assertThat(
                jpaTeacherRepository.findById(idFeMaleTeacher()).get().getGender()
        ).isEqualTo(Gender.FEMALE);
    }

    @Test
    void other() {
        Assertions.assertThat(
                jpaTeacherRepository.findById(idOtherTeacher()).get().getGender()
        ).isEqualTo(Gender.OTHER);
    }

    @Test
    void create() {
        jpaTeacherRepository.create(teacher);
        assertThat(teacher.getId()).isPositive();
        assertThat(
                super.countRowsInTableWhere(TEACHERS, "id=" + teacher.getId())
        ).isOne();
    }

    @Test
    void delete() {
        var id = idMaleTeacher();
        jpaTeacherRepository.delete(id);
        // force JPA to delete now
        entityManager.flush();
        assertThat(
                super.countRowsInTableWhere(TEACHERS, "id=" + id)
        ).isZero();
    }

    @Test
    void findAll() {
        assertThat(
                jpaTeacherRepository.findAll()
        ).hasSize(
                super.countRowsInTable(TEACHERS)
        ).extracting(
                Teacher::getLastname
        ).isSorted();
    }

    @Test
    void findByWageBetween(){
        var MIN_WAGE = BigDecimal.valueOf(2000);
        var MAX_WAGE = BigDecimal.valueOf(8000);
        assertThat(
                jpaTeacherRepository.findByWageBetween(MIN_WAGE, MAX_WAGE)
        ).hasSize(
                super.countRowsInTableWhere(
                        TEACHERS,
                        "wage between 2000 and 8000"
                )
        ).allSatisfy(
          teacher -> assertThat( teacher.getWage() ).isBetween(MIN_WAGE, MAX_WAGE)
        );
    }

    @Test
    public void findAllEmailAddresses(){
        assertThat(
         jpaTeacherRepository.findAllEmailAddresses()
        ).hasSize(
                super.jdbcTemplate.queryForObject(
                        "select count(email) from teachers",
                        Integer.class)
        ).allSatisfy(
                emailAddress -> assertThat( emailAddress ).contains("@")
        );
    }

    @Test
    public void findAllIdsAndEmails(){
        assertThat(
                jpaTeacherRepository.findAllIdsAndEmails()
        ).hasSize(
                super.countRowsInTable(TEACHERS)
        );
    }

    @Test
    public void findHighestWage(){
        assertThat(
                jpaTeacherRepository.findHighestWage()
        ).isEqualByComparingTo(
                super.jdbcTemplate.queryForObject(
                        "select max(wage) from teachers",
                        BigDecimal.class)
                );
    }

    @Test
    public void findTeacherCountByWage(){
        BigDecimal givenWage = BigDecimal.valueOf(3500);
        assertThat(
                jpaTeacherRepository.findTeacherCountByWage()
        ).hasSize(
                super.jdbcTemplate.queryForObject(
                "select count(distinct wage) from teachers",
                Integer.class
                )
        ).filteredOn(
                countByWage -> countByWage.getGivenWage().compareTo(givenWage) == 0
        ).allSatisfy(
            countByWage -> assertThat(
                    countByWage.getTeacherCount()
            ).isEqualTo(
                    super.countRowsInTableWhere(TEACHERS, "wage=3500")
            )
        );
    }
}
















