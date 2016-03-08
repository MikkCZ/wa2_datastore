package cz.cvut.fel.wa2.stankmic.ukol02.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Person")
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    private String firstName, lastName;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Car.class)
    private Set<Car> borrowedCars = new HashSet<Car>();
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Phone.class)
    private Set<Phone> phones = new HashSet<Phone>();
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Address.class)
    private Set<Address> addresses = new HashSet<Address>();

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<Car> getBorrowedCars() {
        return borrowedCars;
    }

    public void setBorrowedCars(Set<Car> borrowedCars) {
        this.borrowedCars = borrowedCars;
    }

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
