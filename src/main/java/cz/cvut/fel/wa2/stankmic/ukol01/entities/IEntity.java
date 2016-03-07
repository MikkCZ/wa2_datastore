package cz.cvut.fel.wa2.stankmic.ukol01.entities;

import java.io.Serializable;

public interface IEntity extends Serializable {
    public String getId();

    public void setId(String id);

    public String getStoreLocation();
}
