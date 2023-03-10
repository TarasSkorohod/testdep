package com.tinder.dao;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Dao<T> {
    List<T> getAll();

    Optional<T> get(int id);

    List<T> getBy(Predicate<T> predicate);

    void save(T entity);

    void delete(int id);

}
