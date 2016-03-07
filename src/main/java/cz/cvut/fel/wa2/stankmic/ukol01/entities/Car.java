package cz.cvut.fel.wa2.stankmic.ukol01.entities;

public class Car implements IEntity {
    private static final long serialVersionUID = -874476944152076688L; // = marker interface
    private final String storeLocation = "cars";

    private String id;
    private String name;

    public Car(String name) {
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getStoreLocation() {
        return storeLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return String.format("id %s, name %s", id, name);
    }
}