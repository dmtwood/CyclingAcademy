package be.dimitrigevers.hibernate.cyclingacademy.queryresults;

import java.math.BigDecimal;

public class CountByWage {

// MEMBER VARS

    private final long teacherCount;
    private final BigDecimal givenWage;

// CONSTRUCTORS

    public CountByWage(long teacherCount, BigDecimal givenWage) {
        this.teacherCount = teacherCount;
        this.givenWage = givenWage;
    }


// GETTERS ( & SETTERS IF MUTABLE)

    public long getTeacherCount() {
        return teacherCount;
    }

    public BigDecimal getGivenWage() {
        return givenWage;
    }

    // METHODS


// OVERRIDDEN METHODS

}
