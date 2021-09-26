package be.dimitrigevers.hibernate.cyclingacademy.repositories;

import be.dimitrigevers.hibernate.cyclingacademy.domain.Teacher;
import be.dimitrigevers.hibernate.cyclingacademy.queryresults.IdAndEmail;
import be.dimitrigevers.hibernate.cyclingacademy.queryresults.CountByWage;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaTeacherRepository implements TeacherRepository {

// MEMBER VARS

    private final EntityManager entityManager;


// CONSTRUCTORS

    public JpaTeacherRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


// OVERRIDDEN METHODS

    @Override
    public Optional<Teacher> findById(long id) {
        return Optional.ofNullable(
                entityManager.find(Teacher.class, id)
        );
    }

    @Override
    public void create(Teacher teacher) {
        entityManager.persist(teacher);
    }

    @Override
    public void delete(long id) {
        findById(id).ifPresent(
                teacher -> entityManager.remove(teacher)
        );
    }

    @Override
    public List<Teacher> findAll() {
        return entityManager.createQuery(
                "select t from Teacher t order by t.lastname",
                Teacher.class
        ).getResultList();
    }

    @Override
    public List<Teacher> findByWageBetween(BigDecimal minWage, BigDecimal maxWage) {
        return entityManager.createNamedQuery(
                "Teacher.findByWageBetween",
                Teacher.class
        ).getResultList();
//        return entityManager.createQuery(
//                        "select t from Teacher t where t.wage between :minWage and :maxWage",
//                        Teacher.class
//                ).setParameter("minWage", minWage)
//                .setParameter("maxWage", maxWage)
//                .getResultList();
    }

    @Override
    public List<String> findAllEmailAddresses() {
        return entityManager.createQuery(
                "select t.email from Teacher t",
                String.class
        ).getResultList();
    }

    @Override
    public List<IdAndEmail> findAllIdsAndEmails() {
        return entityManager.createQuery(
                "select new " +
                        "be.dimitrigevers.hibernate.cyclingacademy.queryresults" +
                        ".IdAndEmail( t.id, t.email ) " +
                        "from Teacher t",
                IdAndEmail.class
        ).getResultList();
    }

    @Override
    public BigDecimal findHighestWage() {
        return entityManager.createQuery(
                "select max(t.wage) from Teacher t",
                BigDecimal.class
        ).getSingleResult();
    }

    @Override
    public List<CountByWage> findTeacherCountByWage() {
        return entityManager.createQuery(
                "select new " +
                        "be.dimitrigevers.hibernate.cyclingacademy.queryresults" +
                        ".CountByWage( count(t), t.wage ) " +
                        "from Teacher t " +
                        "group by t.wage",
                CountByWage.class
        ).getResultList();
    }
}

























