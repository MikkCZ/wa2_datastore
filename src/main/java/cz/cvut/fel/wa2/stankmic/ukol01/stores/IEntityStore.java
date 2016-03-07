package cz.cvut.fel.wa2.stankmic.ukol01.stores;

import cz.cvut.fel.wa2.stankmic.ukol01.entities.IEntity;

import java.io.IOException;
import java.util.Collection;

public interface IEntityStore<T extends IEntity> {
    void createNew(T entity) throws IOException;

    T getById(String id) throws IOException, ClassNotFoundException;

    Collection<T> getAll() throws IOException, ClassNotFoundException;

    void update(T entity) throws IOException;

    void delete(String id) throws IOException;

    void createOrUpdate(T entity) throws IOException;
}