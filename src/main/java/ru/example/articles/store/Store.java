package ru.example.articles.store;

import java.util.List;

public interface Store<T> {
    T save(T model);
    List<T> findAll();
}
