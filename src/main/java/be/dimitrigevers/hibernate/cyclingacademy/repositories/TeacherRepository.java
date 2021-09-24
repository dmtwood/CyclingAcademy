package be.dimitrigevers.hibernate.cyclingacademy.repositories;

import be.dimitrigevers.hibernate.cyclingacademy.domain.Teacher;

import java.util.Optional;

public interface TeacherRepository {

    public Optional<Teacher> findById(long id);

    public void create(Teacher teacher);


}
