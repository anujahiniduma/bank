package com.anu.bank.capg.infrastructure.generic;

import java.util.Optional;

public interface BaseRepository<T, ID> {
    Optional<T> findById(ID id);

    void save(T entity);
}
