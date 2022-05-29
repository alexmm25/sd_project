package backend.model;

import javax.persistence.*;

@Entity
@DiscriminatorValue("sponsor")
public class Sponsor extends User {

    @Column
    private String name;

    @OneToOne
    @JoinColumn(name = "stadiumID")
    private Stadium stadium;

    public Sponsor(String name, String username, String password, Stadium stadium, String email) {
        super(username, password, email);
        this.name = name;
        this.stadium = stadium;
    }

    public Sponsor(String username, String password) {
        super(username, password, "");
    }

    public Sponsor() {}

    public String getName() {
        return name;
    }

    public Stadium getStadium() {
        return stadium;
    }

}