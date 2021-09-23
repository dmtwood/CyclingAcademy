package be.dimitrigevers.hibernate.cyclingacademy.domain;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "teachers")
public class Teacher {

    // MEMBER VARS

//    @Column(name = "id", nullable = false)
    @Id private Long id;

    private String firstname;
    private String lastname;
    private BigDecimal wage;
    private String email;

    // PROTECTED CONSTRUCTOR

    protected Teacher() {
    }


// GETTERS ( & SETTERS IF MUTABLE)

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public BigDecimal getWage() {
        return wage;
    }

    public String getEmail() {
        return email;
    }


// METHODS


// OVERRIDDEN METHODS

}
