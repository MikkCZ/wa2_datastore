package cz.cvut.fel.wa2.stankmic.ukol01.entities;

import cz.cvut.fel.wa2.stankmic.ukol01.stores.DiskEntityStore;
import cz.cvut.fel.wa2.stankmic.ukol01.stores.IEntityStore;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class Relation<U extends IEntity, V extends IEntity> implements IEntity {
    private static final long serialVersionUID = 3149294116516704554L;
    private final String storeLocation = "relation";

    private String id;
    private U owner;
    private V owned;

    public Relation(U owner, V owned) {
        this.owner = owner;
        this.owned = owned;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public U getOwner() {
        return owner;
    }

    public void setOwner(U owner) {
        this.owner = owner;
    }

    public V getOwned() {
        return owned;
    }

    public void setOwned(V owned) {
        this.owned = owned;
    }

    @Override
    public String getStoreLocation() {
        return storeLocation;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        // default serialization
        oos.defaultWriteObject();
        // write the object
        oos.writeObject(this.id);
        final IEntityStore<U> storeU = new DiskEntityStore<>(owner.getStoreLocation());
        storeU.createOrUpdate(owner);
        oos.writeObject(owner.getId());
        final IEntityStore<V> storeV = new DiskEntityStore<>(owned.getStoreLocation());
        storeV.createOrUpdate(owned);
        oos.writeObject(owned.getId());
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        // default deserialization
        ois.defaultReadObject();
        id = (String) ois.readObject();
        final String ownerId = (String) ois.readObject();
        final IEntityStore<U> storeU = new DiskEntityStore<>(owner.getStoreLocation());
        owner = storeU.getById(ownerId);
        final String ownedId = (String) ois.readObject();
        final IEntityStore<V> storeV = new DiskEntityStore<>(owned.getStoreLocation());
        owned = storeV.getById(ownedId);
    }

    public String toString() {
        return String.format("id %s, %s owns %s", id, owner.toString(), owned.toString());
    }
}
