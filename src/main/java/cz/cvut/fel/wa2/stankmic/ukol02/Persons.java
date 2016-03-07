package cz.cvut.fel.wa2.stankmic.ukol02;

import javax.persistence.*;

@Entity
@Table(name = "Persons")
public class Persons {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;

    private String firstName, lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
