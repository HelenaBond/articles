package ru.example.articles.store;

import java.util.List;

public interface Store<T> {
    void save(T model);
    List<T> findAllAfter(int id, int size);
}
