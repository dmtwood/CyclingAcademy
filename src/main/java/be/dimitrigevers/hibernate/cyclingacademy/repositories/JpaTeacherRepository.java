package be.dimitrigevers.hibernate.cyclingacademy.repositories;

import be.dimitrigevers.hibernate.cyclingacademy.domain.Teacher;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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
                entityManager.find( Teacher.class, id )
        );
    }
}
