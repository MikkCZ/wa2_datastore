package cz.cvut.fel.wa2.stankmic.ukol01.stores;

import cz.cvut.fel.wa2.stankmic.ukol01.entities.IEntity;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class DiskEntityStore<T extends IEntity> implements IEntityStore<T> {

    private String STORE_LOCATION = "./store";
    private final String classLocation;

    public DiskEntityStore(String classLocation) {
        this.classLocation = classLocation;
    }

    @Override
    public void createNew(T entity) throws IOException {
        String id = UUID.randomUUID().toString();
        entity.setId(id);
        saveToFile(entity);
        System.out.println("Saving "+entity.toString());
    }

    @Override
    public T getById(String id) throws IOException, ClassNotFoundException {
        File file = Paths.get(STORE_LOCATION, classLocation, id).toFile();
        InputStream is = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(is);
        @SuppressWarnings("unchecked")
        T entity = (T) ois.readObject();
        ois.close();
        is.close();
        return entity;
    }

    @Override
    public Collection<T> getAll() throws IOException, ClassNotFoundException {
        // lambda
        Collection<T> results = new ArrayList<>();
        Files.walk(Paths.get(STORE_LOCATION, classLocation)).forEach(
                file -> {
                    if (Files.isRegularFile(file)) {
                        T entity = null;
                        try {
                            entity = getById(file.getFileName().toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        results.add(entity);
                    }
                }
        );
        return results;
    }

    @Override
    public void update(T entity) throws IOException {
        saveToFile(entity);
    }

    private void saveToFile(T entity) throws IOException {
        String path = Paths.get(STORE_LOCATION, classLocation, entity.getId()).toString();
        File f = new File(path);
        f.getParentFile().mkdirs();
        OutputStream os = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(entity);
        oos.close();
        os.close();
    }

    @Override
    public void delete(String id) throws IOException {
        Files.delete(Paths.get(STORE_LOCATION, classLocation, id));
    }

    @Override
    public void createOrUpdate(T entity) throws IOException {
        if (entity.getId().equals(null)) {
            createNew(entity);
        } else {
            update(entity);
        }
    }
}
