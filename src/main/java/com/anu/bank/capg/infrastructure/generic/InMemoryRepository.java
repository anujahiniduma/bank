package com.anu.bank.capg.infrastructure.generic;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRepository<T, ID> implements BaseRepository<T, ID> {
    protected final Map<ID, T> storage = new ConcurrentHashMap<>();

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(storage.get(id));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void save(T entity) {
        try {
            Field idField = findIdField(entity);
            Object idObj = idField.get(entity);
            if (idObj == null) {
                throw new RuntimeException("ID field is null");
            }
            ID id = (ID) idField.getType().cast(idObj);
            storage.put(id, entity);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("ID field is missing or inaccessible in entity", e);
        }
    }

    private Field findIdField(T entity) throws NoSuchFieldException {
        Class<?> clazz = entity.getClass();
        while (clazz != null) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getName().equalsIgnoreCase("id")) {
                    field.setAccessible(true);
                    return field;
                }
            }
            clazz = clazz.getSuperclass();
        }
        throw new NoSuchFieldException("No field named 'id' found in " + entity.getClass().getSimpleName());
    }
}
