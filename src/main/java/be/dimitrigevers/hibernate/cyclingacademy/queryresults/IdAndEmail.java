package be.dimitrigevers.hibernate.cyclingacademy.queryresults;

public class IdAndEmail {

// MEMBER VARS

    private final long id;
    private final String email;


// CONSTRUCTORS

    public IdAndEmail(long id, String email) {
        this.id = id;
        this.email = email;
    }


// GETTERS ( & SETTERS IF MUTABLE)

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }


// METHODS


// OVERRIDDEN METHODS

}
