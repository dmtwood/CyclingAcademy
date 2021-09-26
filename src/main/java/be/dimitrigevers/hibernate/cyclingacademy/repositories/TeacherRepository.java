package be.dimitrigevers.hibernate.cyclingacademy.repositories;

import be.dimitrigevers.hibernate.cyclingacademy.domain.Teacher;
import be.dimitrigevers.hibernate.cyclingacademy.queryresults.IdAndEmail;
import be.dimitrigevers.hibernate.cyclingacademy.queryresults.CountByWage;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TeacherRepository {

    public Optional<Teacher> findById(long id);

    public void create(Teacher teacher);

    public void delete(long id);

    public List<Teacher> findAll();

    List<Teacher> findByWageBetween(BigDecimal minWage, BigDecimal maxWage);

    List<String> findAllEmailAddresses();

    List<IdAndEmail> findAllIdsAndEmails();

    public BigDecimal findHighestWage();

    List<CountByWage> findTeacherCountByWage();
}
