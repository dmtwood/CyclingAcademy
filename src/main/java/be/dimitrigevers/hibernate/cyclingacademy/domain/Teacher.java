package be.dimitrigevers.hibernate.cyclingacademy.domain;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "teachers")
public class Teacher {

    // MEMBER VARS

//    @Column(name = "id", nullable = false)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String firstname;
    private String lastname;
    private BigDecimal wage;
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    // CONSTRUCTORS

    protected Teacher() {
    }

    public Teacher(String firstname, String lastname, BigDecimal wage, String email, Gender gender) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.wage = wage;
        this.email = email;
        this.gender = gender;
    }

    // GETTERS ( & SETTERS IF MUTABLE)

    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Gender getGender() {
        return gender;
    }

    // METHODS


// OVERRIDDEN METHODS

}
