package ru.example.articles.service;

import ru.example.articles.model.Article;
import ru.example.articles.model.Word;
import ru.example.articles.store.Store;

import java.util.List;

public interface ArticleService {
    void generate(List<Word> words, int count, Store<Article> articleStore);
}
