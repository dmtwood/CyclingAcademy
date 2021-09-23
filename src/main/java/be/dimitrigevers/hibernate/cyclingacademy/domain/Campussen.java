package be.dimitrigevers.hibernate.cyclingacademy.domain;

import javax.persistence.*;

@Table(name = "campussen")
@Entity
public class Campussen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}