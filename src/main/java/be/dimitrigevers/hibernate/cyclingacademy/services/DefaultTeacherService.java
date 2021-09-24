package be.dimitrigevers.hibernate.cyclingacademy.services;

import be.dimitrigevers.hibernate.cyclingacademy.domain.TeacherService;
import be.dimitrigevers.hibernate.cyclingacademy.exceptions.TeacherNotFoundException;
import be.dimitrigevers.hibernate.cyclingacademy.repositories.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class DefaultTeacherService implements TeacherService {

// MEMBER VARS

    private final TeacherRepository teacherRepository;

// CONSTRUCTORS

    public DefaultTeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


// GETTERS ( & SETTERS IF MUTABLE)


// METHODS


// OVERRIDDEN METHODS

    @Override
    public void raiseTeacherWageWith(long id, BigDecimal percentageToRaiseWage) {
        teacherRepository
                .findById(id)
                .orElseThrow( TeacherNotFoundException::new )
                .raiseWage(percentageToRaiseWage);
    }
}
