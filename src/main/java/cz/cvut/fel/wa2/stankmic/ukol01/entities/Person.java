package cz.cvut.fel.wa2.stankmic.ukol01.entities;

public class Person implements IEntity {

    private static final long serialVersionUID = -977266188836888595L;
    private final String storeLocation = "people";

    private String id;
    private String firstName, lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getStoreLocation() {
        return storeLocation;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String toString() {
        return String.format("id %s, firstName %s, lastName %s", id, firstName, lastName);
    }
}
