package cz.cvut.fel.wa2.stankmic.ukol02.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Phone")
public class Phone implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    private Person owner;

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    private String number;

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }

    private PhoneType type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public enum PhoneType {
        MOBILE, WORK, HOME;
    }
}
